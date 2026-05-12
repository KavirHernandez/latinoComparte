package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.TestimoniesEntity;

import java.util.List;

public class TestimoniesServiceImpl implements TestimoniesService{


    @Override
    public List<TestimoniesEntity> listAll() {
        return List.of();
    }

    @Override
    public TestimoniesEntity findById(Long id) {
        return null;
    }

    @Override
    public void save(TestimoniesEntity testimonies) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TestimoniesEntity> searchByNombre(String nombre) {
        return List.of();
    }

    @Override
    public List<TestimoniesEntity> searchByCargo(String cargo) {
        return List.of();
    }

    @Override
    public List<TestimoniesEntity> filterTestimonies(String nombreTest, String cargo, Long idProd) {
        return List.of();
    }
}
