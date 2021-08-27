package com.hobby.webApp.repositories;

import com.hobby.webApp.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<User> findByPassword(String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByKeys(String keys);
}
