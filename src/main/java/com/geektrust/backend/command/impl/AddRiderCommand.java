package com.geektrust.backend.command.impl;

import com.geektrust.backend.command.ICommand;
import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.service.IRiderService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.geektrust.backend.constants.Constants.*;

@RequiredArgsConstructor
public class AddRiderCommand implements ICommand {

    private final IRiderService riderService;

    @Override
    public void execute(List<String> arguments) {
        final String riderId = arguments.get(ONE);
        final Double x_coordinate = Double.parseDouble(arguments.get(TWO));
        final Double y_coordinate = Double.parseDouble(arguments.get(THREE));

        riderService.load(riderId, new Coordinates(x_coordinate, y_coordinate));
    }
}
