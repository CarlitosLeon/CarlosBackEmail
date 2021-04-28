package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IDetalleUsuarioDao extends CrudRepository<DetalleUsuario, Integer>{

    @Query("SELECT d.id From DetalleUsuario d join d.usuario u WHERE u.id=?1 AND d.status=1")
	public Integer findIdDetailUser(int id);
    
    public DetalleUsuario findById(int idDU);
    
}