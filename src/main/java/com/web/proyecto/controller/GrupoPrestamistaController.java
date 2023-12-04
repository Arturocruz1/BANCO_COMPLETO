package com.web.proyecto.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.FormaPago;
import com.web.proyecto.entity.Grupo;
import com.web.proyecto.entity.Ubigeo;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.response.ResponseHandler;
import com.web.proyecto.service.lpml.UsuarioService;
import com.web.proyecto.services.GrupoJefeService;

@Controller
@RequestMapping("/GrupoPrestamistas")
public class GrupoPrestamistaController {

	@Autowired
	private GrupoJefeService serGrupo;

	@Autowired
	private UsuarioService serUsu;
	
	@GetMapping("/lista")
	public String lista(Authentication auth, Model model) {
		
		String userame = auth.getName();
		Usuario user = serUsu.usuarioPorUsername(userame);
		model.addAttribute("user", user);
		
		return "GrupoPrestamistas";
	}

	// Get
	@GetMapping("/libres")
	public ResponseEntity<Object> GetPrestamistasLibres() {
		try {
			List<Usuario> result = serGrupo.getPrestamistasLibres();
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	// Get(/integrantes)
	@GetMapping("/integrantes")
	public ResponseEntity<Object> GetIntegrantes(Authentication auth) {
		try {
			
			String userame = auth.getName();
			Usuario usuarioLogeado = serUsu.usuarioPorUsername(userame);
			int codigoJefe = usuarioLogeado.getCodigoUsuario();

			List<Usuario> usuarios = serGrupo.getIntegrantesPorJefe(codigoJefe); // serUsu.listarIntegrantesPorGrupo(usuarioLogeado.getCodigoUsuario());
			return ResponseHandler.generateResponse("Successfully retrieved data GetIntegrantes!", HttpStatus.OK,
					usuarios);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}

	@RequestMapping("/actualizar")
	private String actualizar(@RequestParam("IdGrupoPago") int cod, @RequestParam("descripcion") String descripcion,
			@RequestParam("estado") int estado, @RequestParam("fecha") LocalDate fecha, RedirectAttributes redirect) {
		try {
			if (cod == 0) {
				redirect.addFlashAttribute("MENSAJE", "El código no existe");
			} else {
				Grupo grupo = serGrupo.buscarPorID(cod);

				if (grupo == null) {
					redirect.addFlashAttribute("MENSAJE", "El código no existe");
				} else {
					grupo.setDescripcionGrupo(descripcion);
					grupo.setEstadoGrupo(estado);
					grupo.setFechaGrupo(fecha);

					serGrupo.actualizar(grupo);

					redirect.addFlashAttribute("MENSAJE", "Grupo actualizado");
				}
			}
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE", "Error en la actualización");
			e.printStackTrace();
		}
		return "redirect:/GrupoPrestamistas/lista";
	}

	@RequestMapping("/buscar")
	@ResponseBody
	private Grupo buscarPorCodigo(@RequestParam("codigo") Integer cod) {
		return serGrupo.buscarPorID(cod);
	}

	@RequestMapping("/integrantes/add")
	public ResponseEntity<Object> addIntegranteToGroup(
			@RequestParam("codigoJefe") int codigoJefe, @RequestParam("codigoIntegrante") int codigoIntegrante) {
		try {
			Grupo grupo = new Grupo();
			grupo.setFechaGrupo(LocalDate.now());
			grupo.setEstadoGrupo(1);
			grupo.setDescripcionGrupo("");

			Ubigeo ubigeo = new Ubigeo();
			ubigeo.setCodigo(1111);
			grupo.setTbUbigeo(ubigeo);

			Usuario jefe = new Usuario();
			jefe.setCodigoUsuario(codigoJefe);
			grupo.setUsuarioLider(jefe);

			Usuario integrante = new Usuario();
			integrante.setCodigoUsuario(codigoIntegrante);
			grupo.setUsuarioregistro(integrante);

			Grupo result = serGrupo.crear(grupo);
			return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
		} catch (Exception e) {
			return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
	}

}
