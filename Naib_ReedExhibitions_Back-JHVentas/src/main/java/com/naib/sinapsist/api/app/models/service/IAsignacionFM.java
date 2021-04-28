/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.DetalleFM;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.HorarioFM;
import com.naib.sinapsist.api.app.models.entity.Zona;
import java.util.List;

/**
 *
 * @author Synapsist-Serv
 */
public interface IAsignacionFM {
	public List<Zona> getListZonaByEvento(int id);

	public List<DetalleUsuario> getFloorManager(int id);

	public HorarioFM saveHorarioFM(HorarioFM horario);

	public DetalleFM saveDetalleFM(DetalleFM detalle);

	public List<DetalleFM> findListDetalleFMByEventoId(int id);

	public void DeleteHorario(int id);
}
