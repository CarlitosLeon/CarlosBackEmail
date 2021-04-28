package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.WhatsAppContacto;

public interface IWhatsAppContactoService {
	
	public List<WhatsAppContacto> findAll();
	
	public List<WhatsAppContacto> getwhatsContacto(int idwEx);
	
	public WhatsAppContacto save(WhatsAppContacto whatsAppContacto);
	
	public String subirPdfContacto(MultipartFile file);

}
