package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.naib.sinapsist.api.app.models.entity.ReunionEvento;

public interface IReunionEventoDao extends JpaRepository<ReunionEvento, Integer>{
	
	@Query("SELECT r FROM ReunionEvento r WHERE r.evento.id = ?1")
	public List<ReunionEvento> getAllReunion(int idE);
	
	@SuppressWarnings("unchecked")
	public ReunionEvento save(ReunionEvento reunionEvento);
	
	public ReunionEvento findById(int idRE);

}
