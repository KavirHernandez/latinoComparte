package org.example.latinocomparte.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "usuarios")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Size(max=15)
    @Column(name="cedula")
    private String cedula;

    @Size(max = 15)
    @Column(name = "nombre")
    private String nombre;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @NotBlank
    @Size(max = 255)
    @Column(name = "contrasena", length = 255, nullable = false)
    private String contrasena;

    @Column(name = "estado_usu", nullable = false)
    private boolean estadoUsu = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private RoleEntity rol;

    @ManyToOne
    @JoinColumn(name = "id_country", nullable = false)
    private CountryEntity country;

    public UserEntity(){}






}
