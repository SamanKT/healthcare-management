package com.springBoot.hospitalMngm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot.hospitalMngm.model.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Integer>{
      Optional<AppRole> findByName(String name);
}
