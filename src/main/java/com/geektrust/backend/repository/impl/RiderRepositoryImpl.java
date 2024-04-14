package com.geektrust.backend.repository.impl;

import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repository.IRiderRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class RiderRepositoryImpl implements IRiderRepository {

    private final Map<String, Rider> riderMap;

    public RiderRepositoryImpl() {
        this.riderMap = new HashMap<>();
    }

    public RiderRepositoryImpl(Map<String, Rider> riderMap) {
        this.riderMap = riderMap;
    }

    @Override
    public Rider save(Rider entity) {
        riderMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Rider> findAll() {
        return new ArrayList<>(riderMap.values());
    }

    @Override
    public Optional<Rider> findById(String id) {
        return Optional.ofNullable(riderMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        return Objects.nonNull(riderMap.get(id));
    }
}
