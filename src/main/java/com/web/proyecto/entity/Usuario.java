package com.web.proyecto.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario",uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class Usuario{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_usuario")
	private Integer codigoUsuario;
	@Column(name = "username",nullable = false,length=100)
	private String username;
	@Column(name = "password",nullable=false,length=200)
	private String password;
	@Column(name = "fecha_registro",nullable=false)
	private LocalDate fechaUsuario;
	@Column(name = "fecha_nacimiento",nullable=false)
	private Date fechaNacimiento;
	@Column(name = "estado",nullable=false)
	private int estadoUsuario;
	@Column(name = "email",nullable=false,length=255)
	private String emailUsuario;
	@Column(name = "flag",nullable = false)
	private int flagUsario;
	@Column(name = "materno",nullable=false,length=255)
	private String marternoUsario;
	@Column(name = "numero_documento",nullable=false)
	private String dniUsario;
	@Column(name = "paterno",nullable=false,length=255)
	private String paternoUsuario;
	@Column(name = "telefono",nullable=false,length=255)
	private String telefonoUsuario;
	
	/*USUARIO SOLICITUD PRESTATARIO*/
	@JsonIgnore
	@OneToMany(mappedBy = "Usuarioprestatariosoli")
	private List<Solictud> listarprestatarioususoli;
	
	/*USUARIO SOLICTUD REGISTRO*/
	@JsonIgnore
	@OneToMany(mappedBy = "Usuarioregistrosoli")
	private List<Solictud> listaregistroususoli;
	
	/*USUARIO HAS ROL*/
	@JsonIgnore
	@OneToMany(mappedBy = "Usuariohasrol")
	private List<UsuarioHasRol> usuahasrol;
	
	/*USUARIO REGISTRO*/
	@JsonIgnore
	@OneToMany(mappedBy = "Usuarioregistro")
	private List<Pago> listaregistrousu;
	
	/*USUARIO REGISTRO POR CUOTA*/
	@JsonIgnore
	@OneToMany(mappedBy = "Usuarioregistrocuota")
	private List<Cuota> listaregistrousucuota;
	
	
	/*---- ONE TOMANY-----*/
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuarioLider")
	private List<Grupo> listaUsuarioLider;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuarioregistro")
	private List<Grupo> listaUsuarioregistro;
	
	//
	
	/*USUARIO HAS BANCO*/
	@JsonIgnore
	@OneToMany(mappedBy = "UsuarioHasBanco")
	private List<BancoHasUsuario> usuariohasbanco;
	
	
	/*Para los usuarios relacionados con el tabla prestamo*/
	@OneToMany(mappedBy = "tbListUsuarioPrestamista")
	@JsonIgnore
	private List<Prestamo>  listPrestamista;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "tbListUsuarioPrestatario")
	private List<Prestamo> listPrestatario;
	
	@OneToMany(mappedBy = "tbListUsuarioRegistra")
	@JsonIgnore
	private List<Prestamo>  listUserRegistra;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "tbListUsuarioActualiza")
	private List<Prestamo> listUserActualiza;
	
	
	
	
	
}
