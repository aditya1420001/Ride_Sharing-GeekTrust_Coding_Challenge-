package com.geektrust.backend.service;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.entities.Driver;

public interface IDriverService {
    Driver load(String driverId, Coordinates location);
    Driver findById(String id);
}
