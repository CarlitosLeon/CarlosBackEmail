package com.naib.sinapsist.api.app.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naib.sinapsist.api.app.models.entity.Agenda;
import com.naib.sinapsist.api.app.models.entity.BTEnvioReunion;
import com.naib.sinapsist.api.app.models.entity.Evento;
import com.naib.sinapsist.api.app.models.entity.ReunionEvento;
import com.naib.sinapsist.api.app.models.service.IAgendaService;
import com.naib.sinapsist.api.app.models.service.IBTEnvioReunionService;
import com.naib.sinapsist.api.app.models.service.IEventoService;
import com.naib.sinapsist.api.app.models.service.IReunionEventoService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequestMapping("/reuniones")
public class ReunionRestController {

	@Autowired
	private IReunionEventoService reunionService;

	@Autowired
	private IBTEnvioReunionService btEnvioReunionService;

	@Autowired
	private IAgendaService agendaService;
	
	@Autowired
	private IEventoService eventoService;

	@PostMapping("/eventoAll/{idE}")
	public ResponseEntity<?> getReunionEvento(@PathVariable int idE) {

		List<ReunionEvento> reunion = null;
		Map<String, Object> response = new HashMap<>();
		try {
			if (idE != 0) {
				reunion = reunionService.getAllReunion(idE);
			} else {
				response.put("message", "Evento invalido");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<ReunionEvento>>(reunion, HttpStatus.OK);
	}

	@PutMapping("/cerrarReunion")
	public ResponseEntity<?> updateReunionEvento(@RequestBody ReunionEvento reunionEvento) {

		ReunionEvento reunionExist = null;
		Map<String, Object> response = new HashMap<>();

		try {
			reunionExist = reunionService.findById(reunionEvento.getId());
			if (reunionExist != null) {
				reunionExist.setStatus(1);

				for (BTEnvioReunion bt : reunionExist.getBitacora()) {
					BTEnvioReunion b = new BTEnvioReunion();
					if (bt.getStatus() == 0) {
						b.setAgenda(bt.getAgenda());
						b.setFecha_hora(Calendar.getInstance());
						b.setStatus(1);
						b.setReunion(reunionExist);
						btEnvioReunionService.save(b);
					}
				}
				reunionExist.setTemas(reunionEvento.getTemas());
				reunionExist = reunionService.save(reunionExist);
			} else {
				response.put("message", "Evento invalido");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<ReunionEvento>(reunionExist, HttpStatus.OK);
	}

	@PostMapping("/contactos/evento/{idE}")
	public ResponseEntity<?> getContactosEvento(@PathVariable int idE) {

		List<Agenda> contactos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			if (idE != 0) {
				contactos = agendaService.getAllEventContacts(idE);
			} else {
				response.put("message", "Evento invalido");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Agenda>>(contactos, HttpStatus.OK);
	}
	
	@PostMapping("/save/{idE}")	
	public ResponseEntity<?> create(@RequestBody Agenda agenda, @PathVariable int idE) {
		
		List<Agenda> contactos = null;
		
		Evento evento = null;		
		Agenda AgendaNew = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {	
			
			if (idE != 0) {
				evento = eventoService.findById(idE);
				if(evento != null) {					
					agenda.setEvento(evento);
					AgendaNew = agendaService.save(agenda);
					}else {
					response.put("message", "Evento invalido");
					response.put("status", 0);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}				
				
			} else {
				response.put("message", "Evento invalido");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//response.put("mensaje", "éxito!");
		//response.put("Producto", AgendaNew);
		return new ResponseEntity<Agenda>(AgendaNew, HttpStatus.CREATED);
		
	}
	@PostMapping("/Reunion_Evento/{idE}")	
	public ResponseEntity<?> create(@RequestBody ReunionEvento reunionEvent, @PathVariable int idE) {
		
		List<ReunionEvento> eventoR = null;
		
		Evento evento = null;       	
		ReunionEvento reunionEve = null;
		
		
		Map<String, Object> response = new HashMap<>();
            try {	
			
			if (idE != 0) {
				evento = eventoService.findById(idE);
				if(evento != null) {				
					reunionEvent.setEvento(evento);
					
					for (BTEnvioReunion bt : reunionEvent.getBitacora()) {
						bt.getAgenda().setEvento(evento);
						bt.setReunion(reunionEvent);
						bt.setFecha_hora(Calendar.getInstance());					
					}
					
					reunionEve = reunionService.save(reunionEvent);	
					}else {
					response.put("message", "Evento invalido");
					response.put("status", 0);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}				
				
			} else {
				response.put("message", "Evento invalido");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
          response.put("mensaje", "éxito!");
    	  response.put("Producto", reunionEve);
            return new ResponseEntity<ReunionEvento>(reunionEve, HttpStatus.CREATED);
	}	

}
