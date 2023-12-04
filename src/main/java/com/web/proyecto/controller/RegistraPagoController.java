package com.web.proyecto.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.Cuota;
import com.web.proyecto.entity.FormaPago;
import com.web.proyecto.entity.Pago;
import com.web.proyecto.entity.Solictud;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.repository.UsuarioRepository;
import com.web.proyecto.service.lpml.CuotaService;
import com.web.proyecto.service.lpml.FormaPagoService;
import com.web.proyecto.service.lpml.PagoServiceImpl;
import com.web.proyecto.service.lpml.SolicitudService;
import com.web.proyecto.service.lpml.UsuarioService;

@Controller
@RequestMapping("/RegisitrarPago")
public class RegistraPagoController {

	
	
	@Autowired
	private SolicitudService serSoli;

	@Autowired
	private UsuarioService serUsu;

	@Autowired
	private FormaPagoService serFormaPago;
	
	@Autowired
	private PagoServiceImpl serPagoService;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private CuotaService serCuota;
	
	
	@GetMapping("/archivoPrincipalss")
	@ResponseBody
	public List<FormaPago> lisdsta() {
		
	List<Usuario> lista=serSoli.listaDeUsuarioPorRol(4);
	List<Usuario> lista2=serUsu.listaUsuarioPrestatarios();	
	List<FormaPago> listaFormapag=serFormaPago.listarFormasPago();
	
	return listaFormapag;
	
	}

	
	
	@GetMapping("/archivoPrincipal")
	public String lista(Model model) {
		
	//List<Usuario> lista=serSoli.listaDeUsuarioPorRol(4);
		List<Usuario> lista=serUsu.listaUsuarioPrestatarios();	
		List<FormaPago> listaFormapag=serFormaPago.listarFormasPago();
		
		//System.out.println(listaFormapag);
		//System.out.println(lista);
		
		List<Object[]> listtaCuotasTodas = serSoli.listaCuotasTodosPrestatarios();
	model.addAttribute("listUsuario", lista);
	model.addAttribute("listaCuotas", listtaCuotasTodas);
	model.addAttribute("formaPago", listaFormapag);
	
	return "Registropagos";
	
	}
	/* Julio Ynca */
	@RequestMapping("/FiltroCoutasPagoJSON")
	@ResponseBody	
	public List<Object[]> consultaCoutaPagosJSON(	@RequestParam("fechaInicio") Date fechaInicioStr,
										            @RequestParam("fechaFin") Date fechaFinStr,
										            @RequestParam("codPrestatario") Integer codigoUsuario){
		
		return serSoli.listaCuotasPretatarioFiltros(codigoUsuario, fechaInicioStr, fechaFinStr);
	}
	
	/* Julio Ynca */
	
	
	
	
	@PostMapping("/fechass")
	public String vistaPorFechas(@RequestParam("fechaInicial")String fecha1,@RequestParam("fechaFinal")String fecha2,@RequestParam("usuario")int usu,Model model) {
		
		List<Cuota> listafecha=new ArrayList<>();
		
		if(fecha1=="" || fecha2=="" || usu==0) {
			List<Usuario> lista=serSoli.listaDeUsuarioPorRol(4);
			model.addAttribute("listUsuario", lista);
			System.out.println("-----1");
			return "historia2";
		}
		System.out.println("-----2");
		List<Usuario> lista=serSoli.listaDeUsuarioPorRol(4);
		model.addAttribute("listUsuario", lista);
    	 listafecha=serSoli.listaDeCuotaPorFecha(fecha1, fecha2, usu);
		model.addAttribute("listSolicitud", listafecha);
		return "historia2";
	}
	
	@GetMapping("/buscarPorId/{cod}")
	@ResponseBody	
	public Cuota buscaree(@PathVariable("cod") int cod) {
		
		Cuota objCuota=serCuota.buscarPorId(cod);	
		return objCuota;
	}
	
