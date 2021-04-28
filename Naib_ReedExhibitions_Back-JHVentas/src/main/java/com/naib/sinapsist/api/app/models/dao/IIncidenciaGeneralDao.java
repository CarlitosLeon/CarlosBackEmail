package com.naib.sinapsist.api.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.IncidenciaGeneral;

public interface IIncidenciaGeneralDao extends CrudRepository<IncidenciaGeneral, Integer>{
	
	public IncidenciaGeneral save(IncidenciaGeneral inciG);

}
