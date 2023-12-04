package com.web.proyecto.service.lpml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Pago;
import com.web.proyecto.repository.PagoRepository;

@Service
public class PagoServiceImpl extends ICRUDlpml<Pago, Integer>{

	@Autowired
	private PagoRepository repo;
	
	@Override
	public JpaRepository<Pago, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}

}
