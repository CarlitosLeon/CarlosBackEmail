package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import com.naib.sinapsist.api.app.models.entity.Agenda;

public interface IAgendaService {

	public List<Agenda> getAllEventContacts(int idE);

	public Agenda save(Agenda agenda);

	public Agenda findById(int idA);

}
