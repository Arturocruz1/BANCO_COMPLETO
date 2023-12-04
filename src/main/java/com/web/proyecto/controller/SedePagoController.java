package com.web.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.SedePago;
import com.web.proyecto.service.lpml.SedePagoService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/SedePago")
public class SedePagoController {
	
	@Autowired
	private SedePagoService serSedP;
	
	
	@RequestMapping("/lista")
	private String index(Model model) {
		model.addAttribute("sedepago", serSedP.listarSedePagos());
		return "SedePago";
	}
	
	@RequestMapping("/grabar")
	private String grabar(
			@RequestParam("id_sede_pago") int cod,
			@RequestParam("nombre_sede") String nombreSede,
			@RequestParam("estado") int estado,
			@RequestParam("tipo_moneda") String tipoMoneda,
			RedirectAttributes redirect) {
		try {
			SedePago s = new SedePago();
			s.setId_sede_pago(cod);
			s.setNombre_sede(nombreSede);
			s.setEstado(estado);
			s.setTipo_moneda(tipoMoneda);
			
			serSedP.registrar(s);
		}catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registro");
			e.printStackTrace();
		}
		redirect.addFlashAttribute("MENSAJE","Se registro exitosamente el sede de pago: " + cod);
		return "redirect:/SedePago/lista";
	}
	
	@RequestMapping("/actualizar")
	private String actualizar (
			@RequestParam("id_sede_pago") int cod,
			@RequestParam("nombre_sede") String nombreSede,
			@RequestParam("estado") int estado,
			@RequestParam("tipo_moneda") String tipoMoneda,
			RedirectAttributes redirect) {
		try {
			SedePago s = new SedePago();
			s.setNombre_sede(nombreSede);
			s.setEstado(estado);
			s.setTipo_moneda(tipoMoneda);
			
			if(cod==0) {
				redirect.addFlashAttribute("MENSAJE","El codigo no existe");
			}else {
				s.setId_sede_pago(cod);
				serSedP.actualizar(s);
				
				redirect.addFlashAttribute("MENSAJE", "Sede Pago "+nombreSede+" actualizado");
			}
		}catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE", "Error en el actualizar");
			e.printStackTrace();
		}
		return "redirect:/SedePago/lista";
	}
	
	//Buscar por codigo
	@RequestMapping("/buscar")
	@ResponseBody
	private SedePago buscarPorCodigo(
			@RequestParam("codigo") Integer cod) {
			return serSedP.buscarPorID(cod);
	}
	
			
	

}
