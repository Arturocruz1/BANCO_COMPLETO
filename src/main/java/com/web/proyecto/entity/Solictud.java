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
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@Table(name="tb_solicitud")
public class Solictud {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="cod_solicitud")
private Integer codigosoli;

@Column(name="solicitud_monto")
private double solimonto;

@Column(name="fecha_inicio_prestamo")
private Date fechainiciopresta;

@Column (name="fecha_fin_prestamo")
private Date fecha_fin_prestamo;

@Column (name="fecha_registro")
private LocalDate fecharegistro;

@Column (name="solicitud_estado")
private Integer estadosoli;

@Column (name="numero_solicitud")
private String numero;

@Column (name="interes_solicitud")
private Double interes;

@Column (name="numero_cuenta")
private String numCuenta;

/*ManyToOne*/
@ManyToOne
@JoinColumn(name="cod_usuario_registro")
private Usuario Usuarioregistrosoli;

@ManyToOne
@JoinColumn(name="cod_usuario_prestatario")
private Usuario Usuarioprestatariosoli;

@JsonIgnore
@OneToMany(mappedBy = "listaSolicitud")
private List<Cuota> listSolicitud;

/* Agregado Julio*/
@OneToMany(mappedBy = "listaPrestamos")
@JsonIgnore
private List<Prestamo> listPrestamo;

}
