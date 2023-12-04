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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_banco")
public class Banco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_banco")
	private Integer codigoBanco;
	@Column(name = "nombre_banco",nullable=false,length=45)
	private String nombreBanco;
	@Column(name = "estado_banco",nullable=false)
	private int estadoBanco;
	
	/*BANCO HAS USUARIO*/
	@JsonIgnore
	@OneToMany(mappedBy = "BancoHasUsuario")
	private List<BancoHasUsuario> bancohasusu;
	
	
	
}
