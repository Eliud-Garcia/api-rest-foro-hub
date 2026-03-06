package com.alura.api_rest_foro.domain.curso.dto;

import com.alura.api_rest_foro.domain.curso.Curso;
import jakarta.validation.constraints.NotBlank;

public record DatosDetalleCurso(
        Long id,
        String nombre,
        String categoria
) {
    public DatosDetalleCurso(Curso curso) {
        this(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}
