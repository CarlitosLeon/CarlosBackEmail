package com.naib.sinapsist.api.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naib.sinapsist.api.app.models.entity.BTEnvioReunion;

public interface IBTEnvioReunionDao extends JpaRepository<BTEnvioReunion, Integer> {
	
	public BTEnvioReunion save(BTEnvioReunion btEnvioReunion);

}
