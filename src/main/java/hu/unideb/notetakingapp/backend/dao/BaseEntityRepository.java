package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T,Long> {
}
