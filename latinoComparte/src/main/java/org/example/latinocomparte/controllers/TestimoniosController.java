package org.example.latinocomparte.controllers;

import org.example.latinocomparte.entities.TestimoniesEntity;
import org.example.latinocomparte.models.servicies.TestimoniesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/testimonios")
public class TestimoniosController {

    private final TestimoniesService testimoniesService;

    public TestimoniosController(TestimoniesService testimoniesService) {
        this.testimoniesService = testimoniesService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activeMenu", "testimonios");
        model.addAttribute("testimonios", testimoniesService.listAll());
        return "testimonials/list";
    }

    @GetMapping("/nuevo")
    public String newForm(Model model) {
        model.addAttribute("activeMenu", "testimonios");
        model.addAttribute("testimonio", new TestimoniesEntity());
        return "testimonials/form";
    }

    @PostMapping("/guardar")
    public String save(@ModelAttribute TestimoniesEntity testimonio) {
        if (testimonio.getFechaCreacion() == null) {
            testimonio.setFechaCreacion(LocalDateTime.now());
        }
        testimoniesService.save(testimonio);
        return "redirect:/testimonios";
    }

    @GetMapping("/editar/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "testimonios");
        model.addAttribute("testimonio", testimoniesService.findById(id));
        return "testimonials/form";
    }

    @PostMapping("/actualizar")
    public String update(@ModelAttribute TestimoniesEntity testimonio) {
        testimoniesService.save(testimonio);
        return "redirect:/testimonios";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable Long id) {
        testimoniesService.delete(id);
        return "redirect:/testimonios";
    }
}
