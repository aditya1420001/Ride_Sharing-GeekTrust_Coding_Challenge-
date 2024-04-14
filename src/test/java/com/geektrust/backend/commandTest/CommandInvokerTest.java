package com.geektrust.backend.commandTest;


import com.geektrust.backend.command.CommandInvoker;
import com.geektrust.backend.command.impl.AddDriverCommand;
import com.geektrust.backend.command.impl.AddRiderCommand;
import com.geektrust.backend.command.impl.BillCommand;
import com.geektrust.backend.command.impl.MatchCommand;
import com.geektrust.backend.command.impl.StartRideCommand;
import com.geektrust.backend.command.impl.StopRideCommand;
import com.geektrust.backend.exception.NoSuchCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;

@DisplayName("CommandInvokerTest")
@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {
    private CommandInvoker commandInvoker;
    @Mock
    AddDriverCommand driverCommand;
    @Mock
    AddRiderCommand riderCommand;

    @Mock
    BillCommand billCommand;

    @Mock
    MatchCommand matchCommand;

    @Mock
    StartRideCommand startRideCommand;

    @Mock
    StopRideCommand stopRideCommand;

    @BeforeEach
    void setup(){
        commandInvoker = new CommandInvoker();

        commandInvoker.registerCommand("ADD_DRIVER", driverCommand);
        commandInvoker.registerCommand("ADD_RIDER", riderCommand);
        commandInvoker.registerCommand("MATCH", matchCommand);
        commandInvoker.registerCommand("START_RIDE", startRideCommand);
        commandInvoker.registerCommand("STOP_RIDE", stopRideCommand);
        commandInvoker.registerCommand("BILL", billCommand);
    }

    @Test
    @DisplayName("executeCommand when in positive case should execute the command")
    public void executeCommand_PositiveCase_ExecutesCommand() {
        commandInvoker.executeCommand("ADD_DRIVER",anyList());
        commandInvoker.executeCommand("ADD_RIDER",anyList());
        commandInvoker.executeCommand("MATCH",anyList());
        commandInvoker.executeCommand("START_RIDE",anyList());
        commandInvoker.executeCommand("STOP_RIDE",anyList());
        commandInvoker.executeCommand("BILL",anyList());
    }

    @Test
    @DisplayName("executeCommand when in negative case i.e,. if incorrect command or the tokens, then it must throw an exception")
    public void executeCommand_NegativeCase_ThrowsException() {
        assertThrows(NoSuchCommandException.class,() -> commandInvoker.executeCommand("RANDOM_COMMAND",new ArrayList<>()));
    }
}
