package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IHorarioFMDao;
import com.naib.sinapsist.api.app.models.entity.HorarioFM;

@Service
public class HorariosFMServiceImpl implements IHorariosFMService {

	@Autowired
	private IHorarioFMDao horariosDao;

	@Override
	@Transactional(readOnly = true)
	public List<HorarioFM> getHorarios(int idE) {
		return horariosDao.getHorarios(idE);
	}

}
