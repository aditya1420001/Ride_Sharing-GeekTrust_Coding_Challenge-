package com.geektrust.backend.commandTest;

import com.geektrust.backend.command.impl.AddDriverCommand;
import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.enums.DriverStatus;
import com.geektrust.backend.service.IDriverService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("AddDriverCommandTest")
@ExtendWith(MockitoExtension.class)
public class AddDriverCommandTest {

    @InjectMocks
    AddDriverCommand addDriverCommand;
    @Mock
    IDriverService driverService;

    @Test
    @DisplayName("Add Driver test")
    public void execute_addDriverCommand() {

        Driver driver = new Driver("D1", new Coordinates(1.0D, 3.0D), DriverStatus.AVAILABLE);

        when(driverService.load("D1", new Coordinates(1.0D, 3.0D))).thenReturn(driver);

        addDriverCommand.execute(List.of("ADD_DRIVER", "D1", "1", "3"));

        verify(driverService, times(1)).load(eq("D1"), any(Coordinates.class));
    }
}
