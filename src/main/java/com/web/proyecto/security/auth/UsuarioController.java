package com.web.proyecto.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.proyecto.service.lpml.UsuarioService;

import jakarta.persistence.Table;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService serUsu;
	
	
	@RequestMapping("/login")
	public String vistaUsuario() {
	
		return "login";
		
	}
	
	@RequestMapping("/logout")
	public String SesionCerrada() {
		
		return "login";
	}
	
	
	@RequestMapping("/noautorizado")
	public String NoAutorizado() {
		return "login";
	}
	
	
}
