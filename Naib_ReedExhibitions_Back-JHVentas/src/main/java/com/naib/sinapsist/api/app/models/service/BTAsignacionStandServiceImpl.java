package com.naib.sinapsist.api.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naib.sinapsist.api.app.models.dao.IBTAsignacionStandDao;
import com.naib.sinapsist.api.app.models.entity.BTAsignacionStand;

@Service
public class BTAsignacionStandServiceImpl implements IBTAsignacionStandService{
	
	@Autowired
	private IBTAsignacionStandDao btAsignacionStandDao;

	@Override
	public BTAsignacionStand save(BTAsignacionStand bitacora) {
		return btAsignacionStandDao.save(bitacora);
	}

}
