package com.hobby.webApp.controllers;

import com.hobby.webApp.dto.CreateRegisterForm;
import com.hobby.webApp.entities.ActivationKey;
import com.hobby.webApp.entities.User;
import com.hobby.webApp.repositories.ActivationKeyRepo;
import com.hobby.webApp.repositories.UserRepo;
import com.hobby.webApp.services.ActivationKeyService;
import com.hobby.webApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Controller
public class RegisterControl {
    private final UserRepo userRepo;
    private final UserService userService;
    private final ActivationKeyService activationKeyService;
    private final ActivationKeyRepo activationKeyRepo;

    @Autowired
    public RegisterControl(UserRepo userRepo, UserService userService, ActivationKeyService activationKeyService, ActivationKeyRepo activationKeyRepo) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.activationKeyService = activationKeyService;
        this.activationKeyRepo = activationKeyRepo;
    }

    @GetMapping("register/form")
    public String getRegisterForm(Model model) {
        model.addAttribute("form", new CreateRegisterForm());
        return "register";
    }

    @GetMapping("user/{fullName}")
    public String getUserView(@PathVariable(name = "fullName") String firstName, String lastName, Model model) {
        User user = userRepo.findByFirstNameAndLastName(firstName, lastName).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("user", user);
        return "userPage";
    }

    @PostMapping("register/process")
    public String formRegisterProcess(@Valid @ModelAttribute("form") CreateRegisterForm form, BindingResult bindingResult){
        if(userRepo.findByEmail(form.getEmail()).isPresent()) {
            FieldError error = new FieldError("form", "email", "Email is already in use");
            bindingResult.addError(error);
        } else if (!form.getPassword().equals(form.getPasswordConfirm())) {
            FieldError error = new FieldError("form", "passwordConfirm", "Passwords don't match");
            bindingResult.addError(error);
        }
        if(bindingResult.hasErrors()) {
            return "register";
        }

        ArrayList<ActivationKey> activationKeys = new ArrayList<>(
                Collections.singletonList(activationKeyService.register()));
        String[] roles = new String[]{"", "USER"};

        User newUser = userService.register(form.getFirstName(), form.getLastName(),
                Integer.parseInt(form.getAge()), form.getEmail(), form.getPassword(), form.isAdmin(), activationKeys, roles);

        activationKeyService.update(userRepo.findByEmail(newUser.getEmail()).get());

        if(form.isAdmin()) {
            return "redirect:/user/"+newUser.getFirstName();
        } else {
            return "redirect:/login/form";
        }
    }
}
