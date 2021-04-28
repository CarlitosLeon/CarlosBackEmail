package com.naib.sinapsist.api.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.Evento;

public interface IEventoDao extends CrudRepository<Evento, Integer> {

	@Query("SELECT e FROM Evento e join e.usuarios du join du.usuario u WHERE u.id=?1 AND du.status=?2")
	public Evento detailUser(int idU, int sts);

	public Evento findById(int idE);
}
