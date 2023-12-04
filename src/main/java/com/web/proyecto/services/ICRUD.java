package com.web.proyecto.services;

import java.util.List;

public interface ICRUD <T,Id> {

	T registrar(T bin);
	
	T actualizar(T bin);
	
	void EliminarPorID (Id cod); 

	T buscarPorId (Id cod);
	
	List<T> listarTodos();
}
