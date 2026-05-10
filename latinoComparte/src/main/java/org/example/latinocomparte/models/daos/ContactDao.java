package org.example.latinocomparte.models.daos;

import org.example.latinocomparte.entities.ContactEntity;
import org.example.latinocomparte.entities.ContactEntity.Finalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactDao extends JpaRepository<ContactEntity, Long> {
    List<ContactEntity> findByNombreContactoContainingIgnoreCase(String nombreContacto);

    List<ContactEntity> findByCorreoContainingIgnoreCase(String correo);

    List<ContactEntity> findByTelefonoContainingIgnoreCase(String telefono);

    List<ContactEntity> findAllByOrderByFechaCreacionAsc();

    List<ContactEntity> findByFinalidad(Finalidad finalidad);

    @Query("""
            SELECT s FROM ContactEntity s
            WHERE (:nombreContacto IS NULL OR :nombreContacto = '' OR LOWER(s.nombreContacto) LIKE LOWER(CONCAT('%', :nombreContacto, '%')))
            AND (:correo IS NULL OR :correo = '' OR LOWER(s.correo) LIKE LOWER(CONCAT('%', :correo, '%')))
            AND (:telefono IS NULL OR :telefono = '' OR LOWER(s.telefono) LIKE LOWER(CONCAT('%', :telefono, '%')))
            AND (:finalidad IS NULL OR s.finalidad = :finalidad)
            AND (:idUsuario IS NULL OR s.usuario.id = :idUsuario)
            """)
    List<ContactEntity> filterSolicitudes(
        @Param("nombreContacto") String nombreContacto,
        @Param("correo") String correo,
        @Param("telefono") String telefono,
        @Param("finalidad") Finalidad finalidad,
        @Param("idUsuario") Long idUsuario
    );
}
