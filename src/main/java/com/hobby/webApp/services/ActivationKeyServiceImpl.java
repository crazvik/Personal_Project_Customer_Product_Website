package com.hobby.webApp.services;

import com.hobby.webApp.entities.ActivationKey;
import com.hobby.webApp.entities.User;
import com.hobby.webApp.repositories.ActivationKeyRepo;
import com.hobby.webApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ActivationKeyServiceImpl implements ActivationKeyService {
    private final ActivationKeyRepo activationKeyRepo;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ActivationKeyServiceImpl(ActivationKeyRepo activationKeyRepo, UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.activationKeyRepo = activationKeyRepo;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public ActivationKey register() {
        int leftLimit = 48, rightLimit = 122, length = 12;
        Random random = new Random();
        ActivationKey newActivationKey = new ActivationKey(
            random.ints(leftLimit, rightLimit+1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(length)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString(), null
        );
        ActivationKey encryptedActivationKey = new ActivationKey(newActivationKey.getKey(), null);
        encryptedActivationKey.setKey(bCryptPasswordEncoder.encode(newActivationKey.getKey()));
        activationKeyRepo.save(encryptedActivationKey);
        return newActivationKey;
    }

    @Override
    public ActivationKey update(User user) {
        ActivationKey tempKey = activationKeyRepo.findAll().get(activationKeyRepo.findAll().size()-1);
        tempKey.setOwner(user);
        return activationKeyRepo.save(tempKey);
    }
}