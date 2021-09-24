package hu.unideb.notetakingapp.backend.service.impl;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.backend.dao.BaseEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NoteServiceImpl extends CoreServiceImpl<Note> implements NoteService {
    public NoteServiceImpl(BaseEntityRepository<Note> baseEntityRepository) {
        super(baseEntityRepository);
    }
}
