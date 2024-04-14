package com.geektrust.backend.service.impl;

import com.geektrust.backend.dto.Bill;
import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.enums.DriverStatus;
import com.geektrust.backend.enums.RideStatus;
import com.geektrust.backend.exception.ServiceException;
import com.geektrust.backend.repository.IDriverRepository;
import com.geektrust.backend.repository.IRideRepository;
import com.geektrust.backend.service.IDriverService;
import com.geektrust.backend.service.IRideService;
import com.geektrust.backend.service.IRiderService;
import com.geektrust.backend.utils.Utils;
import com.geektrust.backend.utils.fareCalculator.BaseFare;
import com.geektrust.backend.utils.fareCalculator.Fare;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations.AddFarePerMinute;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations.AddFarePerPM;
import com.geektrust.backend.utils.fareCalculator.fareDecorator.calculations.AddServiceTax;
import lombok.RequiredArgsConstructor;

import java.math.RoundingMode;
import java.util.List;

import static com.geektrust.backend.constants.Constants.ONE;
import static com.geektrust.backend.constants.Constants.TWO;
import static com.geektrust.backend.constants.ExceptionConstants.INVALID_RIDE;

@RequiredArgsConstructor
public class RideServiceImpl implements IRideService {

    private final IDriverRepository driverRepository;
    private final IRideRepository rideRepository;
    private final IRiderService riderService;
    private final IDriverService driverService;

    private static final Long MIN_TIME_TRAVELLED_DURATION = 0L;


    @Override
    public void startRide(String rideId, Integer nthDriver, String riderId) {

        List<String> driverIds = riderService.match(riderId).getDriverIds();

        validateStartRide(rideId, nthDriver, driverIds);

        Driver driver = driverService.findById(driverIds.get(nthDriver - ONE));


        // Starting the ride and saving it in the DB
        rideRepository.save(new Ride(rideId, riderId,  driver.getId(), RideStatus.STARTED));

        // making the driver unavailable till the ride gets completed and saving it
        driver.setAvailabilityStatus(DriverStatus.NOT_AVAILABLE);
        driverRepository.save(driver);
    }

    private void validateStartRide(String rideId, Integer nthDriver, List<String> driverIds) {

        if (rideRepository.existsById(rideId)) {
            throw new ServiceException(String.format("RIDE_STARTED %s", rideId));
        }

        // Checking if nthDriver > (list of available drivers) and also if the driver is available
        if ((driverIds.size() < nthDriver)
                || DriverStatus.NOT_AVAILABLE.equals(driverService.findById(driverIds.get(nthDriver - ONE)).getAvailabilityStatus())) {
            throw new ServiceException(INVALID_RIDE);
        }

    }

    private Ride findById(String rideId) {
        return rideRepository.findById(rideId).orElseThrow(() -> new ServiceException(INVALID_RIDE));
    }

    @Override
    public void stopRide(String rideId, Coordinates coordinates, Long rideDuration) {
        Ride ride = this.findById(rideId);
        if (RideStatus.STOPPED.equals(ride.getRideStatus())) throw new ServiceException(INVALID_RIDE);
        if (rideDuration < MIN_TIME_TRAVELLED_DURATION) throw new ServiceException("Time travelled can't be negative");


        // updating the driver status back to available
        Driver driver = driverService.findById(ride.getDriverId());
        driver.setAvailabilityStatus(DriverStatus.AVAILABLE);

        driverRepository.save(driver);

        // stopping the ride and updating the required information
        ride.setDestinationLocation(coordinates);
        ride.setRideStatus(RideStatus.STOPPED);
        ride.setRideDuration(rideDuration);

        rideRepository.save(ride);
    }

    @Override
    public Bill getBillByRideId(String rideId) {

        Ride ride = this.findById(rideId);

        if (!RideStatus.STOPPED.equals(ride.getRideStatus())) {
            throw new ServiceException("RIDE_NOT_COMPLETED");
        }

        Coordinates startLocation = riderService.findById(ride.getRiderId()).getLocation();

        // initializing base fare
        Fare fare = new BaseFare();

        // adding km charge
        fare = new AddFarePerPM(fare, Utils.distanceCalculator(startLocation, ride.getDestinationLocation()));

        // adding additional fare for minutes spent in the ride
        fare = new AddFarePerMinute(fare, ride.getRideDuration());

        // adding service tax on total fare
        fare = new AddServiceTax(fare);

        ride.setTotalFare(fare.getPrice());
        rideRepository.save(ride);

        return new Bill(rideId, ride.getDriverId(), ride.getTotalFare().setScale(TWO, RoundingMode.HALF_EVEN).toPlainString());
    }
}
