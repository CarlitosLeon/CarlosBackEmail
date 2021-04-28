/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.HorarioFM;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Synapsist-Serv
 */
public interface IHorarioFMDao extends CrudRepository<HorarioFM, Integer> {

	public HorarioFM save(HorarioFM horario);
	
	@Query("SELECT h.horarios FROM DetalleFM h JOIN h.zona z JOIN z.evento e WHERE e.id=?1 Order by date(entrada)")
	public List<HorarioFM> getHorarios(int id);
}
