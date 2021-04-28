package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naib.sinapsist.api.app.models.dao.IAgendaDao;
import com.naib.sinapsist.api.app.models.entity.Agenda;

@Service
public class AgendaServiceImpl implements IAgendaService {

	@Autowired
	private IAgendaDao agendaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Agenda> getAllEventContacts(int idE) {
		return agendaDao.getAllEventContacts(idE);
	}

	@Override
	public Agenda save(Agenda agenda) {
		return agendaDao.save(agenda);
	}

	@Override
	public Agenda findById(int idA) {
		return agendaDao.findById(idA);
	}

}
