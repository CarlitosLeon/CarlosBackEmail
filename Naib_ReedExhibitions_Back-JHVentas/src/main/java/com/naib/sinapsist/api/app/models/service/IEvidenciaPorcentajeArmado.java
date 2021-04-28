/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.EvidenciaPorcentajeArmado;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Synapsist-Serv
 */
public interface IEvidenciaPorcentajeArmado {
    
    public List<EvidenciaPorcentajeArmado> findAllEvidenciasById(int id);

    public String subirEvidencia(MultipartFile file);
    
    public EvidenciaPorcentajeArmado BuscarEvidenciasArmado(int id);
    
    public EvidenciaPorcentajeArmado guardarEvidenciaArmado(EvidenciaPorcentajeArmado arm);
}
