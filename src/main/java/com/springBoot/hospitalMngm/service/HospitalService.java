package com.springBoot.hospitalMngm.service;

import java.util.List;

import com.springBoot.hospitalMngm.dto.AppUserDto;
import com.springBoot.hospitalMngm.model.Patient;

public interface HospitalService {

	AppUserDto save(AppUserDto userDto)  ;
	
	List<AppUserDto> getAll();
	
	AppUserDto getByUserName(String userName);
	
	AppUserDto getByName(String name);
	
	void addNewPatient(Patient patient, String name)throws Exception;
	
	List<Patient> getPatientsOfDoc(String name);
	
	Patient patientById(int patientId);
	
	void deletePatient(int id);
	
	void addPrescriptionToPatient(int id, String prescription, String docName);
}
