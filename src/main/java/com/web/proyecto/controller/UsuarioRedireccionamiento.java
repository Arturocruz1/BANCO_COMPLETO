package com.web.proyecto.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.Grupo;
import com.web.proyecto.entity.Opcion;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.service.lpml.OpcionService;
import com.web.proyecto.service.lpml.UsuarioHasRolccccService;
import com.web.proyecto.service.lpml.UsuarioService;
import com.web.proyecto.services.GrupoJefeService;

@Controller
@SessionAttributes({"username","codigoUsuario","listaOpcion","nombreRol"})
@RequestMapping("/ruta")
public class UsuarioRedireccionamiento {

	@Autowired
	private UsuarioService serUsu;
	
	@Autowired
	private OpcionService serOpcion;
	
	

	
	
	@RequestMapping("/usuario")
	public String direccion(Authentication auth,Model model,RedirectAttributes redirect) {
		
		String userame=auth.getName();
		Usuario datos=serUsu.usuarioPorUsername(userame);
		int codigoUsuario=datos.getCodigoUsuario();
		
		String nombre = null;
		int cod=serUsu.rolPorCodigoDeUsuario(codigoUsuario).getCodigo();
		
		try {	    
		  nombre =serUsu.rolPorCodigoDeUsuario(codigoUsuario).getNombrerol();
				   					
		} catch (Exception e) {
			nombre ="no cuenta con rol";			
		}
		
		
		
		List<Opcion> lista=serUsu.listaDeOpcionesPorCodigoDeRol(cod);
		
		//trim() se usa para devolver una cadena sin espacios
		
		model.addAttribute("listaOpcion",lista);
		model.addAttribute("username",datos.getUsername());
	    model.addAttribute("codigoUsuario",datos.getCodigoUsuario());
	    model.addAttribute("nombreRol",nombre);
	    
		
		
		return "index";
	}
	
	
	
	
}
