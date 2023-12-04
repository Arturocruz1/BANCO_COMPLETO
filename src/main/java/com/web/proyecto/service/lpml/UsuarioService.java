package com.web.proyecto.service.lpml;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Opcion;
import com.web.proyecto.entity.Rol;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.repository.UsuarioRepository;

@Service
public class UsuarioService extends ICRUDlpml<Usuario, Integer>{

	@Autowired
	private UsuarioRepository repoUsu;
	
	@Autowired
	private RolService serRol;
	
	@Override
	public JpaRepository<Usuario, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repoUsu;
	}
	
	public List<Usuario> listaTodosJefry() {
		return repoUsu.findAll();
	}
	
	public Usuario usuarioPorUsername(String username) {
	   return	repoUsu.findByUsername(username);		
	}
	
	
    public List<Opcion> listaDeOpcionesPorCodigoDeRol(int cod){
    	return repoUsu.listaDeOpcionPorCodigoROL(cod);
    }
	
    public List<Usuario> listadeUsuarioporcodRol(int cod){
    	return repoUsu.listaUsuariocodrol(cod);
    }
    
    public Rol rolPorCodigoDeUsuario(int cod) {
    	
    	if(repoUsu.rolPOrCodioDeUsuario(cod)==null) {
    		
    		return serRol.buscarPorId(5);
    		
    	}else {
			
    		return repoUsu.rolPOrCodioDeUsuario(cod);
		}
    	
    	
    }
    //
    public Usuario buscarUsuarioPorCodigo(Integer cod) {
    	return repoUsu.findById(cod).orElse(null);
    }
    
    /*Obtener el usuario logeado*/
    public Optional<Usuario> findByUsername(String username) {
        return Optional.ofNullable(repoUsu.findByUsername(username));
    }
    
    /*Listar los Prestamista acargo de un 	JefePrestamista*/
    public List<Object[]> listaPrestamistasxJefePrestamista(int codigoLider) {
        return repoUsu.listaUsuarioPrestamistaPorJefe(codigoLider);
    }
    
    public List<Usuario> listaUsuarioPrestatarios(){
    	return repoUsu.listaUsuarioPrestatarios();
    }
    
    /**/
    public List<Object[]> obtenerRentabilidad(Integer vcodLider){
    	return repoUsu.obtenerRentabilidad(vcodLider);
    }
}
