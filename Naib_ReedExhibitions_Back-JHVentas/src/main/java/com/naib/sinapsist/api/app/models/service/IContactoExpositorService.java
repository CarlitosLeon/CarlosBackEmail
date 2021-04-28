package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import com.naib.sinapsist.api.app.models.entity.ContactoExpositor;


public interface IContactoExpositorService {

	public List<ContactoExpositor> getContactoExpositor(int idEx);	

	public void deleteById(int idC);

	public ContactoExpositor findById(int idC);

	public ContactoExpositor save(ContactoExpositor contacto);

	public String subirImgContacto(MultipartFile file);
	
	public String delteImgContacto(String nombreFoto);
	
	public List<ContactoExpositor> findAll(); 
}
