package com.geektrust.backend.service;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.dto.Match;
import com.geektrust.backend.entities.Rider;

public interface IRiderService {
    Rider load(String riderId, Coordinates location);
    Rider findById(String riderId);
    Match match(String riderId);
}
