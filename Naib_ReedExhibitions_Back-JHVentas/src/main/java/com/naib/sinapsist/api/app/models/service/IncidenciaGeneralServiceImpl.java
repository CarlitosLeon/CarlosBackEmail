package com.naib.sinapsist.api.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naib.sinapsist.api.app.models.dao.IIncidenciaGeneralDao;
import com.naib.sinapsist.api.app.models.entity.IncidenciaGeneral;

@Service
public class IncidenciaGeneralServiceImpl implements IIncidenciaGeneralService{
	
	@Autowired
	private IIncidenciaGeneralDao incidenciaDao;
	
	@Override
	public IncidenciaGeneral save(IncidenciaGeneral inciG) {
		return incidenciaDao.save(inciG);
	}

}
