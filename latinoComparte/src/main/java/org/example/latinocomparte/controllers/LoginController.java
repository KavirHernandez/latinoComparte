package org.example.latinocomparte.controllers;

import org.example.latinocomparte.entities.UserEntity;
import org.example.latinocomparte.models.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(HttpSession session) {

        if (session.getAttribute("usuario") != null) {
            return "redirect:/home";
        }

        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("contrasena") String contrasena,
                        Model model,
                        HttpSession session) {

        UserEntity user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "login";
        }

        if (!user.isEstadoUsu()) {
            model.addAttribute("error", "Usuario desactivado");
            return "login";
        }

        if (!user.getContrasena().equals(contrasena)) {
            model.addAttribute("error", "Contraseña incorrecta");
            return "login";
        }

        // ✅ GUARDAR USUARIO EN SESIÓN
        session.setAttribute("usuario", user);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // borra toda la sesión
        return "redirect:/login";
    }
}