package com.geektrust.backend.commandTest;

import com.geektrust.backend.command.impl.BillCommand;
import com.geektrust.backend.dto.Bill;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("BillCommandTest")
@ExtendWith(MockitoExtension.class)
public class BillCommandTest {
    @InjectMocks
    BillCommand billCommand;

    @Mock
    IRideService rideService;

    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }


    @DisplayName("Should print the bill in the console")
    @Test
    public void execute_printTheBill_InTheConsole() {

        // stub
        Bill bill = new Bill("RIDE-101", "D1", "123.45");
        when(rideService.getBillByRideId(eq("RIDE-101"))).thenReturn(bill);

        // expected
        String expectedOutput = "BILL RIDE-101 D1 123.45";

        // actual
        billCommand.execute(List.of("BILL", "RIDE-101"));

        // assert
        assertEquals(expectedOutput, outputStream.toString().trim());
        verify(rideService, times(1)).getBillByRideId(eq("RIDE-101"));
    }

    @AfterEach
    public void dispose() {
        System.setOut(standardOutput);
    }

}
