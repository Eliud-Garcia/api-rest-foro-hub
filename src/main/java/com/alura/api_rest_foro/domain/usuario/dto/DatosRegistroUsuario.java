package com.alura.api_rest_foro.domain.usuario.dto;

import jakarta.validation.constraints.*;

public record DatosRegistroUsuario(
        @NotNull String nombre,
        @NotBlank @Email String email,

        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "La contraseña debe tener al menos 8 caracteres, una letra y un número")
        String contrasenia

) {
}
