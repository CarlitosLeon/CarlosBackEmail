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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.ChatIncidencia;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.Incidencia;
import com.naib.sinapsist.api.app.models.entity.IncidenciaGeneral;
import com.naib.sinapsist.api.app.models.entity.IncidenciaReferenciada;
import com.naib.sinapsist.api.app.models.entity.Salon;
import com.naib.sinapsist.api.app.models.entity.Stand;
import com.naib.sinapsist.api.app.models.entity.Usuario;
import com.naib.sinapsist.api.app.models.service.IChatIncidenciaService;
import com.naib.sinapsist.api.app.models.service.IDetalleUsuarioService;
import com.naib.sinapsist.api.app.models.service.IIncidenciaGeneralService;
import com.naib.sinapsist.api.app.models.service.IIncidenciaReferenciadaService;
import com.naib.sinapsist.api.app.models.service.IIncidenciaService;
import com.naib.sinapsist.api.app.models.service.ISalonService;
import com.naib.sinapsist.api.app.models.service.IStandReferenciaService;
import com.naib.sinapsist.api.app.models.service.IUsuarioService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequestMapping("/incidencia")
public class IncidenciaRestController {

	@Autowired
	private IIncidenciaService incidenciaService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IDetalleUsuarioService detalleUsuarioService;

	@Autowired
	private ISalonService salonService;

	@Autowired
	private IIncidenciaGeneralService incidenciaGService;

	@Autowired
	private IChatIncidenciaService chatService;

	@Autowired
	private IIncidenciaReferenciadaService incidenciaRService;

	@Autowired
	private IStandReferenciaService standRService;

