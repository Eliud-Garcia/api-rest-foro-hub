package com.alura.api_rest_foro.service;

import com.alura.api_rest_foro.domain.curso.Curso;
import com.alura.api_rest_foro.domain.curso.CursoRepository;
import com.alura.api_rest_foro.domain.topico.EstadoTopico;
import com.alura.api_rest_foro.domain.topico.Topico;
import com.alura.api_rest_foro.domain.topico.TopicoRepository;
import com.alura.api_rest_foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.api_rest_foro.domain.topico.dto.DatosRegistroTopico;
import com.alura.api_rest_foro.domain.usuario.Usuario;
import com.alura.api_rest_foro.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public DatosDetalleTopico save(DatosRegistroTopico datos){
        if(!cursoRepository.existsById(datos.id_curso())){
            System.out.println("no existe el curso");
            return null;
        }

        if(!usuarioRepository.existsById(datos.id_usuario())){
            System.out.println("no existe el usuario");
            return null;
        }

        if(topicoRepository.existsByTitulo(datos.titulo())){
            System.out.println("titulo duplicado, ya existe");
            return null;
        }
        if(topicoRepository.existsByMensaje(datos.mensaje())){
            System.out.println("mensaje duplicado, ya existe");
            return null;
        }
        Curso curso = cursoRepository.getReferenceById(datos.id_curso());
        Usuario usuario = usuarioRepository.getReferenceById(datos.id_usuario());

        LocalDateTime fecha = LocalDateTime.now();
        Topico topico = new Topico(null, datos.titulo(), datos.mensaje(), fecha, EstadoTopico.PENDIENTE, usuario, curso);
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }

    public Page<DatosDetalleTopico> findAll(Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosDetalleTopico::new);
    }
}
