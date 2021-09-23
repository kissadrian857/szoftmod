package hu.unideb.notetakingapp.api.service;

import hu.unideb.notetakingapp.api.entity.User;

public interface UserService extends CoreService<User, Long> {
    User findByUsername(String username);
}
