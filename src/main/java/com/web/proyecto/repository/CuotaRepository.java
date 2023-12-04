package com.web.proyecto.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.proyecto.entity.Cuota;

public interface CuotaRepository extends JpaRepository<Cuota, Integer>{
	

	@Query(value = "SELECT s.cod_solicitud, s.numero_solicitud, u.username, c.cuota_monto, c.cuota_estado, " +
	        "(SELECT COUNT(*) FROM tb_cuota) AS cuotas, " +
	        "(SELECT COUNT(*) FROM tb_cuota WHERE cuota_estado = 1) AS cuotas_pendientes, " +
	        "(SELECT COUNT(*) FROM tb_cuota WHERE cuota_estado = 2) AS cuotas_pagadas, " +
	        "(SELECT COUNT(*) FROM tb_cuota WHERE cuota_estado = 3) AS cuota_parcial, " +
	        "p.cod_pago,p.estado,p.monto " +
	        "FROM tb_cuota c " +
	        "LEFT JOIN tb_pago p ON c.cod_cuota = p.cod_cuota " +
	        "INNER JOIN tb_solicitud s ON c.cod_solicitud = s.cod_solicitud " +
	        "INNER JOIN tb_usuario u ON s.cod_usuario_prestatario = u.cod_usuario " +
	        "where c.cod_usuario_registro=22", nativeQuery = true)
	List<Cuota[]> findSolicitudByCodUsuarioRegistro();
	
	
	/*Lista de todos*/
	@Query(value = "SELECT s.numero_solicitud, u.username, ROUND(SUM(DISTINCT(s.solicitud_monto + s.interes_solicitud)), 2) AS monto_total, " +
            " COUNT(*) AS cuotas_totales,  " +
            " SUM(CASE WHEN c.cuota_estado = 1 THEN 1 ELSE 0 END) AS cuotas_pagadas,  " +
            " SUM(CASE WHEN c.cuota_estado = 2 THEN 1 ELSE 0 END) AS cuotas_pendientes,  " +
            "COALESCE(ROUND(SUM(DISTINCT p.monto), 2),0) AS monto_pagado, " +
            "ROUND(SUM(CASE WHEN c.cuota_estado = 2 THEN c.cuota_monto ELSE 0 END), 2) AS monto_pendiente " +
            "FROM tb_cuota c " +
            "LEFT JOIN tb_pago p ON c.cod_cuota = p.cod_cuota " +
            "INNER JOIN tb_solicitud s ON c.cod_solicitud = s.cod_solicitud " +
            "INNER JOIN tb_usuario u ON s.cod_usuario_prestatario = u.cod_usuario " +
            "GROUP BY s.numero_solicitud, u.username", nativeQuery = true)
    List<Object[]> ListaCoutaPorPrestamistasTodos();


	/*Lista por prestamista	*/
    @Query(value = "SELECT " +
            "    s.numero_solicitud, " +
            "    u.username, " +
            "    ROUND(SUM(DISTINCT(s.solicitud_monto + s.interes_solicitud)), 2) AS monto_total,  " +
            "    COUNT(*) AS cuotas_totales, " +
            "    SUM(CASE WHEN c.cuota_estado = 1 THEN 1 ELSE 0 END) AS cuotas_pagadas,  " +
            "    SUM(CASE WHEN c.cuota_estado = 2 THEN 1 ELSE 0 END) AS cuotas_pendientes,  " +
            "    COALESCE(ROUND(SUM(DISTINCT p.monto), 2),0) AS monto_pagado, " +
            "    ROUND(SUM(CASE WHEN c.cuota_estado = 2 THEN c.cuota_monto ELSE 0 END), 2) AS monto_pendiente " +
            "FROM " +
            "    tb_cuota c " +
            "LEFT JOIN tb_pago p ON c.cod_cuota = p.cod_cuota " +
            "INNER JOIN tb_solicitud s ON c.cod_solicitud = s.cod_solicitud " +
            "INNER JOIN tb_usuario u ON s.cod_usuario_prestatario = u.cod_usuario " +
            "WHERE c.cod_usuario_registro = ?1 " +
            "GROUP BY " +
            "    s.numero_solicitud, u.username", nativeQuery = true)
    List<Object[]> ListaCoutaPorPrestamista(Integer codigoUsuario);
    
    List<Cuota> findByFechaPagoBetween(Date fechaInicio, Date fechaFin);
	
	
}
