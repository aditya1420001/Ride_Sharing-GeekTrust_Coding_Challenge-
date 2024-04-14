package com.geektrust.backend.command.impl;

import com.geektrust.backend.command.ICommand;
import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.service.IRideService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.geektrust.backend.constants.Constants.*;


@RequiredArgsConstructor
public class StopRideCommand implements ICommand {

    private final IRideService rideService;

    public static final String RIDE_STOPPED = "RIDE_STOPPED ";

    @Override
    public void execute(List<String> arguments) {
        final String rideId = arguments.get(ONE);
        final Double x = Double.parseDouble(arguments.get(TWO));
        final Double y = Double.parseDouble(arguments.get(THREE));
        final Long rideDuration = Long.parseLong(arguments.get(FOUR));

        try {
            rideService.stopRide(rideId, new Coordinates(x, y), rideDuration);
            System.out.println(RIDE_STOPPED.concat(rideId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
