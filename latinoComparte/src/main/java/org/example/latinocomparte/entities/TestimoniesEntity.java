package org.example.latinocomparte.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="testimonios")
public class TestimoniesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_test")
    private Long idTest;

    @NotBlank
    @Size(max=50)
    @Column(name = "nombre_test", length = 50, nullable = false)
    private String nombreTest;

    @NotBlank
    @Size(max=250)
    @Column(name = "opinion", length = 250, nullable = false)
    private String opinion;

    @NotBlank
    @Size(max=50)
    @Column(name = "cargo_empresa", length = 50, nullable = false)
    private String cargoEmpresa;

    @Size(max = 200)
    @Column (name = "imagen_url", length = 200)
    private String imagenUrl;

    @NotBlank
    @Size(max=250)
    @Column(name = "instagram_Uurl", length = 250, nullable = false)
    private String instagramUrl;

    @NotBlank
    @Size(max=250)
    @Column(name = "facebook", length = 250, nullable = false)
    private String facebookUrl;

    @NotNull
    @DateTimeFormat(pattern = "yyyy,MM,dd")
    @Column(name="fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    //foranea
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity usuarios;



    public TestimoniesEntity(){

    }






}
