package com.web.proyecto.controller;



import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.proyecto.entity.Solictud;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.service.lpml.UsuarioService;



@Controller
@RequestMapping("/prestamogrupo")
public class PrestamosPorGrupoController {
	
	@Autowired
	private UsuarioService serUsu;
	
	
	@GetMapping("/lista")
	public String Index(Model model){
	
		List<Usuario> listaUserJefe = serUsu.listadeUsuarioporcodRol(3);
		model.addAttribute("listaUseJefe", listaUserJefe);
		return "prestamosporgrupo";
	};
	
	@RequestMapping("/listaFiltradaJSON")
	@ResponseBody
	public List<Object[]> listadoPorFiltros(@RequestParam("codJefe") Integer vcod){
		return serUsu.obtenerRentabilidad(vcod);
	}
	
	
}
