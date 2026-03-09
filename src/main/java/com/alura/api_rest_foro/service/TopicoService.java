package com.alura.api_rest_foro.service;

import com.alura.api_rest_foro.domain.curso.Curso;
import com.alura.api_rest_foro.domain.curso.CursoRepository;
import com.alura.api_rest_foro.domain.topico.EstadoTopico;
import com.alura.api_rest_foro.domain.topico.Topico;
import com.alura.api_rest_foro.domain.topico.TopicoRepository;
import com.alura.api_rest_foro.domain.topico.dto.DatosActualizarTopico;
import com.alura.api_rest_foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.api_rest_foro.domain.topico.dto.DatosRegistroTopico;
import com.alura.api_rest_foro.domain.usuario.Usuario;
import com.alura.api_rest_foro.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alura.api_rest_foro.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public DatosDetalleTopico save(DatosRegistroTopico datos) {

        Curso curso = cursoRepository.findById(datos.id_curso())
                .orElseThrow(
                        () -> new EntityNotFoundException("No se encontró el usuario con id: " + datos.id_curso()));

        Usuario usuario = usuarioRepository.findById(datos.id_usuario())
                .orElseThrow(
                        () -> new EntityNotFoundException("No se encontró el usuario con id: " + datos.id_usuario()));

        if (topicoRepository.existsByTitulo(datos.titulo())) {
            throw new ValidacionException("Ya existe un tópico con ese mismo título.");
        }
        if (topicoRepository.existsByMensaje(datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con ese mismo mensaje.");
        }

        LocalDateTime fecha = LocalDateTime.now();
        Topico topico = new Topico(null, datos.titulo(), datos.mensaje(), fecha, EstadoTopico.PENDIENTE, usuario,
                curso);
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }

    public Page<DatosDetalleTopico> findAll(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new);
    }

    @Transactional
    public DatosDetalleTopico findById(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el tópico con id: " + id));
        // lanza un EntityNotFoundException para atraparlo con el gestor de errores y
        // dar el codigo http correcto
        return new DatosDetalleTopico(topico);
    }

    @Transactional
    public Topico update(Long id_topico, DatosActualizarTopico datos) {
        Topico topico = topicoRepository.findById(id_topico)
                .orElseThrow(
                        () -> new EntityNotFoundException("No se encontró el tópico con id: " + id_topico));

        topico = topicoRepository.getReferenceById(id_topico);

        if (topicoRepository.existsByTitulo(datos.titulo()) && !topico.getTitulo().equals(datos.titulo())) {
            throw new ValidacionException("Ya existe un tópico con ese nombre!");
        }
        if (topicoRepository.existsByMensaje(datos.mensaje()) && !topico.getMensaje().equals(datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con ese mensaje!");
        }
        topico.actualizar(datos);
        return topico;
    }

    @Transactional
    public void deleteById(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("No se encontró el tópico con id: " + id));
        topico.eliminar();
    }
}
