package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.Salon;

public interface ISalonDao extends CrudRepository<Salon, Integer>{
	
	@Query("SELECT s FROM Salon s join s.detalleEvento de join de.evento e WHERE e.id=?1")
	public List<Salon> findSalonEvento(int idE);
	
	public Salon findById(int idS);
}
