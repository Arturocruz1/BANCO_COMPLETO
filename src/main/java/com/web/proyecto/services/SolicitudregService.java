package com.web.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Solictud;
import com.web.proyecto.repository.SolicitudRepository;

@Service
public class SolicitudregService {
	
	@Autowired
	private SolicitudRepository repo;
	
	public void registrar(Solictud bean) {
		repo.save(bean);	
	}
	
}
