package com.web.proyecto.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Embeddable
public class RolHasOpcionPk implements Serializable {

	
	@Column(name="cod_opcion" )
	private Integer codigoopcion;
	
	
	@Column(name="cod_rol" )
	private Integer codigoroll;
	
	
	
}
