package com.demo.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.demo.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Integer>{
    public Optional<RoleModel> findByRoleName(String roleName);
}
