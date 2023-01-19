package com.demo.demo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.model.ChaveFuncionalidadeModel;
import com.demo.demo.repository.ChaveFuncionalidadeRepository;
import com.demo.demo.service.DetalheChaveFuncionalidadeServiceImpl;

@RestController
@RequestMapping("/api/chaveFuncionalidades")
public class ChaveFunvcionalidadeController {

    private final ChaveFuncionalidadeRepository chaveFuncionalidadeRepository;

    @Autowired
    private DetalheChaveFuncionalidadeServiceImpl detalheChaveFuncionalidadeServiceImpl;

    public ChaveFunvcionalidadeController(ChaveFuncionalidadeRepository chaveFuncionalidadeRepository){
        this.chaveFuncionalidadeRepository = chaveFuncionalidadeRepository;
    }

    @PostConstruct
    public void initChaveFunctionalite(){
        detalheChaveFuncionalidadeServiceImpl.initChaveFunctionalite();
    }
    
    @GetMapping("/listarTodos")
    public ResponseEntity<List<ChaveFuncionalidadeModel>> lsitarTodos(){
        return ResponseEntity.ok(chaveFuncionalidadeRepository.findAll());
    }
}
