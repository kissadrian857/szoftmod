package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.User;
import org.springframework.stereotype.Service;

@Service
public class LoggedInUserBean {
    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
