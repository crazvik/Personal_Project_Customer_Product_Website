package com.hobby.webApp.services;

import com.hobby.webApp.entities.ActivationKey;
import com.hobby.webApp.entities.Product;
import com.hobby.webApp.entities.User;
import com.hobby.webApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(String firstName, String lastName, int age, String email, String password,
                         boolean isAdmin, String[] roles, ArrayList<ActivationKey> keys, ArrayList<Product> products) {
        User newUser = new User(firstName, lastName, age, email, bCryptPasswordEncoder.encode(password), isAdmin, roles, keys, products);
        return userRepo.save(newUser);
    }
}
