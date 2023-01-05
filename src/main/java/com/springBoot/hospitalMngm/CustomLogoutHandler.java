package com.springBoot.hospitalMngm;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomLogoutHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("the uri is: " + request. getRequestURI());
		
		if (request.getQueryString().equals("register")) {
			response.sendRedirect("/register");
			request.logout();
		}
		
		else if (request.getQueryString().equals("login")) {
			
			response.sendRedirect("/mylogin?logout");
			request.logout();
			
		}
	}

}
