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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_rol")
public class Rol {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="cod_rol")
private Integer codigo; 

@Column(name="rol_nombre")
private String nombrerol;

@Column(name="rol_estado")
private int estadorol;

@JsonIgnore
@OneToMany(mappedBy = "rolhasUsuario")
private List<UsuarioHasRol> listado;

@JsonIgnore
@OneToMany(mappedBy = "listarol")
private List<RolHasOpcion> listaro;


public Rol(int cod) {
	codigo=cod;
}

}
