package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.Shared;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SharedRepository extends BaseEntityRepository<Shared> {
    @Query(value = "SELECT s from Shared s WHERE s.userId= :user_id")
    List<Shared> findByUserId(@Param("user_id") Long userId);

    @Query(value = "SELECT s from Shared s WHERE s.noteId= :note_id")
    List<Shared> findByNoteId(@Param("note_id") Long noteId);
}
