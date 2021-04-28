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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naib.sinapsist.api.app.models.entity.DetalleFM;
import com.naib.sinapsist.api.app.models.entity.HorarioFM;
import com.naib.sinapsist.api.app.models.service.IDetalleFMService;
import com.naib.sinapsist.api.app.models.service.IHorariosFMService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200","*" })
@RequestMapping("/horariosFM")
public class horariosFMController {
	
	@Autowired
	private IHorariosFMService horariosService;
	
	@Autowired
	private IDetalleFMService detalleFMService;
	
	@GetMapping("/consulta/{idE}")
	public ResponseEntity<?> getHorarios(@PathVariable int idE) {

		List<HorarioFM> horariosFM = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			horariosFM = horariosService.getHorarios(idE);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<HorarioFM>>(horariosFM, HttpStatus.OK);
	}

	
	@GetMapping("/consultaResultados/{idE}")
	public ResponseEntity<?> getResultadosHorarios(@PathVariable int idE) {

		List<DetalleFM> detalleFM = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			detalleFM = detalleFMService.getFMEvento(idE);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<DetalleFM>>(detalleFM, HttpStatus.OK);
	}
	
	
	
	

}
