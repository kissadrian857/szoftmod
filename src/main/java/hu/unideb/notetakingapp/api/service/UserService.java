package hu.unideb.notetakingapp.api.service;

import hu.unideb.notetakingapp.api.entity.User;

public interface UserService extends CoreService<User> {
    User findByUsername(String username);
}
