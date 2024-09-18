package com.bimbo.lo.controller;

import com.bimbo.lo.data.repository.UserRepository;
import com.bimbo.lo.data.request.LoginRequest;
import com.bimbo.lo.data.response.LoginResponse;
import com.bimbo.lo.security.JWTSecurityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "api/auth")
public class AuthenticationController {


    JWTSecurityService jwtSecurityService;
    UserRepository userRepository;
    AuthenticationManager authenticationManager;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginData) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.username(), loginData.password()));
        var ou = this.userRepository.findByUsername(loginData.username());
        if (ou.isPresent()) {
            var user = ou.get();
            user.setPassword(null);
            return new LoginResponse(user, jwtSecurityService.generate(user.getUsername()));
        } else
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales erróneas");
    }
}