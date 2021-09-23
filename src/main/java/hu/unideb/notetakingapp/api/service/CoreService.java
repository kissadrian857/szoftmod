package hu.unideb.notetakingapp.api.service;

import java.util.Set;

public interface CoreService<T, ID> {
    Set<T> findAll();

    T findById(ID id);

    T save(T entity);

    void delete(T entity);

    void deleteById(ID id);
}
