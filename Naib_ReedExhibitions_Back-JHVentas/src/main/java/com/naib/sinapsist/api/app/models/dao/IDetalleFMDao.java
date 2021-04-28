/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.DetalleFM;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Synapsist-Serv
 */
public interface IDetalleFMDao extends CrudRepository<DetalleFM, Integer> {

	@Query("FROM DetalleFM fm WHERE fm.zona.evento.id=?1")
	public List<DetalleFM> findListDetalleFMByEventoId(int id);

	public DetalleFM save(DetalleFM detalle);

	@Query("SELECT d FROM DetalleFM d JOIN d.zona z JOIN z.evento e WHERE e.id=?1")
	public List<DetalleFM> getFMEvento(int idE);

}
