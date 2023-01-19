package com.demo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.demo.model.FuncionalidadeModel;
import com.demo.demo.repository.ChaveFuncionalidadeRepository;
import com.demo.demo.repository.FuncionalidadesRepository;

@Service
public class DetalhesFuncionalidadeServiceImpl {

    @Autowired
    private FuncionalidadesRepository funcionalidadesRepository;

    @Autowired
    private ChaveFuncionalidadeRepository chaveFuncionalidadeRepository;

    public FuncionalidadeModel createFuncionalidade(FuncionalidadeModel funcionalidadeModel){
        return funcionalidadesRepository.save(funcionalidadeModel);
    }

    public void initFuncionalidade(){
        FuncionalidadeModel func1 = new FuncionalidadeModel();
        func1.setId(null);
        func1.setAlerts(chaveFuncionalidadeRepository.getReferenceById(1));
        func1.setHistory(chaveFuncionalidadeRepository.getReferenceById(2));
        func1.setReports(chaveFuncionalidadeRepository.getReferenceById(8));
        func1.setTechPubs(chaveFuncionalidadeRepository.getReferenceById(4));
        funcionalidadesRepository.save(func1);
    }
    
}
