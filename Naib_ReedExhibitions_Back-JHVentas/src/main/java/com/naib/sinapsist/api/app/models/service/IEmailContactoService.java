package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.ArchivoContacto;
import com.naib.sinapsist.api.app.models.entity.EmailContacto;
import com.naib.sinapsist.api.app.models.entity.FirmaEmail;

public interface IEmailContactoService {

public List<EmailContacto> findAll(); 
	
	public List<EmailContacto> getemailContacto(int ideCon);
	
	public List<ArchivoContacto> getFiles(int idEC);
	
	public List<EmailContacto> getfirmaFile(int idE);
	
	public EmailContacto save(EmailContacto emailContacto);
	
	public 	EmailContacto findById(int idEc);
	
	public void deleteById(int idE);
	
}
