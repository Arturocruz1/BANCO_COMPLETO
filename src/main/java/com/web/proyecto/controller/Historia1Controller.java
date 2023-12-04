package com.web.proyecto.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.BancoHasUsuario;
import com.web.proyecto.entity.Solictud;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.service.lpml.BancoHasUsuarioService;
import com.web.proyecto.service.lpml.SolicitudService;
import com.web.proyecto.service.lpml.UsuarioService;
import com.web.proyecto.services.SolicitudregService;

@Controller
@RequestMapping("/Solicitud")
public class Historia1Controller {
	
	@Autowired
	private SolicitudService serSoli;
	
	@Autowired
	private BancoHasUsuarioService serBancoHasUsu;
	
	@Autowired
	private UsuarioService serUsu;
	
	@Autowired
	private SolicitudregService serSoliReg;

	@RequestMapping("/lista")
	public String index(Authentication auth, Model model) {
		
		model.addAttribute("generarNum", serSoli.GenerarNumeroSolicitud());
		model.addAttribute("listarCuentas", serBancoHasUsu.listarCuentas());
		
		String userame=auth.getName();
        Usuario datos=serUsu.usuarioPorUsername(userame);
        int codigoUsuario=datos.getCodigoUsuario();

		List<BancoHasUsuario> listadoCuenta=serBancoHasUsu.listarCuentasPorCodigo(codigoUsuario);

		// para grupo jefe prestamista
        model.addAttribute("listarCuentas",listadoCuenta);
        model.addAttribute("codigoUser", datos.getCodigoUsuario());

		model.addAttribute("generarNum", serSoli.GenerarNumeroSolicitud());
		//model.addAttribute("listarCuentas", serBancoHasUsu.listarCuentas());
		
		return "Solicitud";
	}
	
	// REGISTRAR SOLICITUD
	@RequestMapping("/grabar")
	public String grabar(
			@RequestParam("monto") Double monto,
			@RequestParam("fecIni") Date fecIni,
			@RequestParam("fecFin") Date fecFin,
			// fecha registro
			// usuario prestatario
			// usuario registro
			// estado
			@RequestParam("numSoli") String numSoli,
			// interes
			@RequestParam("numCuenta") String numCuenta,
			RedirectAttributes redirect, Authentication auth){
		try {
			//
			String userame=auth.getName();
		    Usuario datos=serUsu.usuarioPorUsername(userame);
		    int codigoUsuario=datos.getCodigoUsuario();
			//
		    
		    LocalDate localDate = LocalDate.now();
		    
		    List<Solictud> listaSolicitudesHoyDia = serSoli.BuscarSolicitudPorFechaUsuario(localDate,
					codigoUsuario);
		    
		 // Verificar que no tenga registros de prestamos hoy dia
			if (listaSolicitudesHoyDia.size() >= 2) {				
				redirect.addFlashAttribute("MENSAJE","NO PUEDE REGISTRAR, PORQUE YA TIENE REGISTRADO UN PRESTAMO EL DÍA DE HOY");
				return "redirect:/Solicitud/lista";
			}
		    
		    
		    
		    
			Solictud s = new Solictud();
			//s.setCodigosoli(1);	
			s.setSolimonto(monto);
			s.setFechainiciopresta(fecIni);
			s.setFecha_fin_prestamo(fecFin);
			s.setFecharegistro(LocalDate.now());
			
			Usuario usu=serUsu.buscarPorId(codigoUsuario);
			s.setUsuarioprestatariosoli(usu);
			s.setUsuarioregistrosoli(usu);
			
			s.setEstadosoli(1);
			s.setNumero(numSoli);
			
				// Aplicar diferentes porcentajes de interés según el monto
		        double porcentajeInteres;
		        if (monto <= 1000) {
		            porcentajeInteres = 0.05; // 3% para montos hasta 1000
		        } else if (monto > 1000 && monto <= 10000) {
		            porcentajeInteres = 0.015; // 5% para montos hasta 5000
		        } else {
		            porcentajeInteres = 0.25; // 8% para montos mayores a 5000
		        }
	
		        double interes = monto * porcentajeInteres;
			
			
			s.setInteres(interes);
			s.setNumCuenta(numCuenta);
			serSoliReg.registrar(s);

			redirect.addFlashAttribute("MENSAJE","Inscripcion registrada");
			
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registro");
			e.printStackTrace();
		}
		
		return "redirect:/Solicitud/lista";
	}
	
}
