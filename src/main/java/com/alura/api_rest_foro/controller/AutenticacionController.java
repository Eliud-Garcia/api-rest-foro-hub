package com.alura.api_rest_foro.controller;

import com.alura.api_rest_foro.domain.usuario.Usuario;
import com.alura.api_rest_foro.domain.usuario.dto.DatosLogin;
import com.alura.api_rest_foro.infra.security.DatosTokenJWT;
import com.alura.api_rest_foro.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> iniciarSesion(@RequestBody @Valid DatosLogin datos){
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasenia());
        var autenticacion = manager.authenticate(authenticationToken);
        Usuario usuario = (Usuario) autenticacion.getPrincipal();
        String tokenJWT = tokenService.generarToken(usuario);
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
