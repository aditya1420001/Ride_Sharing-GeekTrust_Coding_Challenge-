package com.geektrust.backend.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, T> {
    T save(T entity);
    List<T> findAll();
    Optional<T> findById(ID id);
    boolean existsById (ID id);
}
