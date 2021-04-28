/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.DetalleFM;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.RenderDro;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;

/**
 *
 * @author Synapsist-Serv
 */

public interface IStandReferenciaService {

	public Stand getStandReferenciaById(int id);

	public List<RenderDro> getRenderDroByStand(int id);

	public Stand guardarRegistroStand(Stand refe);

	public List<Stand> getListaExpositores(int id);

	public int updateStandToEtiquetado(int id);

	public int updateStandToDetalle(int id);

	public DetalleUsuario getDetalleUsuario(int id);

	public Stand findById(int id);

	public List<DetalleFM> getFMZonasByEvento(int id);

	public void deleteById(int idS);

	public int countStatusCrm(String status, int idE);
	
	public int consultaSiTieneStands(int idEx);
}
