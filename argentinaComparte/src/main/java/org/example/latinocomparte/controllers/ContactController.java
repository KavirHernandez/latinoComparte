package org.example.latinocomparte.controllers;

import org.example.latinocomparte.entities.ContactEntity;
import org.example.latinocomparte.entities.UserEntity;
import org.example.latinocomparte.models.servicies.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/contactos")
public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping
    public String viewContacts(Model model,
                               @RequestParam(required = false) String nombreContacto,
                               @RequestParam(required = false) String correo,
                               @RequestParam(required = false) String telefono,
                               @RequestParam(required = false) ContactEntity.Finalidad finalidad,
                               @RequestParam(required = false) Long idUsuario) {

        // Carga el listado filtrado de solicitudes para la vista admin
        List<ContactEntity> contacts = contactService.filterSolicitudes(nombreContacto, correo, telefono, finalidad, idUsuario);

        // Ordena por ID ascendente para una presentación cronológica
        contacts.sort(Comparator.comparing(ContactEntity::getId));

        model.addAttribute("activeMenu", "contactos");
        model.addAttribute("contactos", contacts);

        // Formulario vacío para el usuario que quiere enviar una solicitud
        model.addAttribute("nuevasSolicitud", new ContactEntity());

        return "contacts/listContacts";
    }


    @PostMapping("/enviar")
    public String enviarSolicitud(@ModelAttribute("nuevasSolicitud") ContactEntity contacto,
                                  HttpSession session) {

        // Asigna la fecha y hora actual como fecha de creación
        contacto.setFechaCreacion(LocalDateTime.now());

        // Si hay un usuario logueado (admin o invitado registrado), lo asocia
        Object usuarioObj = session.getAttribute("usuario");
        if (usuarioObj != null) {
            UserEntity usuario = (UserEntity) usuarioObj;
            // Solo asocia si el usuario tiene ID (existe en BD); los invitados no tienen ID
            if (usuario.getIdUser() != null) {
                contacto.setUsuario(usuario);
            }
        }

        // Guarda la solicitud en la base de datos
        contactService.save(contacto);

        // Redirige con mensaje de éxito
        return "redirect:/contactos?enviado=true";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        // Solo el administrador puede eliminar solicitudes
        if (esInvitado(session)) {
            return "redirect:/contactos?accesoDenegado=true";
        }
        contactService.delete(id);
        return "redirect:/contactos";
    }

    private boolean esInvitado(HttpSession session) {
        Object usuarioObj = session.getAttribute("usuario");
        if (usuarioObj == null) return true;
        UserEntity usuario = (UserEntity) usuarioObj;
        return usuario.getRol() != null && "INVITADO".equals(usuario.getRol().getRol());
    }
}
