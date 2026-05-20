package org.example.latinocomparte.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "contactos")
public class ContactEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nombre_contacto", nullable = false, length = 100)
    private String nombreContacto;

    @NotBlank
    @Size(max = 100)
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @NotBlank
    @Size(max = 100)
    @Column(name = "telefono", nullable = false, length = 100)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(name = "finalidad", nullable = false)
    private Finalidad finalidad;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "fk_usuarios", nullable = true)
    private UserEntity usuario;

    public ContactEntity() {
    }

    public enum Finalidad {
        SERVICIO,               // Consulta sobre servicios generales
        PROGRAMA_EDIFICA,       // Interés en el programa EDIFICA
        SHOWS_Y_CONFERENCIAS    // Contratación de shows o conferencias
    }
}
