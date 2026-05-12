package org.example.latinocomparte.models.daos;

import org.example.latinocomparte.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsDao extends JpaRepository<NewsEntity, Long> {
    List<NewsEntity> findByTituloContainingIgnoreCase(String titulo);
    List<NewsEntity> findByCategoria(String categoria);






}
