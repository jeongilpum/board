package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class LoginController {

    @GetMapping
    public String getLoginPage() {
        return "forward:/index.html";
    }

    @GetMapping("/register")
    public String getregisterPage() {
        return "forward:/register.html";
    }

}
