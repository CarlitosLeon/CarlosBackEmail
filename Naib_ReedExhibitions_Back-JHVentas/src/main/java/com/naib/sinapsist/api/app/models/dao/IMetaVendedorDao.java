package com.naib.sinapsist.api.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.MetaVendedor;

public interface IMetaVendedorDao extends CrudRepository<MetaVendedor, Integer> {

	@Query("SELECT mv FROM MetaVendedor mv WHERE mv.vendedor.id=?1")
	public List<MetaVendedor> getEventoVendedor(int idU);

}
