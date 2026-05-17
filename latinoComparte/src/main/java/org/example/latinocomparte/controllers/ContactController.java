package org.example.latinocomparte.controllers;

import org.example.latinocomparte.entities.ContactEntity;
import org.example.latinocomparte.models.servicies.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/contactos")//indica que una clase o metodo va responder a solicitudes web
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public String viewContacts(Model model, @RequestParam(required = false) String nombreContacto,
                                            @RequestParam(required = false) String correo,
                                            @RequestParam(required = false) String telefono,
                                            @RequestParam(required = false) ContactEntity.Finalidad finalidad,
                                            @RequestParam(required = false) Long idUsuario){
        List<ContactEntity> contacts =contactService.filterSolicitudes(nombreContacto, correo, telefono, finalidad,idUsuario);
        contacts.sort(Comparator.comparing(ContactEntity::getId));//cambiar y ordenar todo
        model.addAttribute("activeMenu", "contactos");
        model.addAttribute("contactos", contacts);
        return "contacts/listContacts";

    }

}
