package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.CountryEntity;
import org.example.latinocomparte.models.daos.CountryDao;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryServiceImpl implements CountryService {
    @Override
    public CountryEntity findById(Long id) {
        return null;
    }

    @Override
    public void save(CountryEntity country) {

    }
}
