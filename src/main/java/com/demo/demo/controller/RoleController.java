package com.demo.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("{id}")
    public RoleModel acharRolePorId(@PathVariable Integer id){
        return roleRepository.findById(id)
                             .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "role nao encontrada"));
    }
}
