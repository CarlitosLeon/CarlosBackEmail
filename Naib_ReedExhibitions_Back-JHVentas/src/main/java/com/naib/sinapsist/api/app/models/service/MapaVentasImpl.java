package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IComponenteDao;
import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.Componente;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MapaVentasImpl implements IMapaVentasService {

	@Autowired
	private IComponenteDao mapaVentasDao;

	@Override
	public List<Stand> getAllReferenciasStandMapa(int id) {
		return mapaVentasDao.findStandById(id);
	}

	@Override
	public List<Componente> getAllComponentesActivos(int id) {
		return mapaVentasDao.findComponenteById(id);
	}

	@Override
	public List<CarteraEvento> getAllExpositoresCRM(int id) {
		return mapaVentasDao.findCarteraEventoById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Componente findById(int idCom) {
		return mapaVentasDao.findById(idCom);
	}

	@Override
	public int updateStatusReubicacion(boolean status, int id) {
		return mapaVentasDao.updateStatusExpositorReubicacion(status, id);
	}

}