package hu.unideb.notetakingapp.api.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Purchase extends BaseEntity {
    @Column(name = "note-id")
    private Long noteId;

    @Column(name = "customer-id")
    private Long customerId;

    @Column(name = "purchase-time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime purchaseTime;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "is-gifted")
    private Boolean isGifted;

    @Column(name = "creator-id")
    private Long creatorId;


    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Boolean getGifted() {
        return isGifted;
    }

    public void setGifted(Boolean gifted) {
        isGifted = gifted;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
