package com.hobby.webApp.repositories;

import com.hobby.webApp.entities.ActivationKey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ActivationKeyRepo extends MongoRepository<ActivationKey, String> {
    Optional<ActivationKey> findByKey(String activationKey);
}
