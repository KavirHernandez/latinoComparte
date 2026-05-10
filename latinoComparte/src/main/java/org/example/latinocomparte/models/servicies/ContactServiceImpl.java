package org.example.latinocomparte.models.servicies;


import org.example.latinocomparte.entities.ContactEntity;
import org.example.latinocomparte.models.daos.ContactDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements  ContactService{
    private final ContactDao contactDao;

    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public List<ContactEntity> listAll() {
        return contactDao.findAll();
    }

    @Override
    public ContactEntity findById(Long id) {
        return contactDao.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    @Override
    public void save(ContactEntity contact) {
      contactDao.save(contact);
    }

    @Override
    public void delete(Long id) {
     contactDao.deleteById(id);
    }

    @Override
    public List<ContactEntity> searchByNombreContacto(String nombreContacto) {
        return contactDao.findByNombreContactoContainingIgnoreCase(nombreContacto);
    }

    @Override
    public List<ContactEntity> filterSolicitudes(String nombreContacto, String correo, String telefono, ContactEntity.Finalidad finalidad, Long idUsuario) {
      return contactDao.filterSolicitudes(nombreContacto,correo,telefono,finalidad,idUsuario);
    }
}
