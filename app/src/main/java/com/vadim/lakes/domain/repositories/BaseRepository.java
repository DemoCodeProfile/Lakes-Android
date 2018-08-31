package com.vadim.lakes.domain.repositories;

import com.vadim.lakes.domain.specifications.BaseSpecification;

import java.util.List;

public interface BaseRepository<T> {
    void add(T item);
    void update(T item);
    void remove(T item);
    List<T> query(BaseSpecification specification);
}
