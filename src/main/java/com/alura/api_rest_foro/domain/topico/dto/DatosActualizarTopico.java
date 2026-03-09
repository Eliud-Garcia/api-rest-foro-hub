package com.alura.api_rest_foro.domain.topico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje
) {
}
