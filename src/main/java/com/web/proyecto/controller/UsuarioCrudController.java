package com.web.proyecto.controller;

import java.io.Console;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.proyecto.entity.Rol;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.entity.UsuarioHasRol;
import com.web.proyecto.entity.UsuarioHasRolPk;
import com.web.proyecto.service.lpml.RolService;
import com.web.proyecto.service.lpml.UsuarioHasRolccccService;
import com.web.proyecto.service.lpml.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioCrudController {
	@Autowired
	private UsuarioService serusus;
	
	@Autowired
	private UsuarioHasRolccccService serRolUsu;
	
	@Autowired
	private RolService serRol;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("/lista")
	public String lista(Model model) {
		model.addAttribute("usuarios", serusus.listaTodosJefry());
		model.addAttribute("rol", serRol.listarTodos());
		
		return "CrudUsuario";
	}
	
	
	 public int nombreusuarionorepite(String nombre) {
		 
		 
		 
		 int lista=serusus.listaTodosJefry().size();
		 
		 for (int i=0;i<lista;i++) {
			
			 if(serusus.listaTodosJefry().get(i).getUsername().equals(nombre)) {
				 return -1;
			 }
				 		
		}
		return 1;
		 
		 
		 
		 
	 }
	
	
	
	
	
	
	
	//RUTA DE GRABAR DE USUARIO
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
				@RequestParam("Rol") Integer codRol,
				
				RedirectAttributes redirect,
				Model model
				) {
				try {
					
	
					
					Usuario usu = new Usuario();
					if(nombreusuarionorepite(user)==-1) {
						model.addAttribute("mensaje","ESCOJE OTRO USUARIO");
						return "redirect:/usuario/lista";
					
				    }else 			
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
					
					Rol r=new Rol(codRol);
					
		
					
				    

					if(cod == 0) {
						
						serusus.registrar(usu);
						 
						//codigo para colocar el rol del usuario
						UsuarioHasRolPk pk=new UsuarioHasRolPk();
						pk.setCodigoroles(codRol);
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
						
						//
						/*No funciona*/
						// Actualizar el rol del usuario
			            UsuarioHasRolPk pk = new UsuarioHasRolPk();
			            pk.setCodigoroles(codRol);
			            pk.setCodigousuario(cod);
			            
			            UsuarioHasRol rolDeUsu=new UsuarioHasRol();
						rolDeUsu.setIdusu(pk);
						rolDeUsu.setRolhasUsuario(r);
						rolDeUsu.setUsuariohasrol(usu);  
						
						serusus.actualizar(usu);
			            // Fin de la actualización del rol
						/*No funciona*/
						//
						 model.addAttribute("mensaje"," ACTIUALIZADO CON EXITO");
					}
						
					} catch (Exception e) {
						 model.addAttribute("mensaje","*** Error en el registro");
						e.printStackTrace();
				}
				return "redirect:/usuario/lista";
	
		}
		
		@RequestMapping("/buscar")
		@ResponseBody
		public Usuario buscaUsuarioPorCodigo(@RequestParam("codigo") Integer cod) {
			return serusus.buscarUsuarioPorCodigo(cod);
		}
		
		
}
