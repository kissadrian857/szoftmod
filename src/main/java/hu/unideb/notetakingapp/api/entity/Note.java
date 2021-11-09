package hu.unideb.notetakingapp.api.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Note extends BaseEntity {
    @Column(name = "body", length = 600)
    private String body;

    @Column(name = "title")
    private String title;

    @Column(name = "date_of_creation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(name = "date_of_last_modification")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastModifiedDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creatorUser;

    @Column(name = "credit_value")
    private Integer creditValue;

    @Column(name = "summary")
    private String summary;

    @Column(name = "topic_id")
    private Long topicId;

    //
    public Integer getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(Integer creditValue) {
        this.creditValue = creditValue;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Note(String body, String title, LocalDate creationDate, LocalDate lastModifiedDate, User creatorUser) {
        this.body = body;
        this.title = title;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
        this.creatorUser = creatorUser;
    }

    public Note() {
    }
}
