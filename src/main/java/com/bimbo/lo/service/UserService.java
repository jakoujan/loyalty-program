package com.bimbo.lo.service;

import com.bimbo.lo.data.entity.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Collection<User> getAll();

    Optional<User> get(String username);

    User save(User entity);

    void update(Integer id, User entity);

    void delete(Integer id);
}
