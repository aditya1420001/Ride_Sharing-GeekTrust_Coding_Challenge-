package com.geektrust.backend.command.impl;

import com.geektrust.backend.command.ICommand;
import com.geektrust.backend.dto.Bill;
import com.geektrust.backend.service.IRideService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BillCommand implements ICommand {

    private final IRideService rideService;

    @Override
    public void execute(List<String> arguments) {

        final String rideId = arguments.get(1);

        try {
            Bill bill = rideService.getBillByRideId(rideId);
            System.out.println(bill);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
