/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IAdminFMDao;
import com.naib.sinapsist.api.app.models.dao.IDetalleFMDao;
import com.naib.sinapsist.api.app.models.dao.IHorarioFMDao;
import com.naib.sinapsist.api.app.models.entity.DetalleFM;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.HorarioFM;
import com.naib.sinapsist.api.app.models.entity.Zona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Synapsist-Serv
 */
@Service
public class AsignacionFMImpl implements IAsignacionFM {

	@Autowired
	private IAdminFMDao adminDao;

	@Autowired
	private IHorarioFMDao horarioDao;

	@Autowired
	private IDetalleFMDao detalleDao;

	@Override
	public List<DetalleUsuario> getFloorManager(int id) {
		return adminDao.findDetalleById(id);
	}

	@Override
	public List<Zona> getListZonaByEvento(int id) {
		return adminDao.zonasInEvento(id);
	}

	@Override
	public DetalleFM saveDetalleFM(DetalleFM detalle) {
		return detalleDao.save(detalle);
	}

	@Override
	public HorarioFM saveHorarioFM(HorarioFM horario) {
		return horarioDao.save(horario);
	}

	@Override
	public List<DetalleFM> findListDetalleFMByEventoId(int id) {
		return detalleDao.findListDetalleFMByEventoId(id);
	}

	@Override
	public void DeleteHorario(int id) {
		horarioDao.deleteById(id);
	}

}
