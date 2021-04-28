package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naib.sinapsist.api.app.models.entity.Agenda;

public interface IAgendaDao extends JpaRepository<Agenda, Integer> {

	@Query("SELECT a FROM Agenda a WHERE a.evento.id = ?1")
	public List<Agenda> getAllEventContacts(int idE);
	
	public Agenda findById(int idA);

}
