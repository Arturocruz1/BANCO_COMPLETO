package com.web.proyecto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tb_banco_has_usuario")
public class BancoHasUsuario {
	@EmbeddedId BancoHasUsuarioPK IduBanco;
	
	@ManyToOne
	@JoinColumn(name="cod_banco",updatable = false , insertable = false , referencedColumnName = "cod_banco")
	private Banco BancoHasUsuario;
	
	@ManyToOne
	@JoinColumn(name="cod_usuario",updatable = false , insertable = false , referencedColumnName = "cod_usuario")
	private Usuario UsuarioHasBanco;
	
	@Column(name = "has_cuenta")
	private String cuenta;
	
	@Column(name = "has_estado")
	private int estado;

}
