package org.example.latinocomparte.models.daos;

import org.aspectj.weaver.ast.Test;
import org.example.latinocomparte.entities.TestimoniesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestimoniesDao extends JpaRepository<TestimoniesEntity, Long> {
    List<TestimoniesEntity> findByNombreTestContainingIgnoreCase(String nombreTest);
    List<TestimoniesEntity> findByCargoEmpresaContainingIgnoreCase(String cargoEmpresa);
    List<TestimoniesEntity> findAllByOrderByFechaCreacionDesc();

    @Query("""
       SELECT t FROM TestimoniesEntity t
       
       WHERE (:nombreTest IS NULL OR :nombreTest = '' 
       OR LOWER(t.nombreTest) 
       LIKE LOWER(CONCAT('%', :nombreTest, '%')))
       AND (:cargoEmpresa IS NULL OR :cargoEmpresa = '' 
       OR LOWER(t.cargoEmpresa) 
       LIKE LOWER(CONCAT('%', :cargoEmpresa, '%')))
       AND (:idUser IS NULL OR t.usuarios.idUser = :idUser)
       
       """)
    List<TestimoniesEntity> filterTestimonios(
            @Param("nombreTest") String nombreTest,
            @Param("cargoEmpresa") String cargoEmpresa,
            @Param("idUser") Long idUser
    );

}
