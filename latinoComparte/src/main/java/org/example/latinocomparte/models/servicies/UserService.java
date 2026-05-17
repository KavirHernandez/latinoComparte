package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<UserEntity> listAll();

    public UserEntity findById(Long id);

    public void save(UserEntity user);

    public void delete(Long id);

    public UserEntity findByEmail(String email);

    public List<UserEntity> searchByNombre(String nombre);

    public List<UserEntity> searchByTelefono(String telefono);

    public List<UserEntity> findByEstadoUsu(boolean estadoUsu);

    public List<UserEntity> findByRol(Long idRol);

    public List<UserEntity> findByCountry(Long idCountry);


}
