package org.example.latinocomparte.models.daos;

import org.example.latinocomparte.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    List<UserEntity> findByNombreContainingIgnoreCase(String nombre);

    List<UserEntity> findByTelefonoContainingIgnoreCase(String telefono);

    List<UserEntity> findByEstadoUsu(boolean estadoUsu);

    List<UserEntity> findByRolIdRol(Long idRol);

    List<UserEntity> findByCountryIdCountry(Long idCountry);


}