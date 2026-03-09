package com.alura.api_rest_foro.domain.topico;

import com.alura.api_rest_foro.domain.curso.Curso;
import com.alura.api_rest_foro.domain.topico.dto.DatosActualizarTopico;
import com.alura.api_rest_foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topico")

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topico")
    private Long id;

    @Column(name = "titulo_topico")
    private String titulo;

    @Column(name = "mensaje_topico")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fecha_creacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_topico")
    private EstadoTopico estado;

    //relaciones de la tabla topico

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idusuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_idcurso")
    private Curso curso;

    public void actualizar(DatosActualizarTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
    }

    public void eliminar() {
        this.estado = EstadoTopico.DESACTIVADO;
    }
}
