package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.NewsEntity;
import java.util.List;

public interface NewsService {

    List<NewsEntity> listAll();

    List<NewsEntity> listPublicadas();

    NewsEntity findById(Long id);

    void save(NewsEntity news);

    void delete(Long id);

    List<NewsEntity> searchByTitulo(String titulo);

    List<NewsEntity> searchByCategoria(String categoria);

    List<NewsEntity> searchByAutor(String autor);

    List<NewsEntity> filterNews(String titulo, String autor, String categoria, Long idNoti);
}
