package com.web.proyecto.service.lpml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.RolHasOpcion;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.entity.UsuarioHasRol;
import com.web.proyecto.entity.UsuarioHasRolPk;
import com.web.proyecto.repository.UsuarioHasRolRepository;

@Service
public class UsuarioHasRolccccService extends ICRUDlpml<UsuarioHasRol, Integer>{

	@Autowired
	private UsuarioHasRolRepository repo;
	
	@Override
	public JpaRepository<UsuarioHasRol, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	public UsuarioHasRol buscarUsuarioHasRolPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }
	

}
