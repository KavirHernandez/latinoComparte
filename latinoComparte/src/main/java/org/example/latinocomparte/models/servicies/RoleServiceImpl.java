package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.RoleEntity;
import org.example.latinocomparte.models.daos.RoleDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleEntity> listAll() {
        return roleDao.findAll();
    }

    @Override
    public RoleEntity findById(Long id) {
        return roleDao.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public void save(RoleEntity role) {
        roleDao.save(role);

    }
}

