package com.naib.sinapsist.api.app.controller;

import com.naib.sinapsist.api.app.models.entity.DetalleFM;
import com.naib.sinapsist.api.app.models.entity.Zona;
import com.naib.sinapsist.api.app.models.service.IAsignacionFM;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Synapsist-Serv
 */
@RestController
@RequestMapping("/api/adminfm")
public class AdminFMController {

	@Autowired
	private IAsignacionFM asignacionService;

	@Secured("ROLE_ADMINFM")
	@GetMapping("/lista_fm/{id}")
	public ResponseEntity<?> listaFloorManager(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("listFM", asignacionService.getFloorManager(id));
			response.put("listDetalleFM", asignacionService.findListDetalleFMByEventoId(id));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("listDetalleFM", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured("ROLE_ADMINFM")
	@GetMapping("/zonas_evento/{id}")
	public List<Zona> listaZonaByEvento(@PathVariable int id) {
		return asignacionService.getListZonaByEvento(id);
	}

	@Secured("ROLE_ADMINFM")
	@PostMapping("/asignacionZonaFM")
	public ResponseEntity<?> createAsignacionZona(@RequestBody List<DetalleFM> asignacionFM) {

		Map<String, Object> response = new HashMap<>();
		try {
			List<DetalleFM> nuevaLista = new ArrayList<DetalleFM>();
			for (int i = 0; i < asignacionFM.size(); i++) {
				DetalleFM det = asignacionFM.get(i);
				DetalleFM nuevo = asignacionService.saveDetalleFM(det);
				nuevaLista.add(nuevo);
			}
			response.put("asignacionFM", nuevaLista);
		} catch (Exception e) {
			response.put("errorD", e.getMessage() + ": " + e.getCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@Secured("ROLE_ADMINFM")
	@DeleteMapping("/deleteHorario/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable int id) {
		Map<String, Object> response = new HashMap();
		try {
			System.out.println(id);
			asignacionService.DeleteHorario(id);
			response.put("mensaje", "Horario Eliminado");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().toString());
			return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}