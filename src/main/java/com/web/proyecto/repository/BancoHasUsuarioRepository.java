package com.web.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.proyecto.entity.BancoHasUsuario;

public interface BancoHasUsuarioRepository extends JpaRepository<BancoHasUsuario, Integer>{
	
	@Query("select x from BancoHasUsuario x where x.UsuarioHasBanco.codigoUsuario=?1")
	public List<BancoHasUsuario> listaCuentasPorCod(int cod);

}
