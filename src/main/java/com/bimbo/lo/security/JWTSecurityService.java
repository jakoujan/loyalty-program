package com.bimbo.lo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
public class JWTSecurityService {

    final String secret;

    public JWTSecurityService(@Value("${secret.key}") String secret) {
        this.secret = secret;
    }

    public String generate(String username) {
        return Jwts.builder().subject(username).issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(8, ChronoUnit.HOURS)))
                .signWith(getKey()).compact();
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validate(String token, UserDetails user) {
        var payload = Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
        var unexpired = payload.getExpiration().after(Date.from(Instant.now()));
        return unexpired && Objects.equals(user.getUsername(), payload.getSubject());
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
