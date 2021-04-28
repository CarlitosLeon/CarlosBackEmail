package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.ActividadExpositor;

public interface IActividadExpositorDao extends CrudRepository<ActividadExpositor, Integer> {

	@SuppressWarnings("unchecked")
	public ActividadExpositor save(ActividadExpositor actividad);

	@Query("SELECT ae FROM ActividadExpositor ae WHERE ae.detalleCartera.id=?1 AND ae.tipo IN('6','7','8','9','10') ORDER BY ae.fecha_hora")
	public List<ActividadExpositor> getHistorialExp(int idCE);

	@Query("SELECT ae FROM ActividadExpositor ae WHERE ae.detalleCartera.expositor.id=?1 AND ae.detalleCartera.evento.id=?2 ORDER BY ae.id DESC")
	public List<ActividadExpositor> getActividadesExpositor(int idEx, int idEv);
	
	@Query("SELECT ae FROM ActividadExpositor ae WHERE ae.detalleCartera.id=?1 ORDER BY ae.fecha_hora DESC")
	public List<ActividadExpositor> getAllHistorialExp(int idCE);

}
