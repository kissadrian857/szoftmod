package hu.unideb.notetakingapp.backend.dao;

import hu.unideb.notetakingapp.api.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends BaseEntityRepository<User>{
    @Query(value = "SELECT u FROM User u WHERE u.userName = :username")
    User findByUsername(@Param("username") String username);
}
