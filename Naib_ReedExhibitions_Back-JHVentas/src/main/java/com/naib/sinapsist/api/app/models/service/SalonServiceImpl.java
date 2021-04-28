package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.ISalonDao;
import com.naib.sinapsist.api.app.models.entity.Salon;

@Service
public class SalonServiceImpl implements ISalonService{

	@Autowired
	private ISalonDao salonDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Salon> findSalonEvento(int idE) {
		return salonDao.findSalonEvento(idE);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Salon findById(int idS) {
		return salonDao.findById(idS);
	}

}
