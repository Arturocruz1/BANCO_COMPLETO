package com.web.proyecto.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class BancoHasUsuarioPK implements Serializable{
	
	@Column(name="cod_usuario" )
	private Integer codigousuario;
	
	
	@Column(name="cod_banco" )
	private Integer codigobanco;

}
