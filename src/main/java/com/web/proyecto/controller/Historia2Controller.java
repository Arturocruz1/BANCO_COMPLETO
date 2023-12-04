package com.web.proyecto.controller;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.Cuota;
import com.web.proyecto.entity.Prestamo;
import com.web.proyecto.entity.Solictud;
import com.web.proyecto.entity.TipoPrestamo;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.service.lpml.CuotaService;
import com.web.proyecto.service.lpml.PrestamoService;
import com.web.proyecto.service.lpml.SolicitudService;
import com.web.proyecto.service.lpml.UsuarioService;

import java.time.temporal.ChronoUnit;

@Controller
@RequestMapping("/h2")
public class Historia2Controller {

	
	@Autowired
	private SolicitudService serSoli;
	
	@Autowired
	private CuotaService serCuota;
	
	@Autowired
	private PrestamoService serPre;
	
	@Autowired
	private UsuarioService serUsu;
	
	
	//AQUI TIENES QUE PROGRAMACAR ARTURO
	/*@RequestMapping("/lista")
	public String listarTodos( Model model) {
		model.addAttribute("listSolicitud", serSoli.listarTodos());
		
		
		return "historia2";
	
	}*/
	
	@GetMapping("/vista")
	public String vistaPrincipal(Model model) {
		model.addAttribute("listSolicitud", serSoli.listarTodos());
		model.addAttribute("listaUserPretatarios", serUsu.listaUsuarioPrestatarios());
		
		return "historia2";
	}
	
	/*
	@RequestMapping("/consulta")
	@ResponseBody
	public List<Solictud> consulta(@RequestParam("vdesde") Date vdesde,@RequestParam("vhasta")Date vhasta ) {
		return serSolitu.listaParametros(vdesde, vhasta);
	}
	*/

	@PostMapping("/fechass")
	public String vistaPorFechas(@RequestParam("fechaInicial")String fecha1,@RequestParam("fechaFinal")String fecha2,Model model) {
		
		List<Solictud> listafecha=new ArrayList<>();
		
		if(fecha1=="" || fecha2=="") {
			model.addAttribute("listSolicitud", serSoli.listarTodos());
			System.out.println("-----1");
			return "historia2";
		}
		System.out.println("-----2");
    	 listafecha=serSoli.listaPorFecha(fecha1, fecha2);
		model.addAttribute("listSolicitud", listafecha);
		return "historia2";
	}
	
	/*Filtro por los 3 parametros*/
	@RequestMapping("/listaFiltradaJSON")
	@ResponseBody
	public List<Solictud> listadoPorFiltros(@RequestParam("codPreta") Integer vcod, 
											@RequestParam("fechaInicio") Date vInicio, 
											@RequestParam("fechaFin") Date vFin){
		return serSoli.listaSolicitudesPorFiltros(vcod, vInicio, vFin);
	}
	
	/**/
	
	
	
	//rodolfo
	@ResponseBody
	@GetMapping("soli/{cod}")
	public Solictud solicitudPorCodigo(@PathVariable("cod")int cod) {
		
		Solictud obj=serSoli.buscarPorId(cod);
		
		return obj;
		
	}
	
	//rodolfo
	@RequestMapping("/actualizar/estado/{codUsuario}/{estado}")
    public String actualizarestado(RedirectAttributes redirect,@PathVariable("codUsuario")int x,@PathVariable("estado")int y) {
		Solictud obj = serSoli.buscarPorId(x);
		/*-------------------------USUARIO LOGEADO---------------------------------*/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Usuario usuario = null;
	    if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
	        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
	        String username = user.getUsername();
	        Optional<Usuario> usuarioOptional = serUsu.findByUsername(username);
	        usuario = usuarioOptional.orElse(null);
	    }

	    if (usuario != null) {
	        if (y == 1) {
	            redirect.addFlashAttribute("MENSAJE", "en duda");
	            return "redirect:/h2/vista";
	        } else if (y == 2) {
	        	/*-------------------------REGISTRO DE PRESTAMO---------------------------------*/	        
	        	
	        	
	            Prestamo p = new Prestamo();
	            p.setMonto(obj.getSolimonto() + obj.getInteres());
	            p.setFecResgistro(LocalDate.now());
	            p.setFecModificacion(LocalDate.now());
	            TipoPrestamo tp = new TipoPrestamo();
	            tp.setCodigoTipoPrestamo(1);
	            p.setTbTipoPrestamo(tp);
	            p.setTbListUsuarioPrestamista(usuario);
	            p.setTbListUsuarioPrestatario(obj.getUsuarioprestatariosoli());
	            p.setTbListUsuarioRegistra(usuario);
	            p.setTbListUsuarioActualiza(usuario);
	            p.setListaPrestamos(obj);
	            serPre.registrar(p);

	            /*-------------------------REGISTRO DE CUOTA---------------------------------*/
	            long dDias = TimeUnit.MILLISECONDS.toDays(obj.getFecha_fin_prestamo().getTime() - obj.getFechainiciopresta().getTime() + 86400000);
	            double fina = (obj.getSolimonto() + obj.getInteres()) / dDias;
	            Date fechaCuota = obj.getFechainiciopresta();

	            for (int numC = 1; numC <= dDias; numC++) {
	                Cuota nc = new Cuota();
	                nc.setNumeroCuota(numC);
	                nc.setMonto(fina);
	                java.sql.Date fechaPagoSql = new java.sql.Date(fechaCuota.getTime());
	                nc.setFechaPago(fechaPagoSql);
	                nc.setFechaRegistro(LocalDate.now());
	                nc.setEstadoCuota(2);
	                nc.setUsuarioregistrocuota(usuario);
	                nc.setListaSolicitud(obj);
	                serCuota.registrar(nc);
	                long aum = 86400000;
	                fechaCuota = new Date(fechaCuota.getTime() + aum);
	            }

	            // Actualiza el estado de la solicitud
	            obj.setUsuarioregistrosoli(usuario);
	            obj.setEstadosoli(y);
	            serSoli.registrar(obj);

	            redirect.addFlashAttribute("MENSAJE", "Aprobado");
	            return "redirect:/h2/vista";
	        } else {
	            redirect.addFlashAttribute("MENSAJE", "Desaprobado");
	            return "redirect:/h2/vista";
	        }
	    } else {
	        // Manejar el caso en el que no se encuentra un usuario
	        redirect.addFlashAttribute("MENSAJE", "Error al obtener el usuario");
	        return "redirect:/h2/vista";
	    }
	
	}
	
}
