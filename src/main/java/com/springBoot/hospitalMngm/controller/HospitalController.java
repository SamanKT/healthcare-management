package com.springBoot.hospitalMngm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springBoot.hospitalMngm.model.Patient;
import com.springBoot.hospitalMngm.service.HospitalService;

@Controller
public class HospitalController {

	@Autowired
	private HospitalService service;

	@RequestMapping("/")
	public String firstPage(@ModelAttribute("patient") Patient patient,  Principal principal, Model model) {

		model.addAttribute("patients", service.getPatientsOfDoc(principal.getName()));
		model.addAttribute("user", principal.getName());
		
		

		return "index";
	}

	@RequestMapping("/addAppointment")
	public String addAppointment(@ModelAttribute("patient") Patient patient, Principal principal, Model model)
			throws Exception {

		Patient updatedPatient = (Patient) model.getAttribute("patientForUpdate");
		if (updatedPatient != null)
			service.addNewPatient(updatedPatient, principal.getName());
		else
			service.addNewPatient(patient, principal.getName());

		return "redirect:";
	}

	@RequestMapping("/update/{id}")
	public String updatePatient(@PathVariable int id, Model model) throws Exception {

		Patient patient = service.patientById(id);

		model.addAttribute("patientForUpdate", patient);

		return "update";
	}

	@RequestMapping("/delete/{id}")
	public String deletePatient(@PathVariable int id, Model model, Principal principal) {
	
		service.deletePatient(id);

		return "redirect:/";
	}
	
	
	@GetMapping("/addPres/{id}")
	public String addPresToPatient( @PathVariable int id, Model model, Principal principal) {
		String prescription="";
		model.addAttribute( "newPrescription", prescription);
		model.addAttribute("patientForPres",  service.patientById(id));

		return "add-pres";
	}
	
	@PostMapping("/addPres/{id}")
	public String doAddToPatient(@RequestParam("newPrescription") String prescription,  @PathVariable int id,  Model model, Principal principal) {
		
		service.addPrescriptionToPatient(id,  prescription, principal.getName());

		return "redirect:/";
	}
	
}
