package com.demo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demo.model.RoleModel;
import com.demo.demo.repository.RoleRepository;

@Service
public class DetalheRoleServiceImpl {
    
    @Autowired
    private RoleRepository roleRepository;

    public RoleModel createNewRole(RoleModel roleModel){
        return roleRepository.save(roleModel);
    }
}
