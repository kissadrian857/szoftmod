package hu.unideb.notetakingapp.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Shared extends BaseEntity {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "note_id")
    private Long noteId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Shared() {
    }

    public Shared(Long userId, Long noteId) {
        this.userId = userId;
        this.noteId = noteId;
    }
}

