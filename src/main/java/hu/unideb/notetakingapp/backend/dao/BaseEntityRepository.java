package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface BaseEntityRepository<T extends BaseEntity> extends CrudRepository<T,Long> {
}
