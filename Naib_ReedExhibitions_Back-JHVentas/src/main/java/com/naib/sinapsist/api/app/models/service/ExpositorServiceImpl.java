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

import com.naib.sinapsist.api.app.models.dao.IExpositorDao;
import com.naib.sinapsist.api.app.models.entity.Expositor;

@Service
public class ExpositorServiceImpl implements IExpositorService {

	@Autowired
	private IExpositorDao expositorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Expositor> findAll() {

		return (List<Expositor>) expositorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Expositor findById(int idExp) {
		return expositorDao.findById(idExp);
	}

	@Override
	@Transactional
	public Expositor save(Expositor expositor) {
		return expositorDao.save(expositor);
	}

	@Override
	public void deleteById(int id) {
		expositorDao.deleteById(id);
	}

	@Override
	public String subirImgExpositor(MultipartFile archivo) {
		String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		// Path rutaArchivo = Paths.get("/home/projects/imgExpositor/"+nombre);
		Path rutaArchivo = Paths.get("imgExpositor").resolve(nombre).toAbsolutePath();
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			return nombre;
		} catch (IOException e) {
			return (e.getMessage() + " : " + e.getCause().toString());
		}
	}

	@Override
	public Integer consRfc(String rfc) {
		return expositorDao.consRfc(rfc);
	}

	@Override
	public String delteImgExpositor(String nombreFoto) {
		// Path fotoDelete = Paths.get("/home/projects/imgExpositor/"+nombreFoto);
		Path fotoDelete = Paths.get("imgExpositor").resolve(nombreFoto).toAbsolutePath();
		try {
			Files.delete(fotoDelete);
			return "Success";
		} catch (IOException e) {
			return (e.getMessage() + " : " + e.getCause().toString());
		}
	}

}
