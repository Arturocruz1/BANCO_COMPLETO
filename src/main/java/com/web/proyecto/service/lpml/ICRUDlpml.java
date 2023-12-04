package com.web.proyecto.service.lpml;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.proyecto.services.ICRUD;

public abstract class ICRUDlpml<T,Id> implements ICRUD<T,Id>{
	
	public abstract JpaRepository<T, Id> getRepository();
	
	
	
	@Override
	public T registrar(T bin) {
		
		return getRepository().save(bin);
	}

	@Override
	public T actualizar(T bin) {
		
		return getRepository().save(bin);
	}

	@Override
	public void EliminarPorID(Id cod) {
		getRepository().deleteById(cod);
		
	}

	@Override
	public T buscarPorId(Id cod) {
		//
		return getRepository().findById(cod).orElse(null);
	}

	@Override
	public List<T> listarTodos() {

		return getRepository().findAll();
	}

}
