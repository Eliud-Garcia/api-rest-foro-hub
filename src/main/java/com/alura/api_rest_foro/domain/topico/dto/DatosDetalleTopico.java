package com.alura.api_rest_foro.domain.topico.dto;

import com.alura.api_rest_foro.domain.topico.EstadoTopico;
import com.alura.api_rest_foro.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion,
        EstadoTopico estadoTopico,
        Long fk_usuario,
        Long fk_curso

) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion(),
                topico.getEstado(),
                topico.getUsuario().getId(),
                topico.getCurso().getId()
        );
    }
}
