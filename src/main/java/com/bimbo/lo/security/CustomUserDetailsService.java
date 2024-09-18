package com.bimbo.lo.security;

import com.bimbo.lo.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userDAO.findByUsername(username).flatMap(user -> {
            return Optional.of(User.builder().username(user.getUsername()).password(user.getPassword())
                    .roles("USER_ROLE").accountLocked(Boolean.FALSE).build());
        }).orElse(null);
    }
}
