package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.TestimoniesEntity;
import org.example.latinocomparte.models.daos.TestimoniesDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimoniesServiceImpl implements TestimoniesService{
    private final TestimoniesDao testimoniesDao;

    public TestimoniesServiceImpl(TestimoniesDao testimoniesDao) {this.testimoniesDao = testimoniesDao;}


    @Override
    public List<TestimoniesEntity> listAll() {
        return testimoniesDao.findAll();
    }

    @Override
    public TestimoniesEntity findById(Long id) {
        return testimoniesDao.findById(id).orElseThrow(() -> new RuntimeException("Testimonies not found"));
    }

    @Override
    public void save(TestimoniesEntity testimonies) {
        testimoniesDao.save(testimonies);
    }

    @Override
    public void delete(Long id) {
        testimoniesDao.deleteById(id);
    }

    @Override
    public List<TestimoniesEntity> searchByNombre(String nombre) {
        return testimoniesDao.findByNombreTestContainingIgnoreCase(nombre);
    }

    @Override
    public List<TestimoniesEntity> searchByCargo(String cargo) {
        return testimoniesDao.findByCargoEmpresaContainingIgnoreCase(cargo);
    }

    @Override
    public List<TestimoniesEntity> filterTestimonies(String nombreTest, String cargo, Long idProd) {
        return testimoniesDao.filterTestimonios(nombreTest, cargo, idProd);
    }
}
