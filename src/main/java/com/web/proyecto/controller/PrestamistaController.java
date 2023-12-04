package com.web.proyecto.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.Banco;
import com.web.proyecto.entity.Rol;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.entity.UsuarioHasRol;
import com.web.proyecto.entity.UsuarioHasRolPk;
import com.web.proyecto.service.lpml.UsuarioHasRolccccService;
import com.web.proyecto.service.lpml.UsuarioService;

@Controller
@RequestMapping("/prestamista")
public class PrestamistaController {

@Autowired
private UsuarioService usuarioservice;

@Autowired
private UsuarioHasRolccccService serRolUsu;

@Autowired
private PasswordEncoder encoder;


@GetMapping("/lista")
public String Index(Model model){
 List<Usuario> prestamista = usuarioservice.listadeUsuarioporcodRol(2);
	model.addAttribute("lista", prestamista);
	return "listadeprestamista";
};
//RUTA DE GRABAR DE PRESTAMISTA
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("codigo")Integer cod,
			@RequestParam("username") String user, 
			@RequestParam("password") String pass, 
			@RequestParam("fechanacimiento")Date fechana,
			@RequestParam("estado") Integer esta,
			@RequestParam("email")String email,
			@RequestParam("flag") Integer flag,
			@RequestParam("materno") String mater,
			@RequestParam("dni") String dni,
			@RequestParam("paterno") String pater,
			@RequestParam("telefono") String tele,
			RedirectAttributes redirect,
			Model model
			) {
			try {
				Usuario usu = new Usuario();
				usu.setUsername(user);
				usu.setPassword(encoder.encode(pass));
				usu.setFechaNacimiento(fechana);
				usu.setFechaUsuario( LocalDate.now());
				usu.setEstadoUsuario(esta);
				usu.setEmailUsuario(email);
				usu.setFlagUsario(flag);
				usu.setMarternoUsario(mater);
				usu.setDniUsario(dni);
				usu.setPaternoUsuario(pater);
				usu.setTelefonoUsuario(tele);
				
				Rol r=new Rol(1);
			    

				if(cod == 0) {
					
					usuarioservice.registrar(usu);
					
					//codigo para colocar el rol del usuario
					UsuarioHasRolPk pk=new UsuarioHasRolPk();
					pk.setCodigoroles(2);
					pk.setCodigousuario(usu.getCodigoUsuario());
					
					UsuarioHasRol rolDeUsu=new UsuarioHasRol();
					rolDeUsu.setIdusu(pk);
					rolDeUsu.setRolhasUsuario(r);
					rolDeUsu.setUsuariohasrol(usu);
					// FIN codigo para colocar el rol del usuario
					
					serRolUsu.registrar(rolDeUsu);
					 model.addAttribute("mensaje"," REGISTRADO CON EXITO");
					
				}else {
					usu.setCodigoUsuario(cod);
					usuarioservice.actualizar(usu);
					 model.addAttribute("mensaje"," ACTIUALIZADO CON EXITO");
				}
					
				} catch (Exception e) {
					 model.addAttribute("mensaje","*** Error en el registro");
					e.printStackTrace();
			}
			//String nom=request.getParamenter("nombre");
			return "redirect:/prestamista/lista";
	}
	@ResponseBody
	@GetMapping("/buscar/{cod}")
	public Usuario UsuarioPorId(@PathVariable("cod")Integer cod) {
		return usuarioservice.buscarPorId(cod);
	}
	
	@RequestMapping("/eliminar")
	public String eliminarPorId(@RequestParam("codigoEliminar")Integer cod,
			RedirectAttributes redirect) {
		usuarioservice.EliminarPorID(cod);
		redirect.addFlashAttribute("MENSAJE","USUARIO ELIMINADO");
        return "redirect:/prestamista/lista";
	}

}

