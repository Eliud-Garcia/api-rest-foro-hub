package com.alura.api_rest_foro.domain.usuario.dto;

import com.alura.api_rest_foro.domain.usuario.Usuario;

public record DatosDetalleUsuario(
        Long id,
        String nombre,
        String email
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
