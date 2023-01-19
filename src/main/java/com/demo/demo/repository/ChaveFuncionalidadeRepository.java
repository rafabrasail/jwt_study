package com.demo.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.demo.model.ChaveFuncionalidadeModel;

public interface ChaveFuncionalidadeRepository extends JpaRepository<ChaveFuncionalidadeModel, Integer> {
    public Optional<ChaveFuncionalidadeModel> findById(Integer id);
}
