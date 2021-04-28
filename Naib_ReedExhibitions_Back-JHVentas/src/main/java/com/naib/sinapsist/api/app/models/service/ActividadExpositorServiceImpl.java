package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IActividadExpositorDao;
import com.naib.sinapsist.api.app.models.entity.ActividadExpositor;

@Service
public class ActividadExpositorServiceImpl implements IActividadExpositorService {

	@Autowired
	private IActividadExpositorDao actividadExpositorDao;

	@Override
	public ActividadExpositor save(ActividadExpositor actividad) {
		return actividadExpositorDao.save(actividad);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActividadExpositor> getHistorialExp(int idCE) {
		return actividadExpositorDao.getHistorialExp(idCE);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActividadExpositor> getActividadesExpositor(int idEx, int idEv) {
		return actividadExpositorDao.getActividadesExpositor(idEx, idEv);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActividadExpositor> getAllHistorialExp(int idCE) {
		return actividadExpositorDao.getAllHistorialExp(idCE);
	}

}
