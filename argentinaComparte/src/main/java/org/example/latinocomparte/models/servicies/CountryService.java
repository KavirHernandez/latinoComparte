package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.CountryEntity;


public interface CountryService {
    public CountryEntity findById(Long id);
    public void save(CountryEntity country);
}
