package com.hobby.webApp.services;

import com.hobby.webApp.entities.ActivationKey;
import com.hobby.webApp.entities.Product;
import com.hobby.webApp.entities.User;

import java.util.ArrayList;

public interface UserService {
    User register(String firstName, String lastName, int age, String email,
                  String password, boolean isAdmin, String[] roles, ArrayList<ActivationKey> keys, ArrayList<Product> products);
}
