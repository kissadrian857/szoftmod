package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends BaseEntityRepository<Note> {
}
