package com.demo.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.model.UsuarioModel;
import com.demo.demo.repository.UsuarioRepository;



@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository){
        this.repository = repository;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuarioModel){
        return ResponseEntity.ok(repository.save(usuarioModel));
    }
}
