package org.example.latinocomparte.controllers;

import org.example.latinocomparte.models.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model){
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam (required = false) String nombre,
                        @RequestParam (required = false) String contrasena,
                        Model model) {

        // ejemplo
        if ("admin".equals(nombre) && "1234".equals(contrasena)) {
            return "redirect:/home";
        }

        model.addAttribute("error", "User and password incorrect");
        return "auth/login";
    }
}
