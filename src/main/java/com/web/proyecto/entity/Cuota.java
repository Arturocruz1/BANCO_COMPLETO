package com.web.proyecto.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="tb_cuota")
public class Cuota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_cuota")
	private Integer codigo;
	
	@Column(name="cuota_numero")
	private int numeroCuota;
	
	@Column(name="cuota_monto")
	private double monto;
	
	@Column(name="cuota_fecha_pago")
	private Date fechaPago;

	@Column(name="cuota_fecha_registro")
	private LocalDate fechaRegistro;

	@Column(name="cuota_estado")
	private int estadoCuota;
	
	@Column(name="cuota_deuda")
	private double deuda;
	
	
	/*ONETOMANY*/
	@JsonIgnore
	@OneToMany(mappedBy = "cuotaPago")
	private List<Pago> listapagocuot;
	
	/*ManyToOne*/
	@ManyToOne
	@JoinColumn(name="cod_usuario_registro")
	private Usuario Usuarioregistrocuota;
	
	@ManyToOne
	@JoinColumn(name="cod_solicitud")
	private Solictud listaSolicitud;
	
	
	public Cuota(int cod) {
		codigo=cod;
	}
	
}
