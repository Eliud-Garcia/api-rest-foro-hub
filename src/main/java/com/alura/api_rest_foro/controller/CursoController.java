package com.alura.api_rest_foro.controller;

import com.alura.api_rest_foro.domain.curso.Curso;
import com.alura.api_rest_foro.domain.curso.dto.DatosDetalleCurso;
import com.alura.api_rest_foro.domain.curso.dto.DatosRegistroCurso;
import com.alura.api_rest_foro.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")

public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriComponentsBuilder){
        Curso curso = new Curso(datos);
        cursoService.save(curso);
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }
}
