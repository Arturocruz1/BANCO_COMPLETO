package com.web.proyecto.service.lpml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Rol;
import com.web.proyecto.repository.RolRepository;

@Service
public class RolService extends ICRUDlpml<Rol, Integer>{

	@Autowired
	private RolRepository ser;
	
	@Override
	public JpaRepository<Rol, Integer> getRepository() {
		// TODO Auto-generated method stub
		return ser;
	}

}
