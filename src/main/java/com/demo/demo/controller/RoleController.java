package com.demo.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.model.RoleModel;
import com.demo.demo.repository.RoleRepository;


@RestController
@RequestMapping("/api/role")
public class RoleController {
    
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @PostMapping("/salvar")
    public ResponseEntity<RoleModel> salvar(@RequestBody RoleModel roleModel){
        return ResponseEntity.ok(roleRepository.save(roleModel));
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<RoleModel>> listarTodos(){
        return ResponseEntity.ok(roleRepository.findAll());
    }
}
