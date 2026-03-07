package com.alura.api_rest_foro.service;

import com.alura.api_rest_foro.domain.ValidacionException;
import com.alura.api_rest_foro.domain.usuario.Usuario;
import com.alura.api_rest_foro.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario save(Usuario usuario){
        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new ValidacionException("El correo " + usuario.getEmail() + " ya existe!");
        }
        return usuarioRepository.save(usuario);
    }
}
