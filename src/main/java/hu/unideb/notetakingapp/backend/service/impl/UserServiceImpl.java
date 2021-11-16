package hu.unideb.notetakingapp.backend.service.impl;

import hu.unideb.notetakingapp.api.entity.User;
import hu.unideb.notetakingapp.api.service.UserService;
import hu.unideb.notetakingapp.backend.dao.BaseEntityRepository;
import hu.unideb.notetakingapp.backend.dao.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends CoreServiceImpl<User> implements UserService {
    public UserServiceImpl(BaseEntityRepository<User> baseEntityRepository) {
        super(baseEntityRepository);
    }

    @Override
    public User findByUsername(String username) {
        User u = ((UserRepository)baseEntityRepository).findByUsername(username).orElse(null);
        return u;
    }

    @Override
    public User save(User entity) {
        Optional<User> optionalUser = ((UserRepository)baseEntityRepository).findByUsername(entity.getUserName());
        if (optionalUser.isPresent()){
            return null;
        }
        return super.save(entity);
    }
}
