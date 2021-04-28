package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.Componente;
import com.naib.sinapsist.api.app.models.entity.Stand;
import java.util.List;

public interface IMapaVentasService {

	public List<Stand> getAllReferenciasStandMapa(int id);

	public List<Componente> getAllComponentesActivos(int id);

	public List<CarteraEvento> getAllExpositoresCRM(int id);

	public Componente findById(int idCom);

	public int updateStatusReubicacion(boolean status, int id);
}