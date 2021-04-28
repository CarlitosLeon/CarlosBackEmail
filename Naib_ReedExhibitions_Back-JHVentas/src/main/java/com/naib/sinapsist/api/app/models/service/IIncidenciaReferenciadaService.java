/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.IncidenciaReferenciada;
import java.util.List;

/**
 *
 * @author Synapsist-Serv
 */
public interface IIncidenciaReferenciadaService {
    
    public List<IncidenciaReferenciada> getIncidenciasByIdStand (int id);
    
    public IncidenciaReferenciada getIncidenciaReferenciadaById (int id);
    
    public IncidenciaReferenciada actualizarIncidenciaReferenciada(IncidenciaReferenciada incidencia);
    
    public  int updateStatusNoIniciada(int id);
    
    public  int updateStatusProceso(int id);
    
    public  int updateStatusTerminada(int id);
    
    public IncidenciaReferenciada save(IncidenciaReferenciada incidencia);
}
