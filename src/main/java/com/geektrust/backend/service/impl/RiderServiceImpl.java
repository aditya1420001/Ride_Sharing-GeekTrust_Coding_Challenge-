package com.geektrust.backend.service.impl;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.dto.Match;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.enums.DriverStatus;
import com.geektrust.backend.exception.ServiceException;
import com.geektrust.backend.repository.IDriverRepository;
import com.geektrust.backend.repository.IRiderRepository;
import com.geektrust.backend.service.IRiderService;
import com.geektrust.backend.utils.Utils;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.geektrust.backend.constants.Constants.ONE;
import static com.geektrust.backend.constants.ExceptionConstants.INVALID_RIDE;

@RequiredArgsConstructor
public class RiderServiceImpl implements IRiderService {


    private final IRiderRepository riderRepository;
    private final IDriverRepository driverRepository;

    public static final Double MAX_DRIVERS_ALLOWED = 5.0D;

    @Override
    public Rider load(String riderId, Coordinates location) {
        return riderRepository.save(new Rider(riderId, location));
    }

    @Override
    public Rider findById(String riderId) {
        return riderRepository.findById(riderId).orElseThrow(() -> new ServiceException(INVALID_RIDE));
    }

    @Override
    public Match match(String riderId) {

        Rider rider = this.findById(riderId);

        List<String> driverIds = driverRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Function.identity(),
                                        driver -> Utils.distanceCalculator(rider.getLocation(), driver.getLocation())))
                .entrySet()
                .stream()
                .filter(entry -> DriverStatus.AVAILABLE.equals(entry.getKey().getAvailabilityStatus()))
                .filter(entry -> entry.getValue() <= MAX_DRIVERS_ALLOWED)
                .sorted(getDistanceDriverComparator())
                .map(Map.Entry::getKey)
                .map(Driver::getId)
                .collect(Collectors.toCollection(LinkedList::new));

        return new Match(driverIds);
    }

    private static Comparator<Map.Entry<Driver, Double>> getDistanceDriverComparator() {
        return (o1, o2) -> {
            if (o1.getValue() > o2.getValue()) {
                return ONE;
            } else if (o1.getValue() < o2.getValue()) {
                return -ONE;
            }

            return o1.getKey().getId().compareTo(o2.getKey().getId()); // comparing by id
        };
    }

}
