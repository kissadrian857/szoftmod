package hu.unideb.notetakingapp.backend.service.impl;

import hu.unideb.notetakingapp.api.entity.Note;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.backend.dao.BaseEntityRepository;
import hu.unideb.notetakingapp.backend.dao.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl extends CoreServiceImpl<Note> implements NoteService {
    public NoteServiceImpl(BaseEntityRepository<Note> baseEntityRepository) {
        super(baseEntityRepository);
    }

    public List<Note> findNotesExceptId(Long id) {
        return ((NoteRepository) baseEntityRepository).findNotesExceptId(id);
    }

    @Override
    public List<Note> getFreeNotes() {
        return ((NoteRepository)baseEntityRepository).getFreeNotes();
    }
}
