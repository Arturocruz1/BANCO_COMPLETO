package com.web.proyecto.service.lpml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.FormaPago;
import com.web.proyecto.repository.FormaPagoRepository;

@Service
public class FormaPagoService {
	
	@Autowired
	private FormaPagoRepository repo;
	
	public List<FormaPago> listarFormasPago() {
		return repo.findAll();
	}
	
	public void registrar(FormaPago f) {
		repo.save(f);
	}
	
	public void actualizar(FormaPago f) {
		repo.save(f);
	}
	
	public FormaPago buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
}
