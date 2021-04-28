/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.EvidenciaServicio;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Synapsist-Serv
 */
public interface IEvidenciaServicioDao extends CrudRepository<EvidenciaServicio, Integer>{
    
    @Query("FROM EvidenciaServicio es WHERE es.detalleContratoServicio.idStandReferencia=?1")
    public List<EvidenciaServicio> findEvidenciaById(int id);
    
    
    public EvidenciaServicio save(EvidenciaServicio evidencia);
}
