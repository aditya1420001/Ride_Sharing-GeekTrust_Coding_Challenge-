package com.geektrust.backend.command.impl;

import com.geektrust.backend.command.ICommand;
import com.geektrust.backend.service.IRideService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.geektrust.backend.constants.Constants.*;

@RequiredArgsConstructor
public class StartRideCommand implements ICommand {

    private final IRideService rideService;
    private static final String RIDE_STARTED = "RIDE_STARTED ";

    @Override
    public void execute(List<String> arguments) {
        final String rideId = arguments.get(ONE);
        final Integer nthDriver = Integer.parseInt(arguments.get(TWO));
        final String riderId = arguments.get(THREE);

        try {
            rideService.startRide(rideId, nthDriver, riderId);
            System.out.println(RIDE_STARTED.concat(rideId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
