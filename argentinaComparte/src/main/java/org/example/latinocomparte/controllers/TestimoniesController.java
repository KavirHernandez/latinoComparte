package org.example.latinocomparte.controllers;

import org.example.latinocomparte.entities.TestimoniesEntity;
import org.example.latinocomparte.entities.UserEntity;
import org.example.latinocomparte.models.servicies.TestimoniesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;


@Controller
@RequestMapping("/testimonios")
public class TestimoniesController {

    private final TestimoniesService testimoniesService;

    public TestimoniesController(TestimoniesService testimoniesService) {
        this.testimoniesService = testimoniesService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activeMenu", "testimonios");
        model.addAttribute("testimonios", testimoniesService.listAll());
        return "testimonies/listTestimonies";
    }

    @GetMapping("/nuevo")
    public String newForm(Model model, HttpSession session) {
        if (esInvitado(session)) {
            return "redirect:/testimonios?accesoDenegado=true";
        }
        model.addAttribute("activeMenu", "testimonios");
        model.addAttribute("testimonio", new TestimoniesEntity());
        return "testimonies/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute TestimoniesEntity testimonio, HttpSession session) {

        if (esInvitado(session)) {
            return "redirect:/testimonios?accesoDenegado=true";
        }

        UserEntity usuario = (UserEntity) session.getAttribute("usuario");
        testimonio.setUsuarios(usuario); // 🔥 IMPORTANTE

        if (testimonio.getFechaCreacion() == null) {
            testimonio.setFechaCreacion(LocalDate.now());
        }

        normalizarCampos(testimonio);

        testimoniesService.save(testimonio);
        return "redirect:/testimonios";
    }

    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Long id, Model model, HttpSession session) {

        if (esInvitado(session)) {
            return "redirect:/testimonios?accesoDenegado=true";
        }

        model.addAttribute("activeMenu", "testimonios");
        model.addAttribute("testimonio", testimoniesService.findById(id));
        return "testimonies/form";
    }

    @PostMapping("/actualizar")
    public String update(@ModelAttribute TestimoniesEntity testimonio, HttpSession session) {

        if (esInvitado(session)) {
            return "redirect:/testimonios?accesoDenegado=true";
        }

        UserEntity usuario = (UserEntity) session.getAttribute("usuario");
        testimonio.setUsuarios(usuario); // 🔥 CLAVE

        if (testimonio.getFechaCreacion() == null) {
            testimonio.setFechaCreacion(LocalDate.now());
        }

        normalizarCampos(testimonio);

        testimoniesService.save(testimonio);
        return "redirect:/testimonios";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {

        if (esInvitado(session)) {
            return "redirect:/testimonios?accesoDenegado=true";
        }

        testimoniesService.delete(id);
        return "redirect:/testimonios";
    }

    // 🔥 MÉTODO REUTILIZABLE (limpia campos vacíos)
    private void normalizarCampos(TestimoniesEntity testimonio) {

        if (testimonio.getInstagramUrl() != null && testimonio.getInstagramUrl().isBlank()) {
            testimonio.setInstagramUrl(null);
        }

        if (testimonio.getFacebookUrl() != null && testimonio.getFacebookUrl().isBlank()) {
            testimonio.setFacebookUrl(null);
        }

        if (testimonio.getImagenUrl() != null && testimonio.getImagenUrl().isBlank()) {
            testimonio.setImagenUrl(null);
        }
    }

    private boolean esInvitado(HttpSession session) {
        Object usuarioObj = session.getAttribute("usuario");
        if (usuarioObj == null) return true;

        UserEntity usuario = (UserEntity) usuarioObj;
        return usuario.getRol() != null && "INVITADO".equals(usuario.getRol().getRol());
    }
}