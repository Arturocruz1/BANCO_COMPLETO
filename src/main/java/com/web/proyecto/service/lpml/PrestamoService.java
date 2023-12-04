package com.web.proyecto.service.lpml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Prestamo;
import com.web.proyecto.repository.PrestamoRepository;
@Service
public class PrestamoService extends ICRUDlpml<Prestamo, Integer>{

	@Autowired
	private PrestamoRepository repo;
	
	@Override
	public JpaRepository<Prestamo, Integer> getRepository() {
		return repo;
	}

}
