package com.web.proyecto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.proyecto.entity.Opcion;
import com.web.proyecto.entity.Rol;
import com.web.proyecto.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	
    //Optional<Usuario> findByUsername(String username);
	
	Usuario findByUsername(String username);
	
	
	@Query("select x from RolHasOpcion a join a.listaopcion x where a.listarol.codigo=?1")
	public List<Opcion> listaDeOpcionPorCodigoROL(int cod);
	
	
	@Query("select x from UsuarioHasRol a join a.rolhasUsuario x where a.Usuariohasrol.codigoUsuario=?1")
	public Rol rolPOrCodioDeUsuario(int cod);
	
	@Query("select x from UsuarioHasRol a join a.Usuariohasrol x where a.rolhasUsuario.codigo=?1")
	public List<Usuario> listaUsuariocodrol(int cod);
	
	/*
	@Query("SELECT u, r.nombrerol FROM Usuario u INNER JOIN u.usuahasrol ur INNER JOIN ur.rolhasUsuario r")
    public List<Object[]> listarUsuariosConRol();
	 */	
	/**/
	/*Listado de los prestamistas a cargo de un jefe prestamista*/
	@Query("SELECT g.usuarioregistro.codigoUsuario, g.usuarioregistro.username " +
		       "FROM Usuario u " +
		       "INNER JOIN u.listaUsuarioLider g " +
		       "WHERE g.usuarioLider.codigoUsuario = ?1")
	public List<Object[]> listaUsuarioPrestamistaPorJefe(int vcod);
	
	@Query("SELECT u FROM Usuario u JOIN u.usuahasrol ur WHERE ur.id.codigoroles = 4")
	public List<Usuario> listaUsuarioPrestatarios();
	
	
	@Query("SELECT " +
	        "g.usuarioregistro.username, " +
	        "SUM(DISTINCT s.solimonto) AS montoPrestado, " +
	        "COALESCE(SUM(p.montopago), 0) AS montoPagado, " +
	        "COALESCE(SUM(CASE WHEN c.estadoCuota IN (2, 3) THEN c.monto + c.deuda ELSE 0 END), 0) AS pendiente, " +
	        "CONCAT(ROUND((COALESCE(SUM(p.montopago), 0) / SUM(DISTINCT s.solimonto)) * 100, 2), '%') AS rentabilidad " +
	        "FROM " +
	        "Grupo g " +
	        "JOIN Solictud s ON g.usuarioregistro.codigoUsuario = s.Usuarioregistrosoli.codigoUsuario " +
	        "LEFT JOIN Cuota c ON s.codigosoli = c.listaSolicitud.codigosoli " +
	        "LEFT JOIN Pago p ON c.codigo = p.cuotaPago.codigo " +
	        "WHERE " +
	        "g.usuarioLider.codigoUsuario = ?1 AND s.estadosoli = 2 " +
	        "GROUP BY " +
	        "g.usuarioregistro.username")
	public List<Object[]> obtenerRentabilidad(Integer codigoUsuarioLider);

	
}
