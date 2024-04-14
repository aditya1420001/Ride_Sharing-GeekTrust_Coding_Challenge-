package com.geektrust.backend.repository.impl;

import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.repository.IRideRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class RideRepositoryImpl implements IRideRepository {
    private final Map<String, Ride> rideMap;

    public RideRepositoryImpl() {
        this.rideMap = new HashMap<>();
    }
    public RideRepositoryImpl(Map<String, Ride> rideMap) {
        this.rideMap = rideMap;
    }

    @Override
    public Ride save(Ride entity) {
        rideMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Ride> findAll() {
        return new ArrayList<>(rideMap.values());
    }

    @Override
    public Optional<Ride> findById(String id) {
        return Optional.ofNullable(rideMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return Objects.nonNull(rideMap.get(id));
    }
}
