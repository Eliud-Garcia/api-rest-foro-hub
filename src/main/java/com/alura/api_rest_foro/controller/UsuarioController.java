package com.alura.api_rest_foro.controller;

import com.alura.api_rest_foro.domain.curso.dto.DatosDetalleCurso;
import com.alura.api_rest_foro.domain.usuario.Usuario;
import com.alura.api_rest_foro.domain.usuario.dto.DatosDetalleUsuario;
import com.alura.api_rest_foro.domain.usuario.dto.DatosRegistroUsuario;
import com.alura.api_rest_foro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = new Usuario(datos);
        usuarioService.save(usuario);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }
}
