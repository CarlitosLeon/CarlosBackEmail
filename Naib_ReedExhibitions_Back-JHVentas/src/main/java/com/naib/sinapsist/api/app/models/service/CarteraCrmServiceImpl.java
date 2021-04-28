package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.ICarteraCrmDao;
import com.naib.sinapsist.api.app.models.entity.CarteraEvento;

@Service
public class CarteraCrmServiceImpl implements ICarteraCrmService {

	@Autowired
	private ICarteraCrmDao carteraDao;

	@Override
	@Transactional(readOnly = true)
	public List<CarteraEvento> getExpositoresCartera(int id, int idE) {
		return carteraDao.getExpositoresCartera(id, idE);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarteraEvento> getAllExpositoresCartera(int idE) {
		return carteraDao.getAllExpositoresCartera(idE);
	}

	@Override
	@Transactional(readOnly = true)
	public CarteraEvento findById(int id) {
		return carteraDao.findById(id);
	}

	@Override
	public List<CarteraEvento> getRelacionVendedor(int idEx) {
		return carteraDao.getRelacionVendedor(idEx);
	}

	@Override
	public void deleteById(int idCa) {
		carteraDao.deleteById(idCa);
	}

	@Override
	public CarteraEvento save(CarteraEvento cartera) {
		return carteraDao.save(cartera);
	}
}
