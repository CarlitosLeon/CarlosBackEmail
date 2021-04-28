package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.ActividadExpositor;

public interface IActividadExpositorService {

	public ActividadExpositor save(ActividadExpositor actividad);

	public List<ActividadExpositor> getHistorialExp(int idCE);

	public List<ActividadExpositor> getActividadesExpositor(int idEx, int idEv);
	
	public List<ActividadExpositor> getAllHistorialExp(int idCE);

}
