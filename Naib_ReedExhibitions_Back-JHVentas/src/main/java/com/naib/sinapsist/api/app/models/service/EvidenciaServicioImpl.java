/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IEvidenciaServicioDao;
import com.naib.sinapsist.api.app.models.entity.EvidenciaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Synapsist-Serv
 */
@Service
public class EvidenciaServicioImpl implements IEvidenciaServicioService{

    @Autowired
    IEvidenciaServicioDao evidenciaServicioDao;
    
    @Override
    public EvidenciaServicio guardarRegistroEvidencia(EvidenciaServicio evidencia) {
        return evidenciaServicioDao.save(evidencia);
    }

    @Override
    public List<EvidenciaServicio> getEvidenciasServicio(int id) {
        return evidenciaServicioDao.findEvidenciaById(id);
    }
    
}
