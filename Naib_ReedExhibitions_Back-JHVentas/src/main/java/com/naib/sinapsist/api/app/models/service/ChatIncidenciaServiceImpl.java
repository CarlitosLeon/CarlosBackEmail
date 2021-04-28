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

import com.naib.sinapsist.api.app.models.dao.IChatIncidenciaDao;
import com.naib.sinapsist.api.app.models.entity.ChatIncidencia;

@Service
public class ChatIncidenciaServiceImpl implements IChatIncidenciaService {

	@Autowired
	private IChatIncidenciaDao chatDao;

	@Override
	public ChatIncidencia guardarEvidenciaInci(ChatIncidencia chat) {
		return chatDao.save(chat);
	}

	@Override
	public String subirEvidencia(MultipartFile archivo) {
		String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
		//Path rutaArchivo = Paths.get("/home/projects/evidenciasChat/"+nombre);
		Path rutaArchivo = Paths.get("evidenciasChat").resolve(nombre).toAbsolutePath();
		try {
			Files.copy(archivo.getInputStream(), rutaArchivo);
			return nombre;
		} catch (IOException e) {
			return (e.getMessage() + " : " + e.getCause().toString());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ChatIncidencia> getConversacion(int ticket) {
		return chatDao.getConversacion(ticket);
	}

}
