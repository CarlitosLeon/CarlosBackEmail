package com.naib.sinapsist.api.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naib.sinapsist.api.app.models.entity.Componente;
import com.naib.sinapsist.api.app.models.entity.Stand;
import com.naib.sinapsist.api.app.models.service.IMapaVentasService;

@RestController
@RequestMapping("/public/plano-comercial/")
public class PlanoComercialRestController {
	
	@Autowired
	private IMapaVentasService mapaService;
	
	@PostMapping("/data/{idE}")
	public ResponseEntity<?> listaComponentesStandMapa(@PathVariable int idE) {
		Map<String, Object> response = new HashMap<>();
		List<Stand> stands = new ArrayList<Stand>();
		try {
			stands = mapaService.getAllReferenciasStandMapa(idE);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Stand>>(stands, HttpStatus.OK);
	}
	
	@PostMapping("/data/component/{idE}")
	public ResponseEntity<?> allComponents(@PathVariable int idE) {
		Map<String, Object> response = new HashMap<>();
		List<Componente> components = new ArrayList<Componente>();
		try {
			components = mapaService.getAllComponentesActivos(idE);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Componente>>(components, HttpStatus.OK);
	}

}
