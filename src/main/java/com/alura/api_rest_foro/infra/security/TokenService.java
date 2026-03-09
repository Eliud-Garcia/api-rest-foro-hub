package com.alura.api_rest_foro.infra.security;

import com.alura.api_rest_foro.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(Usuario usuario){
        //codigo de la documentacion oficial: https://github.com/auth0/java-jwt
        //algoritmo modificado
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("api forohub")
                    .withSubject(usuario.getEmail()) //quien es
                    .withExpiresAt(fechaExpiracion()) //expiracion del token
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-05:00"));
    }
}
