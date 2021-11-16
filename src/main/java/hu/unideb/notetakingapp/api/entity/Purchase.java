package hu.unideb.notetakingapp.api.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Purchase extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "purchase_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime purchaseTime;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "is_gifted")
    private Boolean isGifted;

    @Column(name = "creator_id")
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

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
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
