package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IReunionEventoDao;
import com.naib.sinapsist.api.app.models.entity.ReunionEvento;

@Service
public class ReunionEventoServiceImpl implements IReunionEventoService{
	
	@Autowired
	private IReunionEventoDao reunionDao;

	@Override
	@Transactional(readOnly = true)
	public List<ReunionEvento> getAllReunion(int idE) {
		return reunionDao.getAllReunion(idE);
	}

	@Override
	@Transactional
	public ReunionEvento save(ReunionEvento reunionEvento) {
		return reunionDao.save(reunionEvento);
	}

	@Override
	public ReunionEvento findById(int idRE) {
		return reunionDao.findById(idRE);
	}

}
