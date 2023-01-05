package com.springBoot.hospitalMngm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springBoot.hospitalMngm.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
