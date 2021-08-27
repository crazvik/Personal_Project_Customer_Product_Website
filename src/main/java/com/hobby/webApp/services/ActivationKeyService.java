package com.hobby.webApp.services;

import com.hobby.webApp.entities.ActivationKey;
import com.hobby.webApp.entities.User;

import java.util.Optional;

public interface ActivationKeyService {
    ActivationKey register();
    ActivationKey update(User user);
}
