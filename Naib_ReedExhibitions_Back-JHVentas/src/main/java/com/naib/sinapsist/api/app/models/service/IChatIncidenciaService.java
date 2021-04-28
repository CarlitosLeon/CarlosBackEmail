package com.naib.sinapsist.api.app.models.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.ChatIncidencia;

public interface IChatIncidenciaService {
	
	public ChatIncidencia guardarEvidenciaInci(ChatIncidencia chat);
	
	public String subirEvidencia(MultipartFile file);
	
	public List<ChatIncidencia> getConversacion(int ticket);

}
