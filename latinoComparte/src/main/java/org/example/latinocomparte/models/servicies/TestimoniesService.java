package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.NewsEntity;
import org.example.latinocomparte.entities.TestimoniesEntity;

import java.util.List;

public interface TestimoniesService {

    public List<TestimoniesEntity> listAll();
    public TestimoniesEntity findById(Long id);
    public void save(TestimoniesEntity testimonies) ;
    public void delete(Long id);
    //buscar
    public List<TestimoniesEntity> searchByNombre(String nombre);
    public List<TestimoniesEntity> searchByCargo(String cargo);

    List<TestimoniesEntity> filterTestimonies(String nombreTest,
                                       String cargo,
                                       Long idProd);

}
