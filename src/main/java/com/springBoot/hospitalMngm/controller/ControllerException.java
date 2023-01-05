package com.springBoot.hospitalMngm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerException{

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception e){
		
		return new ResponseEntity<String>("<h3>an error occured! please send the following message to the app developer:"
				+  e.getMessage() +".</h3> <br> The error is handeled by Spring Exception Handler. <br>"
						+ "<h4>Please login againg by clicking the following link: <br><br><a href='http://localhost:8080/logout'><h3>Logout</h3></a> </h4>"
				, HttpStatus.NOT_FOUND) ;
	}

	
	
}
