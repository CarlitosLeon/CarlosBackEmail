package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IIncidenciaDao;
import com.naib.sinapsist.api.app.models.entity.Incidencia;

@Service
public class IncidenciaServiceImpl implements IIncidenciaService {

	@Autowired
	private IIncidenciaDao incidenciaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Incidencia> incidenciasReferenciadas(int id) {
		return incidenciaDao.incidenciasReferenciadas(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Incidencia> incidenciaFME(int idDU, String tipoIncidencia) {
		return incidenciaDao.incidenciaFME(idDU, tipoIncidencia);
	}

	@Override
	@Transactional(readOnly = true)
	public Incidencia findById(int idI) {
		return incidenciaDao.findById(idI);
	}

	@Override
	@Transactional
	public Incidencia save(Incidencia incidencia) {
		return incidenciaDao.save(incidencia);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Incidencia> incidenciaGeneral(int id) {
		return incidenciaDao.incidenciaGeneral(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Incidencia> incidenciaGeneralSalon(int idS) {
		return incidenciaDao.incidenciaGeneralSalon(idS);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer countTotalIncidencias(int idDu) {
		return incidenciaDao.countTotalIncidencias(idDu);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Incidencia> todasIncidencias(int idDu) {
		return incidenciaDao.todasIncidencias(idDu);
	}

}
