package com.springBoot.hospitalMngm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class HospitalSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
		
		http
		
		.authorizeHttpRequests(t ->
					t
					
					.requestMatchers("/mylogin**").permitAll()
					.requestMatchers("/js/**", "/css/**" , "/static/**", "/resources**").permitAll()
					.requestMatchers("/").authenticated()
					.anyRequest()
					.permitAll()
					
				)
		.formLogin(form ->
				form
				.loginPage("/mylogin")
				.loginProcessingUrl("/mylogin")
				.successHandler(successHandler())
				
				.permitAll()
					)
		.logout(out ->
				out.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				 .logoutSuccessHandler(logouyHanlder())
				.permitAll()
				)
		
		.authenticationProvider(authProvider())
		
		;
		
		return http.build();
		
	}
	
	private LogoutSuccessHandler logouyHanlder() {
		
		return new CustomLogoutHandler();
	}

	private AuthenticationSuccessHandler successHandler() {
		SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
		simpleUrlAuthenticationSuccessHandler.setDefaultTargetUrl("/");
		return simpleUrlAuthenticationSuccessHandler;
	}

	@Bean
	public UserDetailsService detailsService() {
		
		return new HospitalUserDetailsServiceer();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(encoder());
		provider.setUserDetailsService(detailsService());
				
				return provider;
	}
	
	
	@Bean
	public PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	
	
}
