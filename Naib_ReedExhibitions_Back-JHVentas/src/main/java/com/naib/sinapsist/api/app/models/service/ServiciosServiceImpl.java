package com.naib.sinapsist.api.app.models.service;

import com.naib.sinapsist.api.app.models.dao.IEvidenciaServicioDao;
import com.naib.sinapsist.api.app.models.dao.IServiciosDao;
import com.naib.sinapsist.api.app.models.entity.DetalleContratoServicio;
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
public class ServiciosServiceImpl implements IServiciosService{
    @Autowired
    IServiciosDao serviciosDao;
    
    @Autowired
    IEvidenciaServicioDao evidenciaServiceDao;
    
    @Override
    public List<DetalleContratoServicio> getDetalleContratoByIdStand(int id) {
        return serviciosDao.findServiciosById(id);
    }

    
    @Override
    public String subirEvidencia(MultipartFile archivo) {
        String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
        //Path rutaArchivo = Paths.get("/home/projects/evidenciasServicio/"+nombre);
        Path rutaArchivo = Paths.get("evidenciasServicio").resolve(nombre).toAbsolutePath();
        try {
            Files.copy(archivo.getInputStream(), rutaArchivo);
            return nombre;
        } catch (IOException e) {
            return (e.getMessage() + " : " + e.getCause().toString());
        }
    }

    @Override
    public DetalleContratoServicio guardarStatusServicio(DetalleContratoServicio detalleServicio) {
        return serviciosDao.save(detalleServicio);
    }


    @Override
    public DetalleContratoServicio buscarById(int id) {
        return serviciosDao.findById(id);
    }
    
}
