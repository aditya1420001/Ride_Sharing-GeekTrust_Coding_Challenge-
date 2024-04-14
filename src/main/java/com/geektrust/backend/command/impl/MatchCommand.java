package com.geektrust.backend.command.impl;

import com.geektrust.backend.command.ICommand;
import com.geektrust.backend.dto.Match;
import com.geektrust.backend.service.IRiderService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MatchCommand implements ICommand {

    private final IRiderService riderService;

    @Override
    public void execute(List<String> arguments) {

        final String riderId = arguments.get(1);
        try {
            Match match = riderService.match(riderId);
            System.out.println(match);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
