package com.web.proyecto.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.web.proyecto.entity.Cuota;
import com.web.proyecto.entity.Solictud;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.service.lpml.CuotaService;
import com.web.proyecto.service.lpml.SolicitudService;
import com.web.proyecto.service.lpml.UsuarioService;

@Controller
@RequestMapping("/Consulta")
public class CuotaConsultaController {
		
	@Autowired
	private CuotaService service;
	@Autowired
	private UsuarioService serUsu;
	@Autowired
	private SolicitudService serSo;
	
	
	@RequestMapping("/cuotas")
	public String listarCuota(Model model, Authentication auth) {
		String userame=auth.getName();
        Usuario datos=serUsu.usuarioPorUsername(userame);
        Integer codigoUsuario=datos.getCodigoUsuario();
        
        model.addAttribute("listaCuotasPrestatario", serSo.listaCoutasPrestatario(codigoUsuario));
        model.addAttribute("UserLogeado", codigoUsuario);
		//model.addAttribute("listCuotas", service.listarTodos());
				
		return "Consulta";
	}
	
	/*Julio Ynca*/
	@RequestMapping("/FiltroCoutasJSON")
	@ResponseBody
	public List<Object[]> consultarCuotasJSON(@RequestParam("fechaInicio") String fechaInicioStr,
	                                          @RequestParam("fechaFin") String fechaFinStr,
	                                          @RequestParam("codigoUsuario") Integer codigoUsuario) {
	    Date fechaInicio = Date.valueOf(LocalDate.parse(fechaInicioStr));
	    Date fechaFin = Date.valueOf(LocalDate.parse(fechaFinStr));

	    return serSo.listaCoutasPrestatarioFechaVencimiento(codigoUsuario, fechaInicio, fechaFin);
	}




	/*Julio Ynca*/
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/filtrar-por-fechas")
    public ResponseEntity<List<Cuota>> filtrarPorFechas(@RequestParam String fechaInicio,
                                                        @RequestParam String fechaFin) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicioLocalDate = LocalDate.parse(fechaInicio, formatter);
        LocalDate fechaFinLocalDate = LocalDate.parse(fechaFin, formatter);
        
        
        
        List<Cuota> cuotasFiltradas = service.listarEntreFechasPago(
        		Date.valueOf(fechaInicioLocalDate),
                Date.valueOf(fechaFinLocalDate)
                );
        return ResponseEntity.ok(cuotasFiltradas);
    }
}
