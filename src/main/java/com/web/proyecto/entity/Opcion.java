package com.web.proyecto.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_opcion")
public class Opcion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_opcion")
	private int cod_opcion;
	@Column(name = "opcion_nombre")
	private String nombre;
	@Column(name = "opcion_ruta")
	private String ruta;
	@Column(name = "opcion_estado")
	private String estado;
	@Column(name = "opcion_tipo")
	private int tipo;
	
	/* COLOCAR LA CONEXION CON LA OTRA TABLA
	*/
	@OneToMany(mappedBy = "listaopcion")
	private List<RolHasOpcion> listaop;
	

	public int getCod_opcion() {
		return cod_opcion;
	}

	public void setCod_opcion(int cod_opcion) {
		this.cod_opcion = cod_opcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
