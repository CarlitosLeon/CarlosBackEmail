package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.CarteraEvento;

public interface ICarteraCrmDao extends CrudRepository<CarteraEvento, Integer> {

	@Query("SELECT c FROM CarteraEvento c WHERE c.vendedor.id=?1 and c.evento.id=?2 Order By c.expositor.id")
	public List<CarteraEvento> getExpositoresCartera(int id, int idE);

	@Query("SELECT c FROM CarteraEvento c WHERE c.evento.id=?1 Order By c.expositor.id")
	public List<CarteraEvento> getAllExpositoresCartera(int idE);

	public CarteraEvento findById(int id);
	
	@Query("SELECT c FROM CarteraEvento c WHERE c.expositor.id=?1")
	public List<CarteraEvento> getRelacionVendedor(int idEx);
	
	public void deleteById(int idCa);
	
	public CarteraEvento save(CarteraEvento cartera);

}
