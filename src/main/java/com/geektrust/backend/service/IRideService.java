package com.geektrust.backend.service;

import com.geektrust.backend.dto.Bill;
import com.geektrust.backend.dto.Coordinates;

public interface IRideService {
    void startRide(String rideId, Integer NthDriver, String riderId);
    void stopRide(String rideId, Coordinates coordinates, Long rideDuration);
    Bill getBillByRideId(String rideId);
}
