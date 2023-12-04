package com.web.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.FormaPago;
import com.web.proyecto.service.lpml.FormaPagoService;


@Controller
@RequestMapping("/FormaPago")
public class FormaPagoController {
	
	@Autowired
	private FormaPagoService serFormP;
	
	// LISTAR TABLA
	@RequestMapping("/lista")
	private String index(Model model) {
		model.addAttribute("formapago", serFormP.listarFormasPago());
		
		return "FormaPago";
	}
	
	// REGISTRAR
	@RequestMapping("/grabar")
	private String grabar(
			@RequestParam("id_forma_pago") int cod,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("estado") int estado,
			RedirectAttributes redirect){
		try {
			//
			FormaPago i = new FormaPago();
			i.setIdFormaPago(cod);
			i.setDescripcion(descripcion);
			i.setEstado(estado);
			//
			serFormP.registrar(i);
			//
			
			
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registro");
			e.printStackTrace();
		}
		redirect.addFlashAttribute("MENSAJE","Se registro exitosamente la forma de pago: " + cod);
		return "redirect:/FormaPago/lista";
	}
	
	// ACTUALIZAR
	@RequestMapping("/actualizar")
	private String actualizar(
			@RequestParam("IdFormaPago") int cod,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("estado") int estado,
			RedirectAttributes redirect){
		try {
			//
			FormaPago i = new FormaPago();
			i.setDescripcion(descripcion);
			i.setEstado(estado);
			//
			if(cod==0) {
				// 
				redirect.addFlashAttribute("MENSAJE","El codigo no existe");
			} else {
				i.setIdFormaPago(cod);
				serFormP.actualizar(i);
				//
				redirect.addFlashAttribute("MENSAJE","Forma de pago "+descripcion+" actualizada");
			}
		
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el actualizar");
			e.printStackTrace();
		}		
		return "redirect:/FormaPago/lista";
	}
	
	// BUSCAR POR CODIGO
	@RequestMapping("/buscar")
	@ResponseBody
	private FormaPago buscarPorCodigo(
			@RequestParam("codigo") Integer cod) {
			return serFormP.buscarPorID(cod);
	}
	
	
	
}