	@PostMapping("/registrarCuota")
	public String registrarCuota(@RequestParam("codigoCuota")int codCuota,@RequestParam("codigoFormapago")int codFormaPago,@RequestParam("nombrePrestario")String nombreUsuario
			,@RequestParam("monto")double montos,@RequestParam("montoPagoUsuario")double montoUsuario,Authentication auth,RedirectAttributes redirect) {
		
	String nombreusu=auth.getName();
	Usuario usu=repoUsuario.findByUsername(nombreusu);
	Cuota objCuota=serCuota.buscarPorId(codCuota);
	FormaPago objFormaPago=serFormaPago.buscarPorID(codFormaPago);
	int number=-1;
	BigDecimal montofinal;
	/*
	 * 1:pagado
	 * 2:activo
	 * 3:parcialmente pagado
	 * 4:excede el pago
	*/
	
	
	BigDecimal restaDeudaMontousuario=BigDecimal.valueOf(montoUsuario).subtract(BigDecimal.valueOf(objCuota.getDeuda()));;
	
	System.out.println(restaDeudaMontousuario);
	
	if(restaDeudaMontousuario.compareTo(BigDecimal.ZERO)==0) {
		number=3;
		//actualizamos la tabla de cuota 
			//	objCuota.setMonto(montofinal);
				objCuota.setDeuda(0);
				objCuota.setEstadoCuota(3);
				serCuota.actualizar(objCuota);
				
				Pago pp=new Pago();	
				
				
				//Cuota cuo=new Cuota(codCuota);
				pp.setCuotaPago(objCuota);
				pp.setMontopago(Double.parseDouble(""+BigDecimal.valueOf(montoUsuario)));
				pp.setFecha(LocalDate.now());
				pp.setUsuarioregistro(usu);
				pp.setGrupoFormaPago(objFormaPago);
				pp.setEstado(number);	
				
				serPagoService.registrar(pp);
				//repoUsuario	
				//serPagoService	
					return "redirect:/RegisitrarPago/archivoPrincipal";
				
	}else if(restaDeudaMontousuario.compareTo(BigDecimal.ZERO)<0) {
		number=3;
		//actualizamos la tabla de cuota 
		//	objCuota.setMonto(montofinal);
			objCuota.setDeuda(Double.parseDouble(""+BigDecimal.valueOf(objCuota.getDeuda()).subtract(BigDecimal.valueOf(montoUsuario))));
			objCuota.setEstadoCuota(3);
			serCuota.actualizar(objCuota);
			
			Pago pp=new Pago();	
			
			
			//Cuota cuo=new Cuota(codCuota);
			pp.setCuotaPago(objCuota);
			pp.setMontopago(Double.parseDouble(""+BigDecimal.valueOf(montoUsuario)));
			pp.setFecha(LocalDate.now());
			pp.setUsuarioregistro(usu);
			pp.setGrupoFormaPago(objFormaPago);
			pp.setEstado(number);	
			
			serPagoService.registrar(pp);
			//repoUsuario	
			//serPagoService	
				return "redirect:/RegisitrarPago/archivoPrincipal";
				
	}else {

		double montoSuma=Double.parseDouble(""+BigDecimal.valueOf(objCuota.getMonto()).add(BigDecimal.valueOf(objCuota.getDeuda())));
		montofinal=BigDecimal.valueOf(objCuota.getMonto()).subtract(restaDeudaMontousuario);
		System.out.println(montofinal);
			
		if(montofinal.compareTo(BigDecimal.ZERO)==0) {
			
					number=1;
					
					//actualizamos la tabla de cuota 
					objCuota.setMonto(Double.parseDouble(""+montofinal));
					objCuota.setDeuda(0);
					objCuota.setEstadoCuota(number);
					serCuota.actualizar(objCuota);
					
					
				Pago pp=new Pago();	
			
				
				//Cuota cuo=new Cuota(codCuota);
				pp.setCuotaPago(objCuota);
				pp.setMontopago(montoSuma);
				pp.setFecha(LocalDate.now());
				pp.setUsuarioregistro(usu);
				pp.setGrupoFormaPago(objFormaPago);
				pp.setEstado(number);	
				
				serPagoService.registrar(pp);
				//repoUsuario	
				//serPagoService	
					return "redirect:/RegisitrarPago/archivoPrincipal";
			
			
		}else if(montofinal.compareTo(BigDecimal.ZERO)<0) {
			number=4;
			
			return "redirect:/RegisitrarPago/archivoPrincipal";
			
		}else {
			number=3;
			
					//actualizamos la tabla de cuota 
					objCuota.setMonto(Double.parseDouble(""+montofinal));
					objCuota.setDeuda(0);
					objCuota.setEstadoCuota(number);
					serCuota.actualizar(objCuota);
					
					
				Pago pp=new Pago();	
				
				
				//Cuota cuo=new Cuota(codCuota);
				pp.setCuotaPago(objCuota);
				pp.setMontopago(Double.parseDouble(""+BigDecimal.valueOf(montoUsuario)));
				pp.setFecha(LocalDate.now());
				pp.setUsuarioregistro(usu);
				pp.setGrupoFormaPago(objFormaPago);
				pp.setEstado(number);	
				
				serPagoService.registrar(pp);
				//repoUsuario	
				//serPagoService	
					return "redirect:/RegisitrarPago/archivoPrincipal";
			
			
		}
		
		
	   }
	}
	
	
	
	
	
	
}
