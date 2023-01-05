package com.springBoot.hospitalMngm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.springBoot.hospitalMngm.dto.AppUserDto;
import com.springBoot.hospitalMngm.service.HospitalService;

@Controller
public class AuthController {
	
	@Autowired
	private HospitalService service;
	
	
	@GetMapping("/register")
	public String register(@ModelAttribute("user") AppUserDto dto) {
		
		return "register";
		
	}
	
	@PostMapping("/register")
	public String doRegister(@ModelAttribute("user") AppUserDto dto) {
		
		service.save(dto);
		return "redirect:/mylogin?confirm";
		
	}
}
