package com.web.proyecto.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_tipo_prestamo")
public class TipoPrestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_tipo_prestamo")
	private int CodigoTipoPrestamo;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name ="tasa")
	private double tasa;
	@Column(name = "estado")
	private int estado;
	
	//
	@OneToMany(mappedBy = "tbTipoPrestamo")
	@JsonIgnore
	private List<Prestamo> tbPrestamo;
	//
	
	
	
	
	public int getCodigoTipoPrestamo() {
		return CodigoTipoPrestamo;
	}
	public void setCodigoTipoPrestamo(int codigoTipoPrestamo) {
		CodigoTipoPrestamo = codigoTipoPrestamo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getTasa() {
		return tasa;
	}
	public void setTasa(double tasa) {
		this.tasa = tasa;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public List<Prestamo> getTbPrestamo() {
		return tbPrestamo;
	}
	public void setTbPrestamo(List<Prestamo> tbPrestamo) {
		this.tbPrestamo = tbPrestamo;
	}
	
	
}
