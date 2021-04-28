package com.naib.sinapsist.api.app.controller;

import com.naib.sinapsist.api.app.models.entity.ActividadExpositor;
import com.naib.sinapsist.api.app.models.entity.BTAsignacionStand;
import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.Componente;
import com.naib.sinapsist.api.app.models.entity.Expositor;
import com.naib.sinapsist.api.app.models.entity.Stand;
import com.naib.sinapsist.api.app.models.entity.Usuario;
import com.naib.sinapsist.api.app.models.service.IActividadExpositorService;
import com.naib.sinapsist.api.app.models.service.IBTAsignacionStandService;
import com.naib.sinapsist.api.app.models.service.IMapaVentasService;
import com.naib.sinapsist.api.app.models.service.IStandReferenciaService;
import com.naib.sinapsist.api.app.models.service.IUsuarioService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vMap")
public class VentasMapaRestController {

	@Autowired
	private IMapaVentasService mapaService;

	@Autowired
	private IStandReferenciaService standService;

	@Autowired
	private IActividadExpositorService actividadExpositorService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IBTAsignacionStandService btAsignacionStandService;

	private String colorReserva = "#17A2B8";
	private String colorFirma = "#007BFF";
	private String colorConfirmado = "#28A745";

	@GetMapping("/generalMap/{id}")
	public ResponseEntity<?> listaComponentesStandMapa(@PathVariable int id) {
		Map<String, Object> response = new HashMap<>();
		try {
			response.put("componentes", mapaService.getAllComponentesActivos(id));
			response.put("expositores", mapaService.getAllExpositoresCRM(id));
			response.put("relacionados", mapaService.getAllReferenciasStandMapa(id));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/venta/stand/{idCom}/{idU}")
	public ResponseEntity<?> ventaNuevaStand(@RequestBody CarteraEvento cartera, @PathVariable int idCom,
			@PathVariable int idU) {
		Map<String, Object> response = new HashMap<>();
		Componente com = new Componente();
		Stand st = new Stand();
		try {

			if (cartera != null) {
				com = mapaService.findById(idCom);
				if (com != null) {
					st.setId(null);
					com.setFill(colorReserva);
					st.setComponente(com);
					st.setExpositor(cartera.getExpositor());
					st.setPorcentajeArmado("0");
					st.setStatus("0");
					st.setStatusPago("0");
					st.setEstatus_asignacion("1");
					st = standService.guardarRegistroStand(st);
					mapaService.updateStatusReubicacion(cartera.isReubicacion(), cartera.getId());

					st.setExpositor(cartera.getExpositor());
					saveActividadExpositor(cartera, "6", com.getNumeroStand(), "");
					saveBTAsignacionStand(cartera.getExpositor(), st, idU, "1");
				} else {
					response.put("message", "Componente invalido");
					response.put("status", 0);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}

			} else {
				response.put("message", "Expositor invalido");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}

		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Stand>(st, HttpStatus.OK);
	}

	private Boolean saveActividadExpositor(CarteraEvento cartera, String tipo, String numS, String motivo) {
		ActividadExpositor ae = new ActividadExpositor();
		if (cartera != null) {

			ae.setDetalleCartera(cartera);
			ae.setTipo(tipo);

			switch (tipo) {
			case "6":
				ae.setDescripcion("El Expositor reservo el Stand: " + numS);
				break;
			case "7":
				ae.setDescripcion("Esperando firma del Stand: " + numS);
				break;
			case "8":
				ae.setDescripcion("El Expositor ha confirmado el Stand: " + numS);
				break;
			case "9":
				ae.setDescripcion(motivo);
				break;
			case "10":
				ae.setDescripcion(motivo);
				break;
			}
			ae.setAccion(numS);
			ae = actividadExpositorService.save(ae);
		} else {
			return false;
		}

		return true;
	}

	private Boolean saveBTAsignacionStand(Expositor exp, Stand st, int idU, String tipo) {
		Usuario u = usuarioService.findById(idU);
		if (u != null) {
			BTAsignacionStand btAS = new BTAsignacionStand();
			btAS.setExpositor(exp);
			btAS.setStand(st);
			btAS.setVendedor(u);
			btAS.setTipo(tipo);
			btAsignacionStandService.save(btAS);
		} else {
			return false;
		}
		return true;
	}

	@PutMapping("/venta/sts/{idS}/{idU}/{sts}")
	public ResponseEntity<?> actualizacionStsVenta(@RequestBody CarteraEvento cartera, @PathVariable int idS,
			@PathVariable int idU, @PathVariable int sts,
			@RequestParam(required = false, defaultValue = "Sin motivo.") String motivo) {
		Map<String, Object> response = new HashMap<>();
		Stand st = null;
		try {
			st = standService.findById(idS);
			if (st != null) {
				String color = st.getComponente().getFill();
				switch (sts) {
				case 1:
					color = colorReserva;
					break;
				case 2:
					color = colorFirma;
					break;
				case 3:
					color = colorConfirmado;
					break;
				default:
					color = "#FFF";
					break;
				}
				mapaService.updateStatusReubicacion(cartera.isReubicacion(), cartera.getId());
				st.getComponente().setFill(color);
				st.setEstatus_asignacion(String.valueOf(sts));
				st = standService.guardarRegistroStand(st);
				saveActividadExpositor(cartera, String.valueOf(sts + 5), st.getComponente().getNumeroStand(), motivo);
				saveBTAsignacionStand(st.getExpositor(), st, idU, String.valueOf(sts));

			} else {
				response.put("message", "Stand invalido");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}

		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Stand>(st, HttpStatus.OK);
	}

	@PostMapping("/venta/Historial/Exp/{idCE}")
	private ResponseEntity<?> getHistorialExpositor(@PathVariable int idCE) {
		Map<String, Object> response = new HashMap<>();
		List<ActividadExpositor> ae = new ArrayList<ActividadExpositor>();
		try {
			ae = actividadExpositorService.getHistorialExp(idCE);

		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<ActividadExpositor>>(ae, HttpStatus.OK);
	}

}