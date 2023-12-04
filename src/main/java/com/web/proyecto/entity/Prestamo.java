package com.web.proyecto.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_prestamo")
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_prestamo")
	private int codigo;
	
	@Column(name="prestamo_monto")
	private double monto;
	
	@Column(name="fecha_registro")
	private LocalDate fecResgistro;
	
	@Column(name="fecha_modificacion")
	private LocalDate fecModificacion;

	@ManyToOne
	@JoinColumn(name="cod_tipo_prestamo")
	private TipoPrestamo tbTipoPrestamo;
	
	/*@ManyToOne
	@JoinColumn(name="cod_solicitud")
	private Solictud tbSolicitud;*/
	
	/*Falta 3 ManytoOne de los demas usuarios*/
	
	@ManyToOne
	@JoinColumn(name="cod_usuario_prestamista")
	private Usuario tbListUsuarioPrestamista;
	
	@ManyToOne
	@JoinColumn(name="cod_usuario_prestatario")
	private Usuario tbListUsuarioPrestatario;
	
	@ManyToOne
	@JoinColumn(name="cod_usuario_registro")
	private Usuario tbListUsuarioRegistra;
	
	
	@ManyToOne
	@JoinColumn(name="cod_usuario_actualiza")
	private Usuario tbListUsuarioActualiza;
	
	@ManyToOne
	@JoinColumn(name="cod_solicitud")
	private Solictud listaPrestamos;
	
	

}
