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
@Table(name="tb_forma_pago")
public class FormaPago {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_forma_pago")
	private int id_forma_pago;
	@Column(name = "cod_descripcion")
	private String descripcion;
	@Column(name = "cod_estado")
	private int estado;
	
	
	/*FORMA PAGO X PAGO*/
	@JsonIgnore
	@OneToMany(mappedBy = "grupoFormaPago")
	private List<Pago> listaformaPago;  
	
	
	public int getIdFormaPago() {
		return id_forma_pago;
	}


	public void setIdFormaPago(int idFormaPago) {
		this.id_forma_pago = idFormaPago;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}

	
	
	/*
	public List<Pago> getListaformaPago() {
		return listaformaPago;
	}


	public void setListaformaPago(List<Pago> listaformaPago) {
		this.listaformaPago = listaformaPago;
	}
	*/
}
