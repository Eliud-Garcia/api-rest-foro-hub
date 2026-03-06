package com.alura.api_rest_foro.domain.curso.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank String nombre,
        @NotBlank String categoria
) {
}
