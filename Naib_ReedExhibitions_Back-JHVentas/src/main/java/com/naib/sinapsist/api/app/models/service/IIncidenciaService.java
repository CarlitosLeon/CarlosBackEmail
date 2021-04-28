package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.Incidencia;

public interface IIncidenciaService {

	public List<Incidencia> incidenciasReferenciadas(int id);

	public List<Incidencia> incidenciaFME(int idDU, String tipoIncidencia);

	public Incidencia findById(int idI);

	public Incidencia save(Incidencia incidencia);

	public List<Incidencia> incidenciaGeneral(int id);

	public List<Incidencia> incidenciaGeneralSalon(int idS);

	public Integer countTotalIncidencias(int idDu);

	public List<Incidencia> todasIncidencias(int idDu);
}
