package com.hobby.webApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexControl {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
}

