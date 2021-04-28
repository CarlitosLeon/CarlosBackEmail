package com.naib.sinapsist.api.app.models.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IDetalleFMDao;
import com.naib.sinapsist.api.app.models.entity.DetalleFM;

@Service
public class DetalleFMServiceImpl implements IDetalleFMService{
	
	@Autowired
	private IDetalleFMDao detalleFMDao;

	@Override
	@Transactional(readOnly = true)
	public List<DetalleFM> getFMEvento(int idE) {
		return detalleFMDao.getFMEvento(idE); 
	}

}
