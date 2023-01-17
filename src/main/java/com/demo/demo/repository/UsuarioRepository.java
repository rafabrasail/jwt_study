package com.demo.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.demo.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    public Optional<UsuarioModel> findByLogin(String login);
}
