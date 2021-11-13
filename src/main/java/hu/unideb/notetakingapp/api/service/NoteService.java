package hu.unideb.notetakingapp.api.service;

import hu.unideb.notetakingapp.api.entity.Note;

import java.util.List;

public interface NoteService extends CoreService<Note> {
    List<Note> findNotesExceptId(Long id);

    List<Note> findNotesWithCreatorId(Long id);

    List<Note> getFreeNotes();
}
