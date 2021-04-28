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

import com.naib.sinapsist.api.app.models.dao.IWhatsAppContactoDao;
import com.naib.sinapsist.api.app.models.entity.WhatsAppContacto;
@Service
public class WhatsAppContactoServiceImpl implements IWhatsAppContactoService {
    @Autowired
    private IWhatsAppContactoDao WhatsAppContactoDa;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<WhatsAppContacto> findAll() {
		return (List<WhatsAppContacto>) WhatsAppContactoDa.findAll();
	}

	@Override
	public List<WhatsAppContacto> getwhatsContacto(int idwEx) {
		return WhatsAppContactoDa.getwhatsContacto(idwEx);
	}

	@Override
	public WhatsAppContacto save(WhatsAppContacto whatsAppContacto) {
		return WhatsAppContactoDa.save(whatsAppContacto);
	}

	@Override
	public String subirPdfContacto(MultipartFile archivo) {
		String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace("", "");
		//Path rutaArchivo = Paths.get("/home/projects/uploadsCRM/"+nameImg);
		Path rutaArchivo = Paths.get("uploadsCRM").resolve(nombre).toAbsolutePath();
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			return nombre;
		}catch(IOException e) {
			return(e.getMessage() + " : " + e.getCause().toString());
		}
	}
	
	


}
