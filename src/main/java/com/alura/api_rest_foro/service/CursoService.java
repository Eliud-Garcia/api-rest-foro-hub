package com.alura.api_rest_foro.service;

import com.alura.api_rest_foro.domain.curso.Curso;
import com.alura.api_rest_foro.domain.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }
}
