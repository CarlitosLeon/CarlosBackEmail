/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IIncidenciaReferenciadaDao;
import com.naib.sinapsist.api.app.models.entity.IncidenciaReferenciada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Synapsist-Serv
 */
@Service
public class IncidenciaReferenciadaImpl implements IIncidenciaReferenciadaService{

    @Autowired
    IIncidenciaReferenciadaDao incidenciaDao;
    
    @Override
    public List<IncidenciaReferenciada> getIncidenciasByIdStand(int id) {
        return incidenciaDao.findIncidenciasReferenciadas(id);
    }

    @Override
    public IncidenciaReferenciada getIncidenciaReferenciadaById(int id) {
        return incidenciaDao.findById(id);
    }

    @Override
    public IncidenciaReferenciada actualizarIncidenciaReferenciada(IncidenciaReferenciada incidencia) {
        return incidenciaDao.save(incidencia);
    }

	@Override
	public IncidenciaReferenciada save(IncidenciaReferenciada incidencia) {
		return incidenciaDao.save(incidencia);
	}

    @Override
    public int updateStatusNoIniciada(int id) {
        return incidenciaDao.updateToNoIniciada(id);
    }

    @Override
    public int updateStatusProceso(int id) {
        return incidenciaDao.updateToProceso(id);
    }

    @Override
    public int updateStatusTerminada(int id) {
        return incidenciaDao.updateToTerminada(id);
    }
    
   
    
}
