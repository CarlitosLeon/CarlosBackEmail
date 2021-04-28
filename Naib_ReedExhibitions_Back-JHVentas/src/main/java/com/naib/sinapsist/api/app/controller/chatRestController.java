package com.naib.sinapsist.api.app.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.ChatIncidencia;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.Incidencia;
import com.naib.sinapsist.api.app.models.service.IChatIncidenciaService;
import com.naib.sinapsist.api.app.models.service.IDetalleUsuarioService;
import com.naib.sinapsist.api.app.models.service.IIncidenciaService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200","*" })
@RequestMapping("/chat")
public class chatRestController {

	@Autowired
	private IIncidenciaService incidenciaService;

	@Autowired
	private IDetalleUsuarioService detalleUsuarioService;

	@Autowired
	private IChatIncidenciaService chatService;

	@PostMapping("/sendImg/{ticket}/{idU}")
	public ResponseEntity<?> sendImage(@RequestParam("img") MultipartFile img, @PathVariable("ticket") int ticket,
			@PathVariable int idU) {
		Map<String, Object> response = new HashMap<>();
		Incidencia incidencia = null;
		Integer dUser = 0;
		DetalleUsuario detailUser = null;

		if (!img.isEmpty()) {
			try {
				incidencia = incidenciaService.findById(ticket);
				if (incidencia != null) {
					dUser = detalleUsuarioService.findIdDetailUser(idU);
					if (dUser != null) {
						String responseUpload = chatService.subirEvidencia(img);
						if (responseUpload.contains(":")) {
							response.put("mensaje", "Error al subir la imagen al servidor");
							response.put("error", responseUpload);
							return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
						} else {
							detailUser = detalleUsuarioService.findById(dUser);
							ChatIncidencia chat = new ChatIncidencia();
							chat.setIncidencia(incidencia);
							chat.setDetalleUsuario(detailUser);
							chat.setMensaje(responseUpload);
							chat.setTipo("2");
							ChatIncidencia chatenviado = chatService.guardarEvidenciaInci(chat);
							response.put("chat", chatenviado);
						}
					} else {
						response.put("message", "Usuario sin detalle");
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
					}
				} else {
					response.put("message", "Número de ticket invalido");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
			} catch (DataAccessException e) {
				response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/sendMessage/{ticket}/{idU}")
	public ResponseEntity<?> sendMessage(@RequestBody ChatIncidencia Message, @PathVariable("ticket") int ticket,
			@PathVariable int idU) {
		Map<String, Object> response = new HashMap<>();
		Incidencia incidencia = null;
		Integer dUser = 0;
		DetalleUsuario detailUser = null;

		if (Message != null && Message.getMensaje() != "") {
			try {
				incidencia = incidenciaService.findById(ticket);
				if (incidencia != null) {
					dUser = detalleUsuarioService.findIdDetailUser(idU);
					if (dUser != null) {
						detailUser = detalleUsuarioService.findById(dUser);
						ChatIncidencia chat = new ChatIncidencia();
						chat.setIncidencia(incidencia);
						chat.setDetalleUsuario(detailUser);
						chat.setMensaje(Message.getMensaje());
						chat.setTipo("1");
						ChatIncidencia chatenviado = chatService.guardarEvidenciaInci(chat);
						response.put("chat", chatenviado);

					} else {
						response.put("message", "Usuario sin detalle");
						return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
					}
				} else {
					response.put("message", "Número de ticket invalido");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
			} catch (DataAccessException e) {
				response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			response.put("message", "El mensaje no puede estar vacio");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getMessage/img/{nameImg:.+}")
	public ResponseEntity<Resource> viewImgChat(@PathVariable String nameImg) {
		//Path rutaArchivo = Paths.get("/home/projects/evidenciasChat/"+nameImg);
		Path rutaArchivo = Paths.get("evidenciasChat").resolve(nameImg).toAbsolutePath();
		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error: No se pudo cargar la imagen " + nameImg);
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/getConversation/{ticket}")
	public ResponseEntity<?> getMessageTicket(@PathVariable int ticket){
		Map<String, Object> response = new HashMap<>();
		List<ChatIncidencia> conversation = null;
		Incidencia incidencia = null;
		
		try {
			
			if(incidenciaService.findById(ticket) != null) {
				conversation = chatService.getConversacion(ticket);
			}else {
				response.put("message", "Número de ticket invalido");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		return new ResponseEntity<List<ChatIncidencia>>(conversation, HttpStatus.OK);
	}
	
}
