package com.springBoot.hospitalMngm;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springBoot.hospitalMngm.model.AppUser;
import com.springBoot.hospitalMngm.repository.HospitalRepository;


public class HospitalUserDetailsServiceer implements UserDetailsService {

	@Autowired
	private HospitalRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser foundUser = userRepository.findByUserName(username).orElseThrow(() ->  new UsernameNotFoundException("The user is not presented"));
		
		User user = new User(foundUser.getName(), foundUser.getPassword(), grantedAutority(foundUser));
		return user;
	}

	private Collection<? extends GrantedAuthority> grantedAutority(AppUser appUser) {
		List<SimpleGrantedAuthority> auths = appUser.getAppRoles().stream()
				.map(a -> new SimpleGrantedAuthority(a.getName()))
				.toList();
						
		return auths;
	}

	
	
}
