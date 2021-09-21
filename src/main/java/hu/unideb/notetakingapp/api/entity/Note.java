package hu.unideb.notetakingapp.api.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
public class Note extends BaseEntity {
    @Column(name = "body")
    private String body;

    @Column(name = "date_of_creation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name = "date_of_last_modification")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creatorUser;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User getCreatorUser() {
        return creatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        this.creatorUser = creatorUser;
    }

    public Note(String body, LocalDate creationDate, LocalDate lastModifiedDate, User creatorUser) {
        this.body = body;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.creatorUser = creatorUser;
    }

    public Note() {
    }
}
