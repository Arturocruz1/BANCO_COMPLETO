package com.web.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Cuota;
import com.web.proyecto.repository.CuotaRepository;

@Service
public class CuotasService {
	
	@Autowired
	private CuotaRepository repo;
	
	public List<Cuota> listarTodo() {
		return repo.findAll();
	}
	
	/*
	public List<Cuota[]> listarPorPrestamista() {
		return repo.findSolicitudByCodUsuarioRegistro();
	}
	*/
	
	/*Listar Todos*/
	public List<Object[]> listaTodosCuotaPrestamistas(){
		return repo.ListaCoutaPorPrestamistasTodos();
	}
	
	/*Listar por Prestamista*/
	public List<Object[]> listaPorCuotaPrestamista(Integer vcodigo){
		return repo.ListaCoutaPorPrestamista(vcodigo);
	}

}
