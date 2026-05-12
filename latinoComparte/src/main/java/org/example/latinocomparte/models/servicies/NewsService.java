package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.NewsEntity;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface NewsService {

    public List<NewsEntity> listAll();
    public NewsEntity findById(Long id);
    public void save(NewsEntity news) ;
    public void delete(Long id);
    //buscar
    public List<NewsEntity> searchByTitulo(String titulo);
    public List<NewsEntity> searchByCategoria(String categoria);
    public List<NewsEntity> searchByAutor(String autor);

    List<NewsEntity> filterNews(String titulo,
                                       String autor,
                                       String categoria,
                                       Long idNoti);


}
