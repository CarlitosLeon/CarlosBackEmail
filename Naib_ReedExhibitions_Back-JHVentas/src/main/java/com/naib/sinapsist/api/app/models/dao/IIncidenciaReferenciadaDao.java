/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.dao;


import com.naib.sinapsist.api.app.models.entity.IncidenciaReferenciada;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Synapsist-Serv
 */
public interface IIncidenciaReferenciadaDao extends CrudRepository<IncidenciaReferenciada, Integer>{
   
    @Query("FROM IncidenciaReferenciada ifr WHERE ifr.stand.id=?1")
    public List<IncidenciaReferenciada> findIncidenciasReferenciadas(int id);
    
    public IncidenciaReferenciada findById(int id);
    
    public IncidenciaReferenciada save(IncidenciaReferenciada incidencia);
    
    @Transactional
    @Modifying
    @Query("UPDATE Incidencia inc SET inc.status='0' WHERE inc.ticket=?1")
    int updateToNoIniciada(int id);
    
    @Transactional
    @Modifying
    @Query("UPDATE Incidencia inc SET inc.status='1' WHERE inc.ticket=?1")
    int updateToProceso(int id);
    
    @Transactional
    @Modifying
    @Query("UPDATE Incidencia inc SET inc.status='2' WHERE inc.ticket=?1")
    int updateToTerminada(int id);
}
