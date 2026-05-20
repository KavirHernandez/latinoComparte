package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.ContactEntity;
import org.example.latinocomparte.entities.ContactEntity.Finalidad;

import java.util.List;

public interface ContactService {
    public List<ContactEntity> listAll();

    public ContactEntity findById(Long id);

    public void save(ContactEntity contact);

    public void delete(Long id);

    public List<ContactEntity> searchByNombreContacto(String nombreContacto);

    public List<ContactEntity> filterSolicitudes(
            String nombreContacto,
            String correo,
            String telefono,
            Finalidad finalidad,
            Long idUsuario
    );
}
