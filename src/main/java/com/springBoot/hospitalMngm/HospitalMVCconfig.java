package com.springBoot.hospitalMngm;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// it is better not use enablewbmvc here. since it interrups the resource handler
@Configuration
public class HospitalMVCconfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/mylogin").setViewName("fancyLogin");
		
		
	}

	

	
}
