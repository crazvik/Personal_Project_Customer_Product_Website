package com.hobby.webApp.controllers;

import com.hobby.webApp.dto.CreateLoginForm;
import com.hobby.webApp.dto.CreateRegisterForm;
import com.hobby.webApp.repositories.UserRepo;
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

@Controller
public class LoginControl {
    private final UserRepo userRepo;

    @Autowired
    public LoginControl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/login/form")
    public String getRegisterForm(Model model) {
        model.addAttribute("form", new CreateLoginForm());
        return "login";
    }

    @PostMapping("/login/process")
    public String formRegisterProcess(@Valid @ModelAttribute("form") CreateRegisterForm form, BindingResult bindingResult) {
        if (!userRepo.findByEmail(form.getEmail()).isPresent()) {
            FieldError error = new FieldError("form", "email", "Email not registered");
            bindingResult.addError(error);
        } else if (!userRepo.findByPassword(form.getPassword()).isPresent()) {
            FieldError error = new FieldError("form", "passwordConfirm", "Wrong password");
            bindingResult.addError(error);
        }
        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "redirect:form";
        }

        if(form.isAdmin()) {
            return "redirect:/user/"+userRepo.findByEmail(form.getEmail());
        } else {
            return "redirect:index";
        }
    }
}
