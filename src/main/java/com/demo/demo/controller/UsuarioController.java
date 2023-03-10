package com.demo.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.demo.model.UsuarioModel;
import com.demo.demo.repository.UsuarioRepository;
import com.demo.demo.service.DetalheUsuarioServiceImpl;



@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    private DetalheUsuarioServiceImpl detalheUsuarioServiceImpl;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder){
        this.repository = repository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void initUser(){
        detalheUsuarioServiceImpl.initUser();
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuarioModel){
        usuarioModel.setPassword(encoder.encode(usuarioModel.getPassword()));
        return ResponseEntity.ok(repository.save(usuarioModel));
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password){
        
        Optional<UsuarioModel> optUsuario = repository.findByLogin(login);
        if (!optUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        UsuarioModel usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

    @PutMapping("{id}")
    public void atualizar(@PathVariable Integer id, @RequestBody @Validated UsuarioModel usuarioAtualizado){
        repository.findById(id).map(
                                    usuario -> {
                                        usuario.setRole(null);
                                        return repository.save(usuario);
                                   }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "cliente nao encontrado"));
                                   
    }

    @GetMapping({ "/forAdmin" })
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping({ "/forUser" })
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }
}
