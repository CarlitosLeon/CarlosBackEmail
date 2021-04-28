/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.DetalleContratoServicio;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Synapsist-Serv
 */
public interface IServiciosDao extends CrudRepository<DetalleContratoServicio, Integer>{
    
    @Query("FROM DetalleContratoServicio dcs WHERE dcs.idStandReferencia=?1 ORDER BY  dcs.tipoServicio.servicio.id")
    public List<DetalleContratoServicio> findServiciosById(int id);
    
    public DetalleContratoServicio save(DetalleContratoServicio contrato);
    
    public DetalleContratoServicio findById (int id);
    
}
