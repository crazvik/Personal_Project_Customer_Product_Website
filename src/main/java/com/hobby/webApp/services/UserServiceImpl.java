package com.hobby.webApp.services;

import com.hobby.webApp.entities.ActivationKey;
import com.hobby.webApp.entities.User;
import com.hobby.webApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(String firstName, String lastName, int age, String email, String password, ArrayList<ActivationKey> keys) {
        User newUser = new User(firstName, lastName, age, email, password, keys);
        return userRepo.save(newUser);
    }
}
