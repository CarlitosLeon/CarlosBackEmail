package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.Evento;

public interface IEventoService {

	public Evento detailUser(int idU, int sts);

	public Evento findById(int idE);

}
