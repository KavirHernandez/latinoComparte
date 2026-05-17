package org.example.latinocomparte.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    @GetMapping("/noticias")
    public String noticias(Model model) {
        model.addAttribute("activeMenu", "noticias");
        return "news/listNews";
    }
}
