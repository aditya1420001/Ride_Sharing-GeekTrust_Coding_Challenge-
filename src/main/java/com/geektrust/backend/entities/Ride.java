package com.geektrust.backend.entities;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.enums.RideStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Ride {

    public Ride (String id, String riderId, String driverId, RideStatus rideStatus) {
        this.id = id;
        this.riderId = riderId;
        this.driverId = driverId;
        this.rideStatus = rideStatus;
        this.destinationLocation = null;
        this.totalFare = null;
    }

    private String id;
    private String riderId;
    private String driverId;
    private RideStatus rideStatus;
    private Coordinates destinationLocation;
    private Long rideDuration;
    private BigDecimal totalFare;
}
