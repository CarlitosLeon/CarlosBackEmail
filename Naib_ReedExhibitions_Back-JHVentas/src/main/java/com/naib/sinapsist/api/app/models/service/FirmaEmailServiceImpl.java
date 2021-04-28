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

import com.naib.sinapsist.api.app.models.dao.IFirmaEmailDao;
import com.naib.sinapsist.api.app.models.entity.FirmaEmail;

@Service
public class FirmaEmailServiceImpl implements IFirmaEmailService{
	@Autowired
	private IFirmaEmailDao FirmaEmailDa;

	@Override
	@Transactional(readOnly = true)
	public List<FirmaEmail> finAll() {
		return (List<FirmaEmail>) FirmaEmailDa.findAll();
	}

	@Override
	public FirmaEmail save(FirmaEmail firmaEmail) {
		return FirmaEmailDa.save(firmaEmail);
	}

	@Override
	public String subirArchivo(MultipartFile archivo) {
		String nombre = UUID.randomUUID().toString()+ "_" + archivo.getOriginalFilename().replace("", "");
		//Path rutaArchivo = Paths.get("/home/projects/uploadsCRM/"+nameImg);
		Path rutaArchivo = Paths.get("uploadsCRM/imgFirma").resolve(nombre).toAbsolutePath();
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			return nombre;
		}catch(IOException e) {
			return(e.getMessage() + " : " + e.getCause().toString());
		}
	}

	@Override
	public FirmaEmail findById(int idE) {
		return FirmaEmailDa.findById(idE);
	}

}
