package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IEvidenciaPorcentajeArmadoDao;
import com.naib.sinapsist.api.app.models.entity.EvidenciaPorcentajeArmado;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author Synapsist-Serv
 */
@Service
public class EvidenciaPorcentajeArmadoImpl implements IEvidenciaPorcentajeArmado{

    @Autowired
    IEvidenciaPorcentajeArmadoDao porcentajeArmadoDao;
    
    @Override
    public List<EvidenciaPorcentajeArmado> findAllEvidenciasById(int id) {
        return porcentajeArmadoDao.findEvidenciaById(id);
    }
    
    @Override
    public String subirEvidencia(MultipartFile archivo) {
        String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
        //Path rutaArchivo = Paths.get("/home/projects/evidenciasArmado/"+nombre);
        Path rutaArchivo = Paths.get("evidenciasArmado").resolve(nombre).toAbsolutePath();
        try {
            Files.copy(archivo.getInputStream(), rutaArchivo);
            return nombre;
        } catch (IOException e) {
            return (e.getMessage() + " : " + e.getCause().toString());
        }
    }


    @Override
    public EvidenciaPorcentajeArmado BuscarEvidenciasArmado(int id) {
        return porcentajeArmadoDao.findById(id);
    }

    @Override
    public EvidenciaPorcentajeArmado guardarEvidenciaArmado(EvidenciaPorcentajeArmado arm) {
        return porcentajeArmadoDao.save(arm);
    }
}
