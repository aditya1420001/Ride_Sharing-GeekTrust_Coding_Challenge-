package com.geektrust.backend.entities;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.enums.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Driver {
    private String id;
    private Coordinates location;
    private DriverStatus availabilityStatus;
}
