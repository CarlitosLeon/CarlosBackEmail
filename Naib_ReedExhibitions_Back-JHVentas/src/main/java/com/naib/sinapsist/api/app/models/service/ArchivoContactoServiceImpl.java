package com.naib.sinapsist.api.app.models.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.dao.IArchivoContactoDao;
import com.naib.sinapsist.api.app.models.entity.ArchivoContacto;

@Service
public class ArchivoContactoServiceImpl implements IArchivoContactoService {
	@Autowired
	private IArchivoContactoDao ArchivoContactoDa;
	
	@Override
	public List<ArchivoContacto> findAll() {
		return (List<ArchivoContacto>)ArchivoContactoDa.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ArchivoContacto> getarchivoContacto(int idaC) {
		return ArchivoContactoDa.getarchivoContacto(idaC);
	}

	@Override
	public ArchivoContacto save(ArchivoContacto archivoContacto) {
		return ArchivoContactoDa.save(archivoContacto);
	}

	@Override
	public String subirArchivo(MultipartFile archivo) {
		String nombre = UUID.randomUUID().toString()+ "_" + archivo.getOriginalFilename().replace("", "");
		//Path rutaArchivo = Paths.get("/home/projects/uploadsCRM/"+nameImg);
		Path rutaArchivo = Paths.get("uploadsCRM/archivosEmail").resolve(nombre).toAbsolutePath();
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			return nombre;
		}catch(IOException e) {
			return(e.getMessage() + " : " + e.getCause().toString());
		}
		
	}

}
