package com.geektrust.backend.repository.impl;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.enums.DriverStatus;
import com.geektrust.backend.repository.IDriverRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DriverRepositoryImpl implements IDriverRepository {

    private final Map<String, Driver> driverMap;

    public DriverRepositoryImpl() {
        this.driverMap = new HashMap<>();
    }
    public DriverRepositoryImpl(Map<String, Driver> driverMap) {
        this.driverMap = driverMap;
    }

    @Override
    public Driver save(Driver entity) {
        driverMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Driver> findAll() {
        return new ArrayList<>(driverMap.values());
    }

    @Override
    public Optional<Driver> findById(String id) {
        return Optional.ofNullable(driverMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return Objects.nonNull(driverMap.get(id));
    }

    public List<Driver> findAllAvailableDrivers() {
        return this.findAll().stream()
                .filter(driver -> DriverStatus.AVAILABLE.equals(driver.getAvailabilityStatus()))
                .collect(Collectors.toList());
    }
}
