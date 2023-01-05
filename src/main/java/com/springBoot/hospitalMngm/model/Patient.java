package com.springBoot.hospitalMngm.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
@Component
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	private String name;
	
	private String appointment;
	
	private String note;
	
	@ElementCollection
	@CollectionTable(
			name="presciptions-table",
			joinColumns = @JoinColumn(name = "patient-id")
			)
	@Column(name="presciptions-list")
	private List<String> presciptions = new ArrayList<String>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="doctor-patient")
	private AppUser doctor;


	public Patient() {
	}

	public Patient(String name, String appointment, AppUser doctor) {
		this.name = name;
		this.appointment = appointment;
		this.doctor = doctor;
	}



	public Patient(String name, String appointment, List<String> presciptions, AppUser doctor) {
		this.name = name;
		this.appointment = appointment;
		
		this.presciptions = presciptions;
		this.doctor = doctor;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

	public AppUser getDoctor() {
		return doctor;
	}


	public void setDoctor(AppUser doctor) {
		this.doctor = doctor;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
	public String dateConverter() {
		String[] split = this.appointment.split(" ");
		
		return split[0];
	}

	public List<String> getPresciptions() {
		return presciptions;
	}

	public void setPresciptions(List<String> presciptions) {
		this.presciptions = presciptions;
	}
}
