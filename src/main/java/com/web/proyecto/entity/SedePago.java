package com.web.proyecto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sede_pago")
public class SedePago {
//  NO DE BE ESTAR
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_sede_pago")
	private int id_sede_pago;
	@Column(name = "cod_nombre_sede")
	private String nombre_sede;
	@Column(name = "cod_estado")
	private int estado;
	@Column(name = "cod_tipo_moneda")
	private String tipo_moneda;
	
	/*
	@JsonIgnore
	@OneToMany(mappedBy = "grupoSedePago")
	private List<Pago> listaSedePago;
	*/
	
	public int getId_sede_pago() {
		return id_sede_pago;
	}
	public void setId_sede_pago(int id_sede_pago) {
		this.id_sede_pago = id_sede_pago;
	}
	public String getNombre_sede() {
		return nombre_sede;
	}
	public void setNombre_sede(String nombre_sede) {
		this.nombre_sede = nombre_sede;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getTipo_moneda() {
		return tipo_moneda;
	}
	public void setTipo_moneda(String tipo_moneda) {
		this.tipo_moneda = tipo_moneda;
	}

	
	
	
}
