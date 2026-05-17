package org.example.latinocomparte.models.servicies;

import org.example.latinocomparte.entities.UserEntity;
import org.example.latinocomparte.models.daos.UserDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserEntity> listAll() {
        return userDao.findAll();
    }

    @Override
    public UserEntity findById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void save(UserEntity user) {
        userDao.save(user);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userDao.findByEmail(email);
    }


    @Override
    public List<UserEntity> searchByNombre(String nombre) {
        return userDao.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<UserEntity> searchByTelefono(String telefono) {
        return userDao.findByTelefonoContainingIgnoreCase(telefono);
    }

    @Override
    public List<UserEntity> findByEstadoUsu(boolean estadoUsu) {
        return userDao.findByEstadoUsu(estadoUsu);
    }

    @Override
    public List<UserEntity> findByRol(Long idRol) {
        return userDao.findByRolIdRol(idRol);
    }

    @Override
    public List<UserEntity> findByCountry(Long idCountry) {
        return userDao.findByCountryIdCountry(idCountry);
    }





}
