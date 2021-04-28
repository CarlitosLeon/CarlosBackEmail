package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.ReunionEvento;

public interface IReunionEventoService {
	
	public List<ReunionEvento> getAllReunion(int idE);
	
	public ReunionEvento save(ReunionEvento reunionEvento);
	
	public ReunionEvento findById(int idRE);

}
