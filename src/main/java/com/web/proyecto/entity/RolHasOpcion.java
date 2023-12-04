package com.web.proyecto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tb_rol_has_opcion")
public class RolHasOpcion {

@EmbeddedId
private RolHasOpcionPk Id;
	
@ManyToOne
@JoinColumn(name = "cod_rol", updatable = false , insertable = false , referencedColumnName = "cod_rol")
private Rol listarol;


@ManyToOne
@JoinColumn(name="cod_opcion", updatable = false, insertable = false , referencedColumnName = "cod_opcion")
private Opcion listaopcion;


}

