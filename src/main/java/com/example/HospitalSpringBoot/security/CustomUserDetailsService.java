package com.example.HospitalSpringBoot.security;


import com.example.HospitalSpringBoot.entities.User;
import com.example.HospitalSpringBoot.enums.services.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service("customUserDetailsService")
@Log
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserService utenteService;
	
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		String ErrMsg = "";
		
		if (username == null) {
			ErrMsg = "Nome utente assente o non valido";
			log.warning(ErrMsg);
	    	throw new UsernameNotFoundException(ErrMsg); 
		}
		User user = this.utenteService.getByUsername(username);
		log.info("Username is : " + user.getUsername() + "Passoword is :"+user.getPassword());
		if (user == null) {
			ErrMsg = String.format("Utente %s non Trovato!!", username);
			log.warning(ErrMsg);
			throw new UsernameNotFoundException(ErrMsg);
		}
		
		UserBuilder builder = null;
		builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
		builder.password("{noop}" + user.getPassword());
		builder.authorities("ROLE_" + user.getRole());
		return builder.build();
	}
}
	