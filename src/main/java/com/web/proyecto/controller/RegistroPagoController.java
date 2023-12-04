package com.web.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registropago")
public class RegistroPagoController {

	//AQUI TIENES QUE PROGRAMACAR ARTURO
		@RequestMapping("/lista")
		public String listarTodos( Model model) {
			
			
			return "Registropagos";
		
		}
}
