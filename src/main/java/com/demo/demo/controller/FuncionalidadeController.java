package com.demo.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.model.FuncionalidadeModel;
import com.demo.demo.repository.FuncionalidadesRepository;
import com.demo.demo.service.DetalhesFuncionalidadeServiceImpl;

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

}
