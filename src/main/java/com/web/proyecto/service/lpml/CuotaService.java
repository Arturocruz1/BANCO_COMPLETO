package com.web.proyecto.service.lpml;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Cuota;
import com.web.proyecto.repository.CuotaRepository;

@Service
public class CuotaService extends ICRUDlpml<Cuota, Integer>{

	@Autowired
	private CuotaRepository repo;
	
	@Override
	public JpaRepository<Cuota, Integer> getRepository() {
		return repo;
	}
	
	
	public List<Cuota> listarEntreFechasPago(Date fechaInicio, Date fechaFin){
		return repo.findByFechaPagoBetween(fechaInicio, fechaFin);
	}

}
