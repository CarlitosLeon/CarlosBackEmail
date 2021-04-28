/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.Zona;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Synapsist-Serv
 */
public interface IAdminFMDao extends CrudRepository<Zona, Integer> {

	@Query("FROM Zona z WHERE z.evento.id=?1")
	public List<Zona> zonasInEvento(int idevento);

	@Query("FROM DetalleUsuario det WHERE det.usuario.rol.id=1 AND det.evento.id=?1 ORDER BY det.id")
	public List<DetalleUsuario> findDetalleById(int idEvento);

}
