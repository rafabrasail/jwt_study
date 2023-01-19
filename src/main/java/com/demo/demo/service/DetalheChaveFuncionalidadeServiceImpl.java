package com.demo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demo.model.ChaveFuncionalidadeModel;
import com.demo.demo.repository.ChaveFuncionalidadeRepository;

@Service
public class DetalheChaveFuncionalidadeServiceImpl {
    
    @Autowired
    private ChaveFuncionalidadeRepository chaveFuncionalidadeRepository;

    public ChaveFuncionalidadeModel createChaveFuncionalidade(ChaveFuncionalidadeModel chaveFuncionalidadeModel){
        return chaveFuncionalidadeRepository.save(chaveFuncionalidadeModel);
    }

    public void initChaveFunctionalite(){
        ChaveFuncionalidadeModel chave1 = new ChaveFuncionalidadeModel();
        chave1.setId(null);
        chave1.setVisualizar(true);
        chave1.setEditar(true);
        chave1.setAprovar(true);
        chaveFuncionalidadeRepository.save(chave1);

        ChaveFuncionalidadeModel chave2 = new ChaveFuncionalidadeModel();
        chave2.setId(null);
        chave2.setVisualizar(false);
        chave2.setEditar(true);
        chave2.setAprovar(true);
        chaveFuncionalidadeRepository.save(chave2);

        ChaveFuncionalidadeModel chave3 = new ChaveFuncionalidadeModel();
        chave3.setId(null);
        chave3.setVisualizar(true);
        chave3.setEditar(false);
        chave3.setAprovar(true);
        chaveFuncionalidadeRepository.save(chave3);

        ChaveFuncionalidadeModel chave4 = new ChaveFuncionalidadeModel();
        chave4.setId(null);
        chave4.setVisualizar(true);
        chave4.setEditar(true);
        chave4.setAprovar(false);
        chaveFuncionalidadeRepository.save(chave4);

        ChaveFuncionalidadeModel chave5 = new ChaveFuncionalidadeModel();
        chave5.setId(null);
        chave5.setVisualizar(false);
        chave5.setEditar(false);
        chave5.setAprovar(true);
        chaveFuncionalidadeRepository.save(chave5);

        ChaveFuncionalidadeModel chave6 = new ChaveFuncionalidadeModel();
        chave6.setId(null);
        chave6.setVisualizar(false);
        chave6.setEditar(true);
        chave6.setAprovar(false);
        chaveFuncionalidadeRepository.save(chave6);

        ChaveFuncionalidadeModel chave7 = new ChaveFuncionalidadeModel();
        chave7.setId(null);
        chave7.setVisualizar(true);
        chave7.setEditar(false);
        chave7.setAprovar(false);
        chaveFuncionalidadeRepository.save(chave7);

        ChaveFuncionalidadeModel chave8 = new ChaveFuncionalidadeModel();
        chave8.setId(null);
        chave8.setVisualizar(false);
        chave8.setEditar(false);
        chave8.setAprovar(false);
        chaveFuncionalidadeRepository.save(chave8);
    }
}
