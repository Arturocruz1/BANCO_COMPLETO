package com.web.proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.FormaPago;
import com.web.proyecto.entity.Grupo;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.repository.GrupoJefeRepository;

@Service
public class GrupoJefeService {
	
	@Autowired
	private GrupoJefeRepository repo;

	public List<Grupo> listarGruposPorUsu(int cod) {
		return repo.listaGrupoPorUsuario(cod);
	}
	
	public void actualizar(Grupo g) {
		repo.save(g);
	}
	
	public Grupo buscarPorID(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	

	public Grupo crear(Grupo grupo) {
		return repo.save(grupo);
	}
	
	public List<Usuario> getIntegrantesPorJefe(int codigoJefe){
		return repo.listarIntegrantesPorJefe(codigoJefe);
	}
	
	public List<Usuario> getPrestamistasLibres(){
		return repo.listarPrestamistasLibres();
	}
	
	
	
}
