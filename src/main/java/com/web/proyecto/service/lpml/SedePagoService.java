package com.web.proyecto.service.lpml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.SedePago;
import com.web.proyecto.repository.SedePagoRepository;

@Service
public class SedePagoService {

	@Autowired
	private SedePagoRepository repo;

	public List<SedePago> listarSedePagos (){
		return repo.findAll();
	}

	public void registrar (SedePago s) {
		repo.save(s);
	}

	public void actualizar(SedePago s) {
		repo.save(s);
	}

	public SedePago buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
}
