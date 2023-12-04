package com.web.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.proyecto.entity.Grupo;
import com.web.proyecto.entity.Usuario;

public interface GrupoJefeRepository extends JpaRepository<Grupo, Integer>{	
	
	@Query("select x from Grupo x where x.usuarioLider.codigoUsuario=?1")
	public List<Grupo> listaGrupoPorUsuario(int cod);
	
	@Query("select u from "
			+ "Usuario u "
			+ "inner join Grupo g on u.codigoUsuario=g.usuarioregistro.codigoUsuario "
			+ "where g.usuarioLider.codigoUsuario=?1")
	public List<Usuario> listarIntegrantesPorJefe(@Param("codigoJefe") int codigoJefe);
	
	@Query("select u from "
			+ "Usuario u "
			+ "left join Grupo g on u.codigoUsuario = g.usuarioregistro.codigoUsuario "
			+ "left join UsuarioHasRol ur on u.codigoUsuario = ur.Usuariohasrol.codigoUsuario "
			+ "where g.usuarioregistro.codigoUsuario is null and ur.rolhasUsuario.codigo=2 ")
	public List<Usuario> listarPrestamistasLibres();
	
}
