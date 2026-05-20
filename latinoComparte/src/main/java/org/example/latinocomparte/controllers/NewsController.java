package org.example.latinocomparte.controllers;

import org.example.latinocomparte.entities.NewsEntity;
import org.example.latinocomparte.entities.UserEntity;
import org.example.latinocomparte.models.servicies.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;


@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/noticias")
    public String noticias(Model model, HttpSession session) {
        model.addAttribute("activeMenu", "noticias");

        // Los administradores ven todas las noticias
        if (esAdmin(session)) {
            model.addAttribute("noticias", newsService.listAll());
        } else {
            // Los invitados y usuarios normales solo ven las publicadas
            model.addAttribute("noticias", newsService.listPublicadas());
        }

        return "news/listNews";
    }

    @GetMapping("/noticias/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("activeMenu", "noticias");
        model.addAttribute("noticia", newsService.findById(id));
        return "news/detalleNews";
    }

    @GetMapping("/noticias/nuevo")
    public String newForm(Model model, HttpSession session) {
        if (esInvitado(session)) {
            return "redirect:/noticias?accesoDenegado=true";
        }
        model.addAttribute("activeMenu", "noticias");
        model.addAttribute("noticia", new NewsEntity());
        return "news/formNews";
    }

    @PostMapping("/noticias/guardar")
    public String save(@ModelAttribute NewsEntity noticia, HttpSession session) {
        if (esInvitado(session)) {
            return "redirect:/noticias?accesoDenegado=true";
        }

        // Asigna la fecha de publicación si no viene del formulario
        if (noticia.getFechaPublicacion() == null) {
            noticia.setFechaPublicacion(LocalDate.now());
        }

        // Asigna el usuario autenticado como autor de la noticia
        UserEntity usuarioActual = (UserEntity) session.getAttribute("usuario");
        noticia.setUsuarios(usuarioActual);

        // Valor por defecto: si no se especificó estado, pone BORRADOR
        if (noticia.getEstado() == null || noticia.getEstado().isBlank()) {
            noticia.setEstado("BORRADOR");
        }

        newsService.save(noticia);
        return "redirect:/noticias";
    }

    @GetMapping("/noticias/editar/{id}")
    public String editForm(@PathVariable Long id, Model model, HttpSession session) {
        if (esInvitado(session)) {
            return "redirect:/noticias?accesoDenegado=true";
        }
        model.addAttribute("activeMenu", "noticias");
        model.addAttribute("noticia", newsService.findById(id));
        return "news/formNews";
    }

    @PostMapping("/noticias/actualizar")
    public String update(@ModelAttribute NewsEntity noticia, HttpSession session) {
        if (esInvitado(session)) {
            return "redirect:/noticias?accesoDenegado=true";
        }

        // Preserva el usuario autor original de la noticia
        UserEntity usuarioActual = (UserEntity) session.getAttribute("usuario");
        noticia.setUsuarios(usuarioActual);

        newsService.save(noticia);
        return "redirect:/noticias";
    }

    @GetMapping("/noticias/eliminar/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        if (esInvitado(session)) {
            return "redirect:/noticias?accesoDenegado=true";
        }
        newsService.delete(id);
        return "redirect:/noticias";
    }


    private boolean esAdmin(HttpSession session) {
        Object usuarioObj = session.getAttribute("usuario");
        if (usuarioObj == null) return false;
        UserEntity usuario = (UserEntity) usuarioObj;
        return usuario.getRol() == null || !"INVITADO".equals(usuario.getRol().getRol());
    }

    private boolean esInvitado(HttpSession session) {
        Object usuarioObj = session.getAttribute("usuario");
        if (usuarioObj == null) return true;
        UserEntity usuario = (UserEntity) usuarioObj;
        return usuario.getRol() != null && "INVITADO".equals(usuario.getRol().getRol());
    }
}
