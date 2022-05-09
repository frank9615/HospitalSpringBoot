package com.example.HospitalSpringBoot.security;

import java.net.URI;
import java.net.URISyntaxException;


import com.example.HospitalSpringBoot.entities.Utente;
import com.example.HospitalSpringBoot.services.IUtenteService;
import com.example.HospitalSpringBoot.services.impl.UtenteService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;

@Service("customUserDetailsService")
@Log
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUtenteService utenteService;
	
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String username) {
		String ErrMsg = "";
		
		if (username == null) {
			ErrMsg = "Nome utente assente o non valido";
			log.warning(ErrMsg);
	    	throw new UsernameNotFoundException(ErrMsg); 
		}
		Utente utente = this.utenteService.getByUsername(username);
		if (utente == null) {
			ErrMsg = String.format("Utente %s non Trovato!!", username);
			log.warning(ErrMsg);
			throw new UsernameNotFoundException(ErrMsg);
		}
		
		UserBuilder builder = null;
		builder = org.springframework.security.core.userdetails.User.withUsername(utente.getUsername());
		builder.password("{noop}" + utente.getPassword());
		builder.authorities("ROLE_" + utente.getRole());
		return builder.build();
	}
}
	