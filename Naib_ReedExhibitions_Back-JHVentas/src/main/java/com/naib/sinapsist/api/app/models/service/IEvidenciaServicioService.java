/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.EvidenciaServicio;
import java.util.List;

/**
 *
 * @author Synapsist-Serv
 */
public interface IEvidenciaServicioService {
    
    public EvidenciaServicio guardarRegistroEvidencia(EvidenciaServicio evidencia);
    
    public List<EvidenciaServicio> getEvidenciasServicio(int id);
}
