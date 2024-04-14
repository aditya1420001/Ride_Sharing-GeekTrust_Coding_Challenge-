package com.geektrust.backend.command.impl;

import com.geektrust.backend.command.ICommand;
import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.service.IDriverService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.geektrust.backend.constants.Constants.*;

@RequiredArgsConstructor
public class AddDriverCommand implements ICommand {


    private final IDriverService driverService;

    @Override
    public void execute(List<String> arguments) {

        final String driverId = arguments.get(ONE);
        final Double x_coordinate = Double.parseDouble(arguments.get(TWO));
        final Double y_coordinate = Double.parseDouble(arguments.get(THREE));

        driverService.load(driverId, new Coordinates(x_coordinate, y_coordinate));

    }
}
