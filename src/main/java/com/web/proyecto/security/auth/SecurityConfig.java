package com.web.proyecto.security.auth;

import java.security.AuthProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	   
	    
	
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 
		 return http
				 .csrf(csrf ->
				 csrf
				 .disable())
				 .authorizeHttpRequests(authRequest ->
				 {
					try {
						authRequest
						 .requestMatchers("/usuario/**","/RegisitrarPago/**").permitAll()
						 .anyRequest().authenticated()
						 .and()
						 .formLogin(formLogin ->
						 formLogin
						 .loginPage("/usuario/login")
						 .defaultSuccessUrl("/ruta/usuario"))
						 .logout(logout ->
						 {
							try {
								logout
								 .logoutUrl("/logout")
								 .logoutSuccessUrl("/usuario/logout")
								 .and()
								 .exceptionHandling()
								 .accessDeniedPage("/usuario/noautorizado");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				})
		         .build();
		 
	 }
	  
	  
	  
}
