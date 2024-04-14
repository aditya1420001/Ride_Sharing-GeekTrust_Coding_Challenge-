package com.geektrust.backend.commandTest;

import com.geektrust.backend.command.impl.StopRideCommand;
import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.service.IRideService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@DisplayName("StopRideCommandTest")
@ExtendWith(MockitoExtension.class)
public class StopRideCommandTest {

    @InjectMocks
    StopRideCommand stopRideCommand;

    @Mock
    IRideService rideService;

    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }


    @Test
    @DisplayName("Testing")
    public void execute_StopRideCommand_ShouldPrintInConsole() {

        // expected
        String expectedOutput = "RIDE_STOPPED RIDE-001";

        // actual
        stopRideCommand.execute(List.of("STOP_RIDE", "RIDE-001", "4", "5", "32"));

        // assert
        assertEquals(expectedOutput, outputStream.toString().trim());
        Mockito.verify(rideService, Mockito.times(1)).stopRide(anyString(), any(Coordinates.class), any(Long.class));
    }

    @AfterEach
    public void dispose() {
        System.setOut(standardOutput);
    }


}
