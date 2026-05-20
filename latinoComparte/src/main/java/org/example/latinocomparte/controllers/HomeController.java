package org.example.latinocomparte.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(Model model) {
        // Activa el resaltado del menú "INICIO" en el navbar
        model.addAttribute("activeMenu", "home");
        return "home/home";
    }

    @GetMapping("/nuestra-historia")
    public String historia(Model model) {
        model.addAttribute("activeMenu", "historia");
        return "home/historia";
    }

    @GetMapping("/programa-edifica")
    public String edifica(Model model) {
        model.addAttribute("activeMenu", "programas");
        return "home/edifica";
    }

    @GetMapping("/top-speakers")
    public String topSpeakers(Model model) {
        model.addAttribute("activeMenu", "programas");
        return "home/topSpeakers";
    }

    @GetMapping("/nodus")
    public String nodus(Model model) {
        model.addAttribute("activeMenu", "programas");
        return "home/nodus";
    }
}
