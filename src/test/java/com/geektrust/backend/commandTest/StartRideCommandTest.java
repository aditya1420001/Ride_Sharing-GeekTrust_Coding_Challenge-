package com.geektrust.backend.commandTest;

import com.geektrust.backend.command.impl.StartRideCommand;
import com.geektrust.backend.service.IRideService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("StartRideCommandTest")
@ExtendWith(MockitoExtension.class)
public class StartRideCommandTest {
    @InjectMocks
    StartRideCommand startRideCommand;
    @Mock
    IRideService rideService;

    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @DisplayName("start ride command test | should print the output when drivers are available")
    @Test
    public void execute_StartRideCommand() {

        // expected
        String expectedOutput = "RIDE_STARTED RIDE-101";

        // actual
        startRideCommand.execute(List.of("START_RIDE", "RIDE-101", "1",  "R1"));

        // assert
        assertEquals(expectedOutput, outputStream.toString().trim());
        verify(rideService, times(1)).startRide(anyString(), any(Integer.class), anyString());
    }

    @AfterEach
    public void dispose() {
        System.setOut(standardOutput);
    }

}
