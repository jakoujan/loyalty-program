package com.bimbo.lo.controller;

import com.bimbo.lo.data.entity.User;
import com.bimbo.lo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    final UserService service;

    @GetMapping
    public Collection<User> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/{username}")
    Optional<User> get(@PathVariable("username") String username) {
        return this.service.get(username);
    }

    @PostMapping
    User save(@RequestBody User entity) {
        return this.service.save(entity);
    }

    @PutMapping("/{id}")
    void update(@PathVariable("id") Integer id, @RequestBody User entity) {
        this.service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Integer id) {
        this.service.delete(id);
    }
}
