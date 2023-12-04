package com.web.proyecto.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tb_usuario_has_rol")
public class UsuarioHasRol {
	
	@EmbeddedId UsuarioHasRolPk Idusu;
	
@ManyToOne
@JoinColumn(name="cod_usuario",updatable = false , insertable = false , referencedColumnName = "cod_usuario")
private Usuario Usuariohasrol;

@ManyToOne
@JoinColumn(name="cod_rol",updatable = false , insertable = false , referencedColumnName = "cod_rol")
private Rol rolhasUsuario;


}
