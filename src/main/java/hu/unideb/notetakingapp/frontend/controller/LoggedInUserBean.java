package hu.unideb.notetakingapp.frontend.controller;

import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
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

    public boolean login(String username, String password) {
        if (username == null || password == null)
            return false;
        if (userService.findByUsername(username) == null)
            return false;
        if (userService.findByUsername(username).getPasswordHash().equals(password)) {
            loggedInUser = userService.findByUsername(username);
            return true;
        } else return false;
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }
}
