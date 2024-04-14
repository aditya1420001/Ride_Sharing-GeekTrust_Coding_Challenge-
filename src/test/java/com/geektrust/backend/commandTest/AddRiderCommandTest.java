package com.geektrust.backend.commandTest;

import com.geektrust.backend.command.impl.AddRiderCommand;
import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.service.IRiderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("AddRiderCommandTest")
@ExtendWith(MockitoExtension.class)
public class AddRiderCommandTest {
    @InjectMocks
    AddRiderCommand addRiderCommand;
    @Mock
    IRiderService riderService;

    @Test
    @DisplayName("Add Rider Test")
    public void executing_AddRider() {

        Rider rider = new Rider("R1", new Coordinates(3.0D, 5.0D));

        when(riderService.load("R1", new Coordinates(3.0D, 5.0D))).thenReturn(rider);

        addRiderCommand.execute(Arrays.asList("ADD_RIDER", "R1", "3", "5"));

        verify(riderService, times(1)).load(eq("R1"), any(Coordinates.class));
    }

}
