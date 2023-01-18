package com.demo.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.demo.data.DetalheUsuarioData;
import com.demo.demo.model.RoleModel;
import com.demo.demo.model.UsuarioModel;
import com.demo.demo.repository.RoleRepository;
import com.demo.demo.repository.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
    
    private final PasswordEncoder encoder;  //para encriptografar o password do user by code

    @Autowired
    private final UsuarioRepository repository;

    @Autowired
    private final RoleRepository roleRepository;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository, PasswordEncoder encoder,
                                    RoleRepository roleRepository){
        this.repository = repository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
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
        RoleModel roleAdminModel = new RoleModel();
        roleAdminModel.setId(null);
        roleAdminModel.setRoleName("admin");
        roleAdminModel.setDescription("admin role");
        roleAdminModel.setFunctionalities("null");
        roleRepository.save(roleAdminModel);

        RoleModel roleUserModel = new RoleModel();
        roleUserModel.setId(null);
        roleUserModel.setRoleName("user");
        roleUserModel.setDescription("user role");
        roleUserModel.setFunctionalities("null");
        roleRepository.save(roleUserModel);

        UsuarioModel usuarioModel_01 = new UsuarioModel();
        usuarioModel_01.setId(null);
        usuarioModel_01.setLogin("rsanto");
        usuarioModel_01.setPassword(encoder.encode("admin@pass"));
        repository.save(usuarioModel_01);

        UsuarioModel usuarioModel_02 = new UsuarioModel();
        usuarioModel_02.setId(null);
        usuarioModel_02.setLogin("zeca");
        usuarioModel_02.setPassword(encoder.encode("zeca@pass"));
        repository.save(usuarioModel_02);
    }
}