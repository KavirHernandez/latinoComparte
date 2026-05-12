package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.NewsEntity;
import org.example.latinocomparte.models.daos.NewsDao;
import org.example.latinocomparte.models.daos.RoleDao;

import java.util.List;

public class NewsServiceImpl implements NewsService{

    private final NewsDao newsDao;

    public NewsServiceImpl(NewsDao newsDao) {this.newsDao = newsDao;}

    @Override
    public List<NewsEntity> listAll() {
        return newsDao.findAll();
    }

    @Override
    public NewsEntity findById(Long id) {
        return newsDao.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
    }

    @Override
    public void save(NewsEntity news) {
        newsDao.save(news);
    }

    @Override
    public void delete(Long id) {
        newsDao.deleteById(id);
    }

    @Override
    public List<NewsEntity> searchByTitulo(String titulo) {
        return newsDao.findByTituloContainingIgnoreCase(titulo);
    }

    @Override
    public List<NewsEntity> searchByCategoria(String categoria) {
        return newsDao.findByCategoriaContainingIgnoreCase(categoria);
    }

    @Override
    public List<NewsEntity> searchByAutor(String autor) {
        return newsDao.findByAutorContainingIgnoreCase(autor);
    }

    @Override
    public List<NewsEntity> filterNews(String titulo, String autor, String categoria, Long idNoti) {
        return newsDao.filterNews(titulo, autor, categoria, idNoti);
    }


}
