package com.naib.sinapsist.api.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.naib.sinapsist.api.app.models.entity.BTAsignacionStand;

public interface IBTAsignacionStandDao extends CrudRepository<BTAsignacionStand, Integer> {

	@SuppressWarnings("unchecked")
	public BTAsignacionStand save(BTAsignacionStand bitacora);
}
