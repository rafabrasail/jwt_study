package com.demo.demo.service;

import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.demo.data.DetalheUsuarioData;
import com.demo.demo.model.RoleModel;
import com.demo.demo.model.UsuarioModel;
import com.demo.demo.repository.FuncionalidadesRepository;
import com.demo.demo.repository.RoleRepository;
import com.demo.demo.repository.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
    
    private final PasswordEncoder encoder;  //para encriptografar o password do user by code

    @Autowired
    private final UsuarioRepository repository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final FuncionalidadesRepository funcionalidadesRepository;

    public DetalheUsuarioServiceImpl(UsuarioRepository repository, PasswordEncoder encoder,
                                    RoleRepository roleRepository, FuncionalidadesRepository funcionalidadesRepository){
        this.repository = repository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.funcionalidadesRepository = funcionalidadesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<UsuarioModel> usuario = repository.findByLogin(username);
        if(!usuario.isPresent()){
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }
        return new DetalheUsuarioData(usuario);
    }

    //Supostamente para criar novov usuario
    public UsuarioModel registerUser(UsuarioModel usuarioModel){
        RoleModel role = roleRepository.findByRoleName("UsuarioModel").get();
        Set<RoleModel> userRoles = new HashSet<>();
        userRoles.add(role);
        usuarioModel.setRole(userRoles);
        return repository.save(usuarioModel);
        
    }

    //Testar - pegar usuario existente e alterar a role
    public void registerRoleInUser(UsuarioModel usuario, RoleModel role){
        RoleModel roleModel = roleRepository.findByRoleName("RoleModel").get();
        Set<RoleModel> userRoles = new HashSet<>();
        UsuarioModel usuarioModel = repository.findById(null).get();
        userRoles.add(roleModel);
        usuarioModel.setRole(userRoles);
    }

    //Authority in some way
    private Set getAuthority(UsuarioModel usuarioModel){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuarioModel.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    public void initUser(){
        RoleModel roleAdminModel = new RoleModel();
        roleAdminModel.setId(null);
        roleAdminModel.setRoleName("admin");
        roleAdminModel.setDescription("admin role");
        roleAdminModel.setFunctionalities(funcionalidadesRepository.getReferenceById(1));
        roleRepository.save(roleAdminModel);

        RoleModel roleUserModel = new RoleModel();
        roleUserModel.setId(null);
        roleUserModel.setRoleName("user");
        roleUserModel.setDescription("user role");
        roleUserModel.setFunctionalities(funcionalidadesRepository.getReferenceById(2));
        roleRepository.save(roleUserModel);

        UsuarioModel usuarioModel_01 = new UsuarioModel();
        usuarioModel_01.setId(null);
        usuarioModel_01.setLogin("rsanto");
        usuarioModel_01.setPassword(encoder.encode("admin@pass"));
        Set<RoleModel> role_01 = new HashSet<>();
        role_01.add(roleAdminModel);
        usuarioModel_01.setRole(role_01);
        repository.save(usuarioModel_01);

        UsuarioModel usuarioModel_02 = new UsuarioModel();
        usuarioModel_02.setId(null);
        usuarioModel_02.setLogin("zeca");
        usuarioModel_02.setPassword(encoder.encode("zeca@pass"));
        Set<RoleModel> role_02 = new HashSet<>();
        role_02.add(roleUserModel);
        usuarioModel_02.setRole(role_02);
        repository.save(usuarioModel_02);

        // insert into user_demo.user_role (usuario_id, roles_id) values (1,1);
    }
}