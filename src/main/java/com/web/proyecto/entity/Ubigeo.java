package com.web.proyecto.entity;


import java.time.LocalDate;
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
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tb_ubigeo")
public class Ubigeo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_ubigeo")
	private int codigo;
	
	@Column(name="departamento")
	private String departamento;
	
	@Column(name="provincia")
	private String provincia;
	
	@Column(name="distrito")
	private String distrito;
	
	@OneToMany(mappedBy = "tbUbigeo")
	@JsonIgnore
	private List<Grupo> ListGrupo;
	
}
