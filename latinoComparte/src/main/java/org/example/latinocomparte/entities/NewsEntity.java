package org.example.latinocomparte.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name="noticias")

public class NewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_not")
    private Long idNoti;

    @NotBlank
    @Size(max = 150)
    @Column(name ="titulo_not", length = 150, nullable = false)
    private String titulo;

    @NotBlank
    @Size(max = 255)
    @Column(name ="resumen_not", length = 250, nullable = false)
    private String resumen;

    @NotBlank
    @Size(max = 255)
    @Column(name ="contenido_not", nullable = false)
    private String contenido;

    @Size(max = 200)
    @Column (name = "imagen_url", length = 200)
    private String imagenUrl;

    @NotNull
    @DateTimeFormat(pattern = "yyyy,MM,dd")
    @Column(name="fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @NotBlank
    @Size(max = 100)
    @Column(name ="autor_not", length = 50, nullable = false)
    private String autor;

    @NotBlank
    @Size(max=20)
    @Column(name ="estado_not", length = 20, nullable = false)
    private String estado;


    @NotBlank
    @Size(max = 100)
    @Column(name ="categoria_not", length = 20, nullable = false)
    private String categoria;

    //foraneas
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity usuarios;


    public NewsEntity(){

    }
}
