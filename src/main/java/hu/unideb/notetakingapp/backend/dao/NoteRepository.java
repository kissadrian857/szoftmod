package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends BaseEntityRepository<Note> {
    @Query(value = "SELECT n FROM Note n WHERE n.creatorUser.id <> :id")
    List<Note> findNotesExceptId(@Param("id") Long id);
}
