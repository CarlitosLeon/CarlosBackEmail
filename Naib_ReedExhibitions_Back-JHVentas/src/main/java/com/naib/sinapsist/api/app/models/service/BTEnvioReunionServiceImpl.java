package com.naib.sinapsist.api.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naib.sinapsist.api.app.models.dao.IBTEnvioReunionDao;
import com.naib.sinapsist.api.app.models.entity.BTEnvioReunion;

@Service
public class BTEnvioReunionServiceImpl implements IBTEnvioReunionService{
	
	@Autowired
	private IBTEnvioReunionDao btEnvioReunionDao;

	@Override
	public BTEnvioReunion save(BTEnvioReunion btEnvioReunion) {
		return btEnvioReunionDao.save(btEnvioReunion);
	}
	
	
}
