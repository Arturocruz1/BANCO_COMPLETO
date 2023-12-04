package com.web.proyecto.service.lpml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Opcion;
import com.web.proyecto.repository.OpcionRepository;

@Service
public class OpcionService extends ICRUDlpml<Opcion, Integer>{

	@Autowired
	private OpcionRepository repoOP;
	
	
	@Override
	public JpaRepository<Opcion, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repoOP;
	}

	
	
	
	
}
