package com.demo.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.demo.model.FuncionalidadeModel;

public interface FuncionalidadesRepository extends JpaRepository<FuncionalidadeModel, Integer> {
    public Optional<FuncionalidadeModel> findById(Integer id);
}
