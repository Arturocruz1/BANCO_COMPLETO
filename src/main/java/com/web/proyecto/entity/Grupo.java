package com.web.proyecto.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_grupo")
public class Grupo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_grupo")
	private Integer codigoGrupo;
	@Column(name = "grupo_descripcion",nullable=false,length=45)
	private String descripcionGrupo;
	@Column(name = "grupo_estado",nullable=false)
	private int estadoGrupo;
	@Column(name = "fecha_registro",nullable=false)
	private LocalDate fechaGrupo;
	
	
	@ManyToOne
	@JoinColumn(name = "cod_ubigeo")
	private Ubigeo tbUbigeo;	
	
	@ManyToOne
	@JoinColumn(name = "cod_usuario_lider")
	private Usuario usuarioLider;
	
	@ManyToOne
	@JoinColumn(name = "cod_usuario_registro")
	private Usuario usuarioregistro;
	
	
	
}
