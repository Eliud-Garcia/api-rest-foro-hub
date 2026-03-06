package com.alura.api_rest_foro.controller;

import com.alura.api_rest_foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.api_rest_foro.domain.topico.dto.DatosRegistroTopico;
import com.alura.api_rest_foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos){
        DatosDetalleTopico topico = topicoService.save(datos);
        return ResponseEntity.ok(topico);
    }
}
