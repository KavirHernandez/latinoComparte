package org.example.latinocomparte.entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name="roles")
public class RoleEntity implements Serializable {
    private static final long serialVerionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rol")
    private Long idRol;

    @NotBlank
    @Size(max=20)
    @Column(name="nombre_rol", length = 20, nullable = false)
    private String rol;

    public RoleEntity() {}
    public RoleEntity(String rol) {
        this.rol = rol;
    }

}
