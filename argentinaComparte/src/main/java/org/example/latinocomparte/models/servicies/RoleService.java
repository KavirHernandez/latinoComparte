package org.example.latinocomparte.models.servicies;



import org.example.latinocomparte.entities.RoleEntity;

import java.util.List;

public interface RoleService {
    public List<RoleEntity> listAll();

    public RoleEntity findById(Long id);

    public void save(RoleEntity role);
}
