package com.geektrust.backend.command;

import com.geektrust.backend.exception.NoSuchCommandException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CommandInvoker {
    private static final Map<String, ICommand> commandMap = new HashMap<>();

    public void registerCommand(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    public ICommand getCommand(String commandName) {
        return commandMap.get(commandName);
    }
    public void
    executeCommand(String commandName, List<String> arguments) {
        ICommand command = this.getCommand(commandName);

        if (Objects.isNull(command)) {
            throw new NoSuchCommandException("No such command");
        }

        command.execute(arguments);
    }

}
