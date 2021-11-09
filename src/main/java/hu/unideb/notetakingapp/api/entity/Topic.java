package hu.unideb.notetakingapp.api.entity;

import javax.persistence.Column;

public class Topic extends BaseEntity {
    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
