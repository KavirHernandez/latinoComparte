package org.example.latinocomparte.models.daos;

import org.example.latinocomparte.entities.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDao extends JpaRepository<CountryEntity, Long> {

}
