package com.demo.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.demo.demo.model.FuncionalidadeModel;
import com.demo.demo.repository.FuncionalidadesRepository;
import com.demo.demo.service.DetalhesFuncionalidadeServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/funcionalidade")
public class FuncionalidadeController {
    
    private final FuncionalidadesRepository funcionalidadesRepository;

    @Autowired
    private DetalhesFuncionalidadeServiceImpl detalhesFuncionalidadeServiceImpl;

    public FuncionalidadeController(FuncionalidadesRepository funcionalidadesRepository){
        this.funcionalidadesRepository = funcionalidadesRepository;
    }

    @PostConstruct
    public void initFuncionalidade(){
        detalhesFuncionalidadeServiceImpl.initFuncionalidade();
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<FuncionalidadeModel>> listarFuncionalidade(){
        return ResponseEntity.ok(funcionalidadesRepository.findAll());
    }

    @GetMapping("{id}")
    public FuncionalidadeModel acharFuncionalidadePorId(@PathVariable Integer id){
        return funcionalidadesRepository.findById(id)
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "func nao encontrada"));
    }

    @PutMapping("{id}")
    public void atualizarFuncionalidade(@PathVariable Integer id, @RequestBody @Validated FuncionalidadeModel funcionalidadeModel){
        funcionalidadesRepository.findById(id).map(
                                funcionalidade -> {
                                    funcionalidade.setAlerts(null);
                                    funcionalidade.setHistory(null);
                                    funcionalidade.setReports(null);
                                    funcionalidade.setTechPubs(null);
                                    return funcionalidadesRepository.save(funcionalidade);
                                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "func nao encontrada e nao atualizada"));      
    }

    @PostMapping(value="/salvar")
    public FuncionalidadeModel salvarFuncionalidade(@RequestBody FuncionalidadeModel funcionalidadeModel) {
        return funcionalidadesRepository.save(funcionalidadeModel);
    }
    


}
