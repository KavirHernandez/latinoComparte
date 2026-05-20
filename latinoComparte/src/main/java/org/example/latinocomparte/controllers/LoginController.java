package org.example.latinocomparte.controllers;

import org.example.latinocomparte.entities.RoleEntity;
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
        // Si ya hay sesión activa, no es necesario volver a loguearse
        if (session.getAttribute("usuario") != null) {
            return "redirect:/";
        }
        return "auth/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("contrasena") String contrasena,
                        Model model,
                        HttpSession session) {

        // Busca el usuario por su correo electrónico
        UserEntity user = userService.findByEmail(email);
        user.getRol().getRol();

        // Verificación 1: el usuario debe existir en la base de datos
        if (user == null) {
            model.addAttribute("error", "Usuario no encontrado");
            return "auth/login";
        }

        // Verificación 2: el usuario debe estar activo (no deshabilitado)
        if (!user.isEstadoUsu()) {
            model.addAttribute("error", "Usuario desactivado");
            return "auth/login";
        }

        // Verificación 3: la contraseña debe coincidir
        if (!user.getContrasena().equals(contrasena)) {
            model.addAttribute("error", "Contraseña incorrecta");
            return "auth/login";
        }

        // Login exitoso: guarda el usuario en la sesión HTTP
        session.setAttribute("usuario", user);

        return "redirect:/";
    }

    @GetMapping("/login/invitado")
    public String loginComoInvitado(HttpSession session) {
        // Crea un rol de invitado de forma temporal (sin persistirlo en BD)
        RoleEntity rolInvitado = new RoleEntity("INVITADO");

        // Crea el usuario invitado temporal con datos ficticios
        UserEntity invitado = new UserEntity();
        invitado.setNombre("Invitado");
        invitado.setEmail("invitado@argentinacomparte.com");
        invitado.setRol(rolInvitado);
        invitado.setEstadoUsu(true);

        // Guarda el invitado en sesión igual que un usuario normal
        session.setAttribute("usuario", invitado);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Elimina todos los atributos de sesión
        return "redirect:/login";
    }
}
