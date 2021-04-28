package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.Expositor;

public interface IExpositorDao extends CrudRepository<Expositor, Integer> {

	public List<Expositor> findAll();

	public Expositor findById(int idExp);

	@SuppressWarnings("unchecked")
	public Expositor save(Expositor expositor);
	
	public void deleteById(int id);
	
	@Query("SELECT COUNT(e) FROM Expositor e WHERE e.rfc = ?1")
	public Integer consRfc(String rfc);

}
