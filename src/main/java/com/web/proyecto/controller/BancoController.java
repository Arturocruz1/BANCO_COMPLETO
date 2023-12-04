package com.web.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.Banco;
import com.web.proyecto.service.lpml.ServiceBanco;

import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/banco")
public class BancoController {
	@Autowired
	private ServiceBanco serBancos;
	
	
	//mapear la ruta @GetMapping
	@GetMapping("/lista")
	public String lista(Model model) {
		
		model.addAttribute("bancolista",serBancos.listarTodos());
		return "banco";
	}
	//RUTA (URL) PARA GRABAR Banco
			@RequestMapping("/grabar")
			public String grabar(@RequestParam("codigo") Integer cod,
					@RequestParam("nombre") String nom,
					@RequestParam("estado") Integer est,
					RedirectAttributes redirect
					) {
				try {
					//CREAR OBJETO DE LA ENTIDAD MEDICAMENTO
					Banco ban=new Banco();
					ban.setNombreBanco(nom);
					ban.setEstadoBanco(est);
					//CREAR OBJETO DE LA ENTIDAD "TIPOMEDICAMENTO"
					//TipoMedicamento tm=new TipoMedicamento();
					//tm.setCodigo(codTipo);
					//ENVIAR OBJETO "TM" DENTRO DEL OBJETO "MET" INVCAR AL METODO SET TIPO
					serBancos.registrar(ban);
					if(cod==0) {
						serBancos.registrar(ban);
					}
					else {
						ban.setCodigoBanco(cod);
						serBancos.actualizar(ban);
						redirect.addFlashAttribute("MENSAJE","Banco Actualizado correctamente");
					}
					serBancos.registrar(ban);
					//enviar atributo
					redirect.addFlashAttribute("MENSAJE","Banco registrado");
					
				} catch (Exception e) {
					redirect.addFlashAttribute("MENSAJE","*** Error en el registro");
					e.printStackTrace();
				}
				//String nom=request.getParamenter("nombre");
				return "redirect:/banco/lista";
			}
			
			@ResponseBody
			@GetMapping("/buscar/{cod}")
			public Banco BancoPorId(@PathVariable("cod")Integer cod) {
				return serBancos.buscarPorId(cod);
			}
}
