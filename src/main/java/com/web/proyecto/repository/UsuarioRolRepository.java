package com.web.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.proyecto.entity.Usuario;

public interface UsuarioRolRepository extends JpaRepository<Usuario, Integer>{

    
	
}
