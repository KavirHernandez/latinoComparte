package org.example.latinocomparte.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="pais")
public class CountryEntity {
    private static final long serialVerionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_country")
    private Long idCountry;

    @NotBlank
    @Size(max=50)
    @Column(name = "nombre_coun", length = 100, nullable = false)
    private String Country;

    public CountryEntity() {

    }

}
