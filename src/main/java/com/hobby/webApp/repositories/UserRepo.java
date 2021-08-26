package com.hobby.webApp.repositories;

import com.hobby.webApp.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}
