/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;

import com.naib.sinapsist.api.app.models.entity.EvidenciaPorcentajeArmado;
import com.naib.sinapsist.api.app.models.entity.Expositor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Synapsist-Serv
 */
public interface IEvidenciaPorcentajeArmadoDao extends CrudRepository<Expositor, Integer>{
    
    @Query("SELECT ar FROM EvidenciaPorcentajeArmado ar WHERE ar.idStandReferencia=?1")
    public List<EvidenciaPorcentajeArmado> findEvidenciaById(int id);
    
    @Query("FROM EvidenciaPorcentajeArmado ar WHERE ar.idStandReferencia=?1")
    public EvidenciaPorcentajeArmado findById(int ind);
    
    
    public EvidenciaPorcentajeArmado save(EvidenciaPorcentajeArmado arm);
}
