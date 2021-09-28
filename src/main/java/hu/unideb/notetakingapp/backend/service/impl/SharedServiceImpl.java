package hu.unideb.notetakingapp.backend.service.impl;

import hu.unideb.notetakingapp.api.entity.Shared;
import hu.unideb.notetakingapp.api.service.SharedService;
import hu.unideb.notetakingapp.backend.dao.BaseEntityRepository;
import hu.unideb.notetakingapp.backend.dao.SharedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedServiceImpl extends  CoreServiceImpl<Shared> implements SharedService {
    public SharedServiceImpl(BaseEntityRepository<Shared> baseEntityRepository) {
        super(baseEntityRepository);
    }

    @Override
    public List<Shared> findByUserId(Long id) {
        List<Shared> records;
        records = ((SharedRepository)baseEntityRepository).findByUserId(id);
        return records;
    }

    @Override
    public List<Shared> findByNoteId(Long id) {
        List<Shared> records;
        records = ((SharedRepository) baseEntityRepository).findByNoteId(id);
        return records;
    }
}
