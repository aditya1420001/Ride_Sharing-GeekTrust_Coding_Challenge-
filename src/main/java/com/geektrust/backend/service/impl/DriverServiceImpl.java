package com.geektrust.backend.service.impl;

import com.geektrust.backend.dto.Coordinates;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.enums.DriverStatus;
import com.geektrust.backend.exception.ServiceException;
import com.geektrust.backend.repository.IDriverRepository;
import com.geektrust.backend.service.IDriverService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DriverServiceImpl implements IDriverService {

    private final IDriverRepository driverRepository;
    @Override
    public Driver load(String driverId, Coordinates location) {
        return driverRepository.save(new Driver(driverId, location, DriverStatus.AVAILABLE));
    }

    @Override
    public Driver findById(String id) {
        return driverRepository.findById(id).orElseThrow(() -> new ServiceException("Driver not found"));
    }
}
