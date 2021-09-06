package com.hobby.webApp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CreateLoginForm {
    @NotEmpty(message = "Can't be empty.")
    private String email;

    @NotEmpty(message = "Can't be empty.")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
