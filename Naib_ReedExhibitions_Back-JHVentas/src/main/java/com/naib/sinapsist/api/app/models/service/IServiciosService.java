/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.entity.DetalleContratoServicio;
import com.naib.sinapsist.api.app.models.entity.EvidenciaServicio;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Synapsist-Serv
 */
public interface IServiciosService {
    public List<DetalleContratoServicio> getDetalleContratoByIdStand(int id);
    
    public String subirEvidencia(MultipartFile file);
    
    public DetalleContratoServicio guardarStatusServicio(DetalleContratoServicio contratoServicio);
    
    public DetalleContratoServicio buscarById(int id);
    
}
