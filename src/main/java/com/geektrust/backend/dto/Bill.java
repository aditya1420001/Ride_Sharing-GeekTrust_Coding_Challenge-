package com.geektrust.backend.dto;

import lombok.Data;

@Data
public class Bill {
    private final String rideId;
    private final String driverId;
    private final String totalFare;

    @Override
    public String toString() {
        return String.format("BILL %s %s %s", rideId, driverId, totalFare);
    }

}
