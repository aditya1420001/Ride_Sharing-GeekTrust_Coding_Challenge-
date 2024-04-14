package com.geektrust.backend.commandTest;

import com.geektrust.backend.command.impl.MatchCommand;
import com.geektrust.backend.dto.Match;
import com.geektrust.backend.service.IRiderService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("MatchCommandTest")
@ExtendWith(MockitoExtension.class)
public class MatchCommandTest {
    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    @InjectMocks
    MatchCommand matchCommand;
    @Mock
    IRiderService riderService;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    @DisplayName("When riders are available")
    public void match_WhenDriversAreAvailable_PositiveCase() {
        // stub
        when(riderService.match(eq("R1"))).thenReturn(new Match(List.of("D1", "D3", "D2")));

        // expected
        String expectedOutput = "DRIVERS_MATCHED D1 D3 D2";

        // actual
        matchCommand.execute(List.of("MATCH", "R1"));

        // assert
        assertEquals(expectedOutput, outputStream.toString().trim());
        verify(riderService, times(1)).match(eq("R1"));
    }

    @Test
    @DisplayName("When riders are unavailable")
    public void match_WhenDriversAreUnavailable_NegativeCase() {
        // stub
        when(riderService.match(eq("R1"))).thenReturn(new Match(Collections.emptyList()));

        // expected
        String expectedOutput = "NO_DRIVERS_AVAILABLE";

        // actual
        matchCommand.execute(List.of("MATCH", "R1"));

        // assert
        assertEquals(expectedOutput, outputStream.toString().trim());
        verify(riderService, times(1)).match(eq("R1"));
    }


    @AfterEach
    public void dispose() {
        System.setOut(standardOutput);
    }

}
