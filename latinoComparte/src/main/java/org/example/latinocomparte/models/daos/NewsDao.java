package org.example.latinocomparte.models.daos;

import org.example.latinocomparte.entities.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface NewsDao extends JpaRepository<NewsEntity, Long> {
    List<NewsEntity> findByTituloContainingIgnoreCase(String titulo);
    List<NewsEntity> findByCategoriaContainingIgnoreCase(String categoria);
    List<NewsEntity> findByAutorContainingIgnoreCase(String autor);
    @Query("""
        SELECT n FROM NewsEntity n
        WHERE (:titulo IS NULL OR :titulo = '' 
            OR LOWER(n.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))   
        AND (:autor IS NULL OR :autor = '' 
            OR LOWER(n.autor) LIKE LOWER(CONCAT('%', :autor, '%')))            
        AND (:categoria IS NULL OR :categoria = '' 
            OR LOWER(n.categoria) LIKE LOWER(CONCAT('%', :categoria, '%')))            
        AND (:idNoti IS NULL 
            OR n.idNoti = :idNoti)
        """)
    List<NewsEntity> filterNews(
            @Param("titulo") String titulo,
            @Param("autor") String autor,
            @Param("categoria") String categoria,
            @Param("idNoti") Long idNoti
    );
}
