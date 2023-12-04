package com.web.proyecto.security.auth;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Usuario;
import com.web.proyecto.service.lpml.UsuarioService;

@Service
public class UserSecurity implements UserDetailsService{

	
	@Autowired
	private UsuarioService serUsu;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetails user=null;
		
		Usuario datos=serUsu.usuarioPorUsername(username);
		
		Set<GrantedAuthority> rol=new HashSet<GrantedAuthority>();
		
		/*************************NOMBRE USUARIO***********************************************/
		
		String nombre = null;
		
		     try {	    
			  nombre =serUsu.rolPorCodigoDeUsuario(datos.getCodigoUsuario()).getNombrerol();
					   					
			} catch (Exception e) {
				nombre ="no cuenta con rol";			
			}				
		/************************************************************************/
		
		rol.add(new SimpleGrantedAuthority(nombre));
		
		user=new User(username, datos.getPassword(), rol);
		

		return user;
	}

	
	
}
