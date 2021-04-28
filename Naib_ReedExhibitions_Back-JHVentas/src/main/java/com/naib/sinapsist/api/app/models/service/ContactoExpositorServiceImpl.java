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

import com.naib.sinapsist.api.app.models.dao.IContactoExpositorDao;
import com.naib.sinapsist.api.app.models.entity.ContactoExpositor;

@Service
public class ContactoExpositorServiceImpl implements IContactoExpositorService {

	@Autowired
	private IContactoExpositorDao contactoExpositorDao;

	@Override
	@Transactional(readOnly = true)
	public List<ContactoExpositor> getContactoExpositor(int idEx) {
		return contactoExpositorDao.getContactoExpositor(idEx);
	}
	
	

	@Override
	public void deleteById(int idC) {
		contactoExpositorDao.deleteById(idC);
	}

	@Override
	public ContactoExpositor findById(int idC) {
		return contactoExpositorDao.findById(idC);
	}

	@Override
	public ContactoExpositor save(ContactoExpositor contacto) {
		return contactoExpositorDao.save(contacto);
	}

	@Override
	public String subirImgContacto(MultipartFile archivo) {
		String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		// Path rutaArchivo = Paths.get("/home/projects/imgContactoExpositor/"+nombre);
		Path rutaArchivo = Paths.get("imgContactoExpositor").resolve(nombre).toAbsolutePath();
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			return nombre;
		} catch (IOException e) {
			return (e.getMessage() + " : " + e.getCause().toString());
		}
	}

	@Override
	public String delteImgContacto(String nombreFoto) {
		// Path fotoDelete =
		// Paths.get("/home/projects/imgContactoExpositor/"+nombreFoto);
		Path fotoDelete = Paths.get("imgContactoExpositor").resolve(nombreFoto).toAbsolutePath();
		try {
			Files.delete(fotoDelete);
			return "Success";
		} catch (IOException e) {
			return (e.getMessage() + " : " + e.getCause().toString());
		}
	}



	@Override
	public List<ContactoExpositor> findAll() {
		return (List<ContactoExpositor>)contactoExpositorDao.findAll();
	}

}
