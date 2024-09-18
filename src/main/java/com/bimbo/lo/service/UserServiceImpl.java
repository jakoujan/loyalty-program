package com.bimbo.lo.service;

import com.bimbo.lo.data.entity.User;
import com.bimbo.lo.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    /**
     * @return List of Users
     */
    @Override
    public Collection<User> getAll() {
        return repository.findAll();
    }

    /**
     * @param username
     * @return Optional User
     */
    @Override
    public Optional<User> get(String username) {
        return repository.findByUsername(username);
    }

    /**
     * @param entity
     * @return User
     */
    @Override
    public User save(User entity) {
        repository.findByUsername(entity.getUsername()).ifPresentOrElse((user) -> {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "El usuario ya existe");
        }, () -> {
            entity.setPassword(encoder.encode(entity.getPassword()));
            repository.save(entity);
        });
        return entity;
    }

    /**
     * @param id
     * @param entity
     * @return
     */
    @Override
    public void update(Integer id, User entity) {
        repository.findById(id).ifPresent(user -> {
            user.setPassword(encoder.encode(entity.getPassword()));
        });
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        this.repository.findById(id).ifPresent(repository::delete);
    }
}
