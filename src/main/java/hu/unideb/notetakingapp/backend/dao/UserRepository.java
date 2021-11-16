package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends BaseEntityRepository<User>{

    @Query(value = "SELECT u FROM User u WHERE u.userName = :username")
    Optional<User> findByUsername(@Param("username") String username);
}
