package com.alura.api_rest_foro.controller;

import com.alura.api_rest_foro.domain.topico.Topico;
import com.alura.api_rest_foro.domain.topico.dto.DatosActualizarTopico;
import com.alura.api_rest_foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.api_rest_foro.domain.topico.dto.DatosRegistroTopico;
import com.alura.api_rest_foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        DatosDetalleTopico topico = topicoService.save(datos);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listar(
            @PageableDefault(size = 30, sort = { "titulo" }) Pageable paginacion) {
        Page<DatosDetalleTopico> lista = topicoService.findAll(paginacion);
        // Se puede hacer Spring HATEOAS para mejorar la paginación
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> buscarPorId(@PathVariable Long id) {
        DatosDetalleTopico topico = topicoService.findById(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos){
        Topico modificacion = topicoService.update(id, datos);
        return ResponseEntity.ok(new DatosDetalleTopico(modificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        topicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
