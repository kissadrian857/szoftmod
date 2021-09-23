package hu.unideb.notetakingapp.api.service;

import hu.unideb.notetakingapp.api.entity.BaseEntity;

import java.util.Set;

public interface CoreService<T extends BaseEntity, ID> {
    Set<T> findAll();

    T findById(ID id);

    T save(T entity);

    void delete(T entity);

    void deleteById(ID id);
}
