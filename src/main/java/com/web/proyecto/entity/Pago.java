package com.web.proyecto.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="tb_pago")
public class Pago {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="cod_pago")
private Integer codigopago;

@Column(name="monto")
private Double montopago;

@Column(name="fecha_registro")
private LocalDate fecha;

@Column(name="estado")
private int estado;

/*ManyTone*/
@ManyToOne
@JoinColumn(name="cod_cuota")
private Cuota cuotaPago;

@ManyToOne
@JoinColumn(name="cod_forma_pago")
private FormaPago grupoFormaPago;

/**/
@ManyToOne
@JoinColumn(name="cod_usuario_registro")
private Usuario Usuarioregistro;

}
