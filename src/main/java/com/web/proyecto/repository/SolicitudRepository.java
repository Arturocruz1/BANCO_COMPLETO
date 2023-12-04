package com.web.proyecto.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.proyecto.entity.Cuota;
import com.web.proyecto.entity.Solictud;
import com.web.proyecto.entity.Usuario;

public interface SolicitudRepository extends JpaRepository<Solictud, Integer>{

	@Query(value="{call sp_generar_numero}", nativeQuery = true)
	public String GenerarNumeroSolicitud();
	
	@Query("select x from Solictud x where x.fecharegistro=:fecha and x.Usuarioprestatariosoli.codigoUsuario= :cod_usuario")
	public List<Solictud> BuscarSolicitudPorFechaRegistroUsuario(@Param("fecha") LocalDate fechaRegistro,@Param("cod_usuario") Integer cod_usuario_prestatario);
	
	
	@Query(value="SELECT * FROM tb_solicitud WHERE fecha_inicio_prestamo >= ?1 AND fecha_fin_prestamo <= ?2", nativeQuery = true)
	public List<Solictud> listaPorFecha(String fecha1,String fecha2);
	
	@Query(value="SELECT * FROM tb_cuota WHERE cuota_fecha_pago >= ?1 AND cuota_fecha_pago <= ?2 AND cod_usuario_registro=?3",nativeQuery = true)
	public List<Cuota> listaDeCuotaPorFecha(String fecha1,String fecha2,int prestatario);
	
	
	@Query("select x from UsuarioHasRol a join a.Usuariohasrol x where a.rolhasUsuario.codigo=?1")
	public List<Usuario> listaDeUsuarioPorRol(int cod);
	
	/*
	@Query("SELECT s.numero, s.solimonto, s.interes FROM Solictud s WHERE (s.fecharegistro BETWEEN 0? AND 1?) ")
	public abstract List<Solictud> filtroParametros(Date vdesde , Date vhasta );
	//@Query("SELECT s FROM Solicitud s WHERE s.fechaInicio >= ?1 AND s.fechaFin <= ?2")
	*/
	
	
	@Query("SELECT s FROM Solictud s WHERE s.Usuarioprestatariosoli.codigoUsuario = ?1 AND (s.fechainiciopresta >= ?2 AND s.fecha_fin_prestamo <= ?3)")
	public List<Solictud> solicitudXFiltros(int vcod, Date vfechaI, Date vfechaF);

	 
	 
	 /*Lista de las coutas para el prestatarios con un parametro*/
	 @Query("SELECT p.codigo, c.numeroCuota, c.monto,c.fechaPago, c.estadoCuota, c.deuda " +
	           "FROM Solictud s " +
	           "JOIN Cuota c ON c.listaSolicitud.codigosoli = s.codigosoli " +
	           "JOIN Prestamo p ON p.listaPrestamos.codigosoli = s.codigosoli " +
	           "WHERE s.Usuarioprestatariosoli.codigoUsuario = ?1")
	 List<Object[]> obtenerDetallesPrestamoPorUsuario(Integer codigoUsuario);
	 
	 /*Lista de las coutas para el prestatarios con 3 parametros */
	 @Query("SELECT p.codigo, c.numeroCuota, c.monto,c.fechaPago, c.estadoCuota, c.deuda " +
		           "FROM Solictud s " +
		           "JOIN Cuota c ON c.listaSolicitud.codigosoli = s.codigosoli " +
		           "JOIN Prestamo p ON p.listaPrestamos.codigosoli = s.codigosoli " +
		           "WHERE s.Usuarioprestatariosoli.codigoUsuario = ?1 AND c.fechaPago BETWEEN ?2 AND ?3")
	 List<Object[]> obtenerDetallesPrestamoPorUsuarioFechaVencimiento(Integer codigoUsuario, Date fechaVI,Date fechaVF);
	 
	 
	 /*Lista de todas los coutas con el estado = 2*/
	 @Query("SELECT c.codigo, p.codigo, s.Usuarioprestatariosoli.username, c.numeroCuota, c.monto, c.fechaPago " +
		       "FROM Solictud s " +
		       "JOIN Cuota c ON c.listaSolicitud.codigosoli = s.codigosoli " +
		       "JOIN Prestamo p ON p.listaPrestamos.codigosoli = s.codigosoli " +
		       "WHERE (c.estadoCuota = 2 or c.estadoCuota = 3)" +
		       "ORDER BY p.codigo")
	List<Object[]> listaCuotaEstado2();
	
	/*Lista de todas los coutas con el estado = 2 y con los 3 parametros de fecha*/
	 @Query("SELECT c.codigo, p.codigo, s.Usuarioprestatariosoli.username, c.numeroCuota, c.monto, c.fechaPago " +
		       "FROM Solictud s " +
		       "JOIN Cuota c ON c.listaSolicitud.codigosoli = s.codigosoli " +
		       "JOIN Prestamo p ON p.listaPrestamos.codigosoli = s.codigosoli " +
		       "WHERE (c.estadoCuota = 2 or c.estadoCuota = 3) AND s.Usuarioprestatariosoli.codigoUsuario = ?1 AND c.fechaPago BETWEEN ?2 AND ?3 " +
		       "ORDER BY p.codigo")
	List<Object[]> listaCuotaEstado2Filtro(Integer codigoUsuario, Date fechaVI, Date fechaVF);

	 
}
