package com.demo.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.demo.demo.data.DetalheUsuarioData;
import com.demo.demo.model.UsuarioModel;
import com.demo.demo.repository.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
    
    private final UsuarioRepository repository;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<UsuarioModel> usuario = repository.findByLogin(username);
        if(!usuario.isPresent()){
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }
        return new DetalheUsuarioData(usuario);
    }

    public void initUser(){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setId(null);
        usuarioModel.setLogin("rsanto");
        usuarioModel.setPassword("admin@pass");
    }
}
