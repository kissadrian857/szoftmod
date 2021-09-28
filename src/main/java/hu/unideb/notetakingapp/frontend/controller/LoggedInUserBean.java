package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.NoteService;
import hu.unideb.notetakingapp.api.service.UserService;
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

    private final UserService userService;

    public LoggedInUserBean(UserService userService) {
        this.userService = userService;
    }

    public boolean isLoggedIn() {
        if (loggedInUser == null)
            return false;
        if (userService.findByUsername(loggedInUser.getUserName()) == null)
            return false;
        else
            return userService.findByUsername(loggedInUser.getUserName())
                    .getPasswordHash().equals(loggedInUser.getPasswordHash());
    }
}
