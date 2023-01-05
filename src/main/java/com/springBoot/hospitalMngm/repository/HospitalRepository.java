package com.springBoot.hospitalMngm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot.hospitalMngm.model.AppUser;

public interface HospitalRepository extends JpaRepository<AppUser, Integer> {

	Optional<AppUser> findByUserName(String userName);
	Optional<AppUser> findByName(String name);
}
