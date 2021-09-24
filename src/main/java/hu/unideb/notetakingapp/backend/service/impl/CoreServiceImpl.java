package hu.unideb.notetakingapp.backend.service.impl;

import hu.unideb.notetakingapp.api.entity.BaseEntity;
import hu.unideb.notetakingapp.api.service.CoreService;
import hu.unideb.notetakingapp.backend.dao.BaseEntityRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class CoreServiceImpl<T extends BaseEntity> implements CoreService<T> {
    private final BaseEntityRepository<T> baseEntityRepository;

    public CoreServiceImpl(BaseEntityRepository<T> baseEntityRepository) {
        this.baseEntityRepository = baseEntityRepository;
    }

    @Override
    public Set<T> findAll() {
        Set<T> entities = new HashSet<>();
        for (T entity : baseEntityRepository.findAll()) {
            entities.add(entity);
        }
        return entities;
    }

    @Override
    public T findById(Long id) {
        Optional<T> optionalEntity = baseEntityRepository.findById(id);

        return optionalEntity.orElse(null);
    }

    @Override
    public T save(T entity) {
        return baseEntityRepository.save(entity);
    }

    @Override
    public void delete(T entity) {
        baseEntityRepository.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        baseEntityRepository.deleteById(id);
    }
}
