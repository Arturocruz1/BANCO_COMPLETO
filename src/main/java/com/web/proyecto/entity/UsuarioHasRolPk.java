package com.web.proyecto.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class UsuarioHasRolPk implements Serializable{
	
	@Column(name="cod_usuario" )
	private Integer codigousuario;
	
	
	@Column(name="cod_rol" )
	private Integer codigoroles;


}
