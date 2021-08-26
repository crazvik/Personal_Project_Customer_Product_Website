package com.hobby.webApp.repositories;

import com.hobby.webApp.entities.ActivationKey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivationKeyRepo extends MongoRepository<ActivationKey, String> {
}
