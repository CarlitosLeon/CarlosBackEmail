/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IStandReferenciaDao;
import com.naib.sinapsist.api.app.models.entity.DetalleFM;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.RenderDro;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Synapsist-Serv
 */
@Service
public class StandReferenciaServiceImpl implements IStandReferenciaService {

	@Autowired
	private IStandReferenciaDao standRefeDao;

	@Override
	public Stand getStandReferenciaById(int id) {
		return standRefeDao.findById(id);
	}

	@Override
	public List<RenderDro> getRenderDroByStand(int id) {
		return standRefeDao.findDroById(id);
	}

	@Override
	public Stand guardarRegistroStand(Stand refe) {
		return standRefeDao.save(refe);
	}

	@Override
	public List<Stand> getListaExpositores(int id) {
		return standRefeDao.findAllById(id);
	}

	@Override
	public DetalleUsuario getDetalleUsuario(int id) {
		return standRefeDao.findDetalleUsuarioById(id);
	}

	@Override
	public int updateStandToEtiquetado(int id) {
		return standRefeDao.updateStatusStandEtiquetado(id);
	}

	@Override
	public int updateStandToDetalle(int id) {
		return standRefeDao.updateStatusStandDetalle(id);
	}

	@Override
	public Stand findById(int id) {
		return standRefeDao.findById(id);
	}

	@Override
	public List<DetalleFM> getFMZonasByEvento(int id) {
		return standRefeDao.findFMZonasEventoById(id);
	}

	@Override
	public void deleteById(int idS) {
		standRefeDao.deleteById(idS);
	}

	@Override
	public int countStatusCrm(String status, int idE) {
		return standRefeDao.countStatusCrm(status, idE);
	}

	@Override
	public int consultaSiTieneStands(int idEx) {
		return standRefeDao.consultaSiTieneStands(idEx);
	}

}
