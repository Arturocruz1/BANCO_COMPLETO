package com.web.proyecto.service.lpml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.BancoHasUsuario;
import com.web.proyecto.repository.BancoHasUsuarioRepository;

@Service
public class BancoHasUsuarioService {
	
	@Autowired
	private BancoHasUsuarioRepository repo;
	
	public List<BancoHasUsuario> listarCuentas() {
		return repo.findAll();
	}

	public List<BancoHasUsuario> listarCuentasPorCodigo(int cod) {
		return repo.listaCuentasPorCod(cod);
	}
	
}