	@GetMapping("/referenciada/evento/{idE}")
	public ResponseEntity<?> getIncidenciaReferenciada(@PathVariable int idE) {

		List<Incidencia> incidencias = null;
		Map<String, Object> response = new HashMap<>();
		try {
			incidencias = incidenciaService.incidenciasReferenciadas(idE);
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Incidencia>>(incidencias, HttpStatus.OK);
	}

	@GetMapping("/evento/usersFM/{idE}")
	public ResponseEntity<?> getFMEvento(@PathVariable int idE) {

		List<Usuario> fm = null;
		Map<String, Object> response = new HashMap<>();
		try {
			fm = usuarioService.findUserEvent("ROLE_FM", idE);
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (fm.size() == 0) {
			response.put("message", "Sin usuarios activos para este evento");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		return new ResponseEntity<List<Usuario>>(fm, HttpStatus.OK);
	}

	@GetMapping("/referenciada/FM/{idU}")
	public ResponseEntity<?> getIncidenciaFMEventoReferenciada(@PathVariable int idU) {
		List<Incidencia> incidencias = null;
		Integer dUser = 0;
		Map<String, Object> response = new HashMap<>();
		try {
			dUser = detalleUsuarioService.findIdDetailUser(idU);
			if (dUser == null) {
				response.put("message", "Usuario sin detalle");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			incidencias = incidenciaService.incidenciaFME(dUser, "1");
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (incidencias.size() == 0) {
			response.put("message", "Sin incidencias");
			response.put("status", 1);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		return new ResponseEntity<List<Incidencia>>(incidencias, HttpStatus.OK);
	}

	@PutMapping("/updateIncidenciaSts/{idI}/{idU}")
	public ResponseEntity<?> updateIncidencia(@RequestBody Incidencia incidencia, @PathVariable int idI,
			@PathVariable int idU) {
		Integer dUser = 0;
		DetalleUsuario detailUser = null;
		Incidencia incidenciaActual = null;
		Map<String, Object> response = new HashMap<>();
		try {
			incidenciaActual = incidenciaService.findById(idI);
			if (incidenciaActual != null) {

				dUser = detalleUsuarioService.findIdDetailUser(idU);
				if (dUser != null) {
					detailUser = detalleUsuarioService.findById(dUser);
					ChatIncidencia chat = new ChatIncidencia();
					chat.setIncidencia(incidencia);
					chat.setDetalleUsuario(detailUser);
					chat.setMensaje(incidencia.getStatus());
					chat.setTipo("3");
					chatService.guardarEvidenciaInci(chat);
					incidenciaActual.setStatus(incidencia.getStatus());
					incidenciaActual = incidenciaService.save(incidenciaActual);

				} else {
					response.put("message", "Usuario sin detalle");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}

			} else {
				response.put("message", "la incidencia no existe");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Incidencia>(incidenciaActual, HttpStatus.OK);

	}

	@GetMapping("/general/{idE}")
	public ResponseEntity<?> getIncidenciaGeneralEvento(@PathVariable int idE) {

		List<Incidencia> incidencias = null;
		Map<String, Object> response = new HashMap<>();
		try {
			incidencias = incidenciaService.incidenciaGeneral(idE);
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Incidencia>>(incidencias, HttpStatus.OK);
	}

	@GetMapping("/salon/evento/{idE}")
	public ResponseEntity<?> getSalonEvento(@PathVariable int idE) {
		List<Salon> salon = null;
		Map<String, Object> response = new HashMap<>();
		try {
			salon = salonService.findSalonEvento(idE);
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (salon.size() == 0) {
			response.put("message", "Sin salones");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}

		return new ResponseEntity<List<Salon>>(salon, HttpStatus.OK);

	}

	@GetMapping("/general/salon/{idS}")
	public ResponseEntity<?> getIncidenciaGeneralSalon(@PathVariable int idS) {

		List<Incidencia> incidencias = null;
		Map<String, Object> response = new HashMap<>();
		try {
			incidencias = incidenciaService.incidenciaGeneralSalon(idS);
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Incidencia>>(incidencias, HttpStatus.OK);
	}

	@GetMapping("/find/{ticket}")
	public ResponseEntity<?> getIncidenciaTicket(@PathVariable int ticket) {

		Incidencia incidencia = null;
		Map<String, Object> response = new HashMap<>();
		try {
			incidencia = incidenciaService.findById(ticket);
			if (incidencia == null) {
				response.put("message", "la incidencia no existe");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Incidencia>(incidencia, HttpStatus.OK);

	}

	@PostMapping("/save/{idS}/{idU}")
	public ResponseEntity<?> createIncidenciaGeneral(@RequestBody Incidencia incidencia, @PathVariable String idS,
			@PathVariable String idU) {

		Integer idDU = 0;
		Integer idSS = 0;
		Integer idUU = 0;
		DetalleUsuario detalleU = null;
		Salon salon = null;
		IncidenciaGeneral incGI = new IncidenciaGeneral();
		Incidencia incidenciaN = new Incidencia();
		Map<String, Object> response = new HashMap<>();
		try {
			idUU = Integer.parseInt(idU);
			idDU = detalleUsuarioService.findIdDetailUser(idUU);
			detalleU = detalleUsuarioService.findById(idDU);

			incidencia.setIdDetalleUsuario(detalleU);
			incidencia.setTipoIncidencia("2");

			incidenciaN = incidenciaService.save(incidencia);
			idSS = Integer.parseInt(idS);
			salon = salonService.findById(idSS);

			
			incGI.setIncidencia(incidenciaN);
			incGI.setSalon(salon);

			IncidenciaGeneral incGN = incidenciaGService.save(incGI);

			response.put("Incidencia", incidenciaN);

		} catch (DataAccessException e) {
			response.put("errorD", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PostMapping("/saveFotoInci")
	public ResponseEntity<?> subirFotoInci(@RequestParam("foto") MultipartFile archivo,
			@RequestParam("idInci") int idInci, @RequestParam int idU) {
		Map<String, Object> response = new HashMap<>();
		Integer idDU = 0;
		DetalleUsuario detalleU = null;
		Incidencia inci = null;
		try {
			if (!archivo.isEmpty()) {
				ChatIncidencia chat = new ChatIncidencia();
				String resultado = chatService.subirEvidencia(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir la imagen al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					idDU = detalleUsuarioService.findIdDetailUser(idU);
					detalleU = detalleUsuarioService.findById(idDU);
					inci = incidenciaService.findById(idInci);

					chat.setMensaje(resultado);
					chat.setTipo("2");
					chat.setDetalleUsuario(detalleU);
					chat.setIncidencia(inci);
					chatService.guardarEvidenciaInci(chat);

					response.put("chat", chat);
					response.put("img", resultado);
				}
			}
		} catch (DataAccessException e) {
			response.put("errorD", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/saveR")
	public ResponseEntity<?> createIncidenciaRef(@RequestBody Incidencia incidencia, @RequestParam int idSr,
			@RequestParam int idU) {

		Integer idDU = 0;
		DetalleUsuario detalleU = null;
		Map<String, Object> response = new HashMap<>();
		try {
			idDU = detalleUsuarioService.findIdDetailUser(idU);
			detalleU = detalleUsuarioService.findById(idDU);

			incidencia.setIdDetalleUsuario(detalleU);
			incidencia.setTipoIncidencia("1");
			incidencia.setReporta("Expositor");
			Incidencia incidenciaN = incidenciaService.save(incidencia);

			Stand stand = standRService.findById(idSr);
			// prueba;

			IncidenciaReferenciada incRef = new IncidenciaReferenciada();
			incRef.setIncidencia(incidenciaN);
			incRef.setStand(stand);
			IncidenciaReferenciada incRefN = incidenciaRService.save(incRef);
			response.put("Incidencia", incidenciaN);
			response.put("IncidenciaRef", incRefN);
		} catch (DataAccessException e) {
			response.put("errorD", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@GetMapping("/consultaIncidenciasModal/{idU}")
	public ResponseEntity<?> getResultadosHorarios(@PathVariable int idU) {
		Map<String, Object> response = new HashMap<>();
		Integer idDu = 0;
		Integer totalInci = 0;
		List<Incidencia> todasInci;
		try {
			idDu = detalleUsuarioService.findIdDetailUser(idU);
			totalInci = incidenciaService.countTotalIncidencias(idDu);
			todasInci = incidenciaService.todasIncidencias(idDu);
			response.put("Count", totalInci);
			response.put("Todas", todasInci);

		} catch (DataAccessException e) {
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
