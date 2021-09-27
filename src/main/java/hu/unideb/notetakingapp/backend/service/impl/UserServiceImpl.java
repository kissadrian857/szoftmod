package hu.unideb.notetakingapp.backend.service.impl;

import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.UserService;
import hu.unideb.notetakingapp.backend.dao.BaseEntityRepository;
import hu.unideb.notetakingapp.backend.dao.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends CoreServiceImpl<User> implements UserService {
    public UserServiceImpl(BaseEntityRepository<User> baseEntityRepository) {
        super(baseEntityRepository);
    }

    @Override
    public User findByUsername(String username) {
        User u = ((UserRepository)baseEntityRepository).findByUsername(username);
        return u;
    }
}
