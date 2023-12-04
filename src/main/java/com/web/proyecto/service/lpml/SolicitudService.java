package com.web.proyecto.service.lpml;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.web.proyecto.entity.Cuota;
import com.web.proyecto.entity.Solictud;
import com.web.proyecto.entity.Usuario;
import com.web.proyecto.repository.SolicitudRepository;

@Service
public class SolicitudService extends ICRUDlpml<Solictud, Integer>{

	@Autowired
	private SolicitudRepository repo;
	
	@Override
	public JpaRepository<Solictud, Integer> getRepository() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	// Generar numero de solicitud
	public String GenerarNumeroSolicitud() {
		return repo.GenerarNumeroSolicitud();
	}
	
	// buscar solicitud por fecha
	public List<Solictud> BuscarSolicitudPorFechaUsuario(LocalDate fechaRegistro, Integer cod_usuario_prestatario) {
		return repo.BuscarSolicitudPorFechaRegistroUsuario(fechaRegistro, cod_usuario_prestatario);
	}
	

	//Buscar entre fecha 1 y fecha 2
	public List<Solictud> listaPorFecha(String fecha1,String fecha2){
		return repo.listaPorFecha(fecha1, fecha2);
	}
	
	
	public List<Cuota> listaDeCuotaPorFecha(String fecha1,String fecha2,int prestatario){
		return listaDeCuotaPorFecha(fecha1, fecha2, prestatario);
	}
	
	public List<Usuario> listaDeUsuarioPorRol(int cod){
		return repo.listaDeUsuarioPorRol(cod);
	}
	
	/*
	public List<Solictud> listaParametros(Date vdesde , Date vhasta ){
		return repo.filtroParametros(vdesde, vhasta);
	}
	*/
	public List<Solictud> listaSolicitudesPorFiltros(int vcod, Date vfechaI, Date vfechaF){
		return repo.solicitudXFiltros(vcod, vfechaI, vfechaF);
	}
	
	public List<Object[]> listaCoutasPrestatario(Integer vcod){
		return repo.obtenerDetallesPrestamoPorUsuario(vcod);
	}
	
	public List<Object[]> listaCoutasPrestatarioFechaVencimiento(Integer vcod, Date fechaVI,Date fechaVF){
		return repo.obtenerDetallesPrestamoPorUsuarioFechaVencimiento(vcod, fechaVI, fechaVF);
	}
	
	public List<Object[]> listaCuotasTodosPrestatarios(){
		return repo.listaCuotaEstado2();
	}
	public List<Object[]> listaCuotasPretatarioFiltros(Integer vcod, Date fechaVI,Date fechaVF){
		return repo.listaCuotaEstado2Filtro(vcod, fechaVI, fechaVF);
	}
	
}
