package hu.unideb.notetakingapp.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class User extends BaseEntity {
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String passwordHash;

    @OneToMany(mappedBy = "creatorUser")
    private Set<Note> notes;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User(String userName, String passwordHash) {
        this.userName = userName;
        this.passwordHash = passwordHash;
    }

    public User() {
    }
}
