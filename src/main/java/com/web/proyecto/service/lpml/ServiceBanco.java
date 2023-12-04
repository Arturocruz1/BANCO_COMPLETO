package com.web.proyecto.service.lpml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Banco;
import com.web.proyecto.repository.BancoRepository;

@Service
public class ServiceBanco extends ICRUDlpml<Banco, Integer>{
	@Autowired
	private BancoRepository repobanc;
	
	@Override
	public JpaRepository<Banco, Integer> getRepository() {
		
		return repobanc;
	}
	
}
