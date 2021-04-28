package com.naib.sinapsist.api.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naib.sinapsist.api.app.models.entity.Evento;
import com.naib.sinapsist.api.app.models.entity.MetaVendedor;
import com.naib.sinapsist.api.app.models.entity.Usuario;
import com.naib.sinapsist.api.app.models.service.IEventoService;
import com.naib.sinapsist.api.app.models.service.IMetaVendedorService;
import com.naib.sinapsist.api.app.models.service.IUsuarioService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequestMapping("/user")
public class UsuarioRestController {

	@Autowired
	private IEventoService eventoService;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IMetaVendedorService metaVendedorService;

	@GetMapping("/evento/{idU}")
	public ResponseEntity<?> detalleEvento(@PathVariable int idU) {
		Usuario user = null;
		Evento detalleUsuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			user = usuarioService.findById(idU);
			if (user != null) {
				detalleUsuario = eventoService.detailUser(user.getId(), 1);
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			response.put("status", 0);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (user == null) {
			response.put("message", "Usuario Invalido");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else if (user != null) {
			if (detalleUsuario == null) {
				response.put("message", "Usuario sin detalle");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		}

		return new ResponseEntity<Evento>(detalleUsuario, HttpStatus.OK);
	}

	@PostMapping("/evento/vendedor/{idU}")
	public ResponseEntity<?> detalleVendedor(@PathVariable int idU) {
		Map<String, Object> response = new HashMap<>();
		List<MetaVendedor> ev = null;
		try {
			
			ev = metaVendedorService.getEventoVendedor(idU);

			if(ev.isEmpty()) {
				response.put("message", "Usuario sin detalle");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			response.put("status", 0);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<MetaVendedor>>(ev, HttpStatus.OK);
	}

}
