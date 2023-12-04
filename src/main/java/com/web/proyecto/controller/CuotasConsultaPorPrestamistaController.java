package com.web.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.proyecto.entity.Usuario;
import com.web.proyecto.service.lpml.UsuarioService;
import com.web.proyecto.services.CuotasService;

@Controller
@RequestMapping("/ConsultaCuotaPrestamista")
public class CuotasConsultaPorPrestamistaController {
	
	@Autowired
	private CuotasService serCuota;
	
	@Autowired
	private UsuarioService serUsu;
	
	@RequestMapping("/lista")
	public String listarCuota(Model model) {
		/*model.addAttribute("listado", serCuota.listarTodo());*/
		
		/*Julio Ynca */
		List<Object[]> listaAsignadaJulio = serCuota.listaTodosCuotaPrestamistas();
		model.addAttribute("listadoJulio", listaAsignadaJulio);
		
		/**/
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            String username = user.getUsername();
            
            // Ahora utilizamos el servicio para obtener la entidad Usuario desde la base de datos
            Optional<Usuario> usuarioOptional = serUsu.findByUsername(username);

            // Verifica si la entidad Usuario se encontró y realiza operaciones necesarias
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                Integer codigoUsuario = usuario.getCodigoUsuario();
                model.addAttribute("codigoUsuario", codigoUsuario);
                
                
             // Lista Usuario bajo un Jefe Prestamista:
        		List<Object[]> listaPrestamistas = serUsu.listaPrestamistasxJefePrestamista(codigoUsuario);
        		List<Map<String, Object>> prestamistasInfo = listaPrestamistas.stream()
                        .map(obj -> {
                            Map<String, Object> map = new HashMap<>();
                            map.put("codigoUsuario", obj[0]);
                            map.put("username", obj[1]);
                            return map;
                        })
                        .collect(Collectors.toList());

                model.addAttribute("listadoPxJP", prestamistasInfo);
        		
        		
            
            }
        }
		

		/**/
		// listar usuarios
		//model.addAttribute("listadoUsu", serUsu.listadeUsuarioporcodRol(2));	
		

		
		return "ConsultaCuotaPrestamista";
	}
	
	/*Codigo de la consulta por prestamista Julio*/
	
	@RequestMapping("/listaPorPrestamista")
	@ResponseBody
	public List<Object[]> listaPorCuotaPrestamista(@RequestParam("codigoPrestamista") int codigo){
		return serCuota.listaPorCuotaPrestamista(codigo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
