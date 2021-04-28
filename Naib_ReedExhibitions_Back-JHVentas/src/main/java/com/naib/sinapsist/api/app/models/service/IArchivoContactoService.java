package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.ArchivoContacto;

public interface IArchivoContactoService {
	
	public List<ArchivoContacto> findAll();
	
	public List<ArchivoContacto>getarchivoContacto(int idaC);
	
	public ArchivoContacto save(ArchivoContacto archivoContacto);
	
	public String subirArchivo(MultipartFile file);

}
