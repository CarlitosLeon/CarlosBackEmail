package com.naib.sinapsist.api.app.controller;

import com.naib.sinapsist.api.app.models.entity.DetalleContratoServicio;
import com.naib.sinapsist.api.app.models.entity.DetalleFM;
import com.naib.sinapsist.api.app.models.entity.DetalleUsuario;
import com.naib.sinapsist.api.app.models.entity.EvidenciaPorcentajeArmado;
import com.naib.sinapsist.api.app.models.entity.EvidenciaServicio;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naib.sinapsist.api.app.models.entity.Expositor;
import com.naib.sinapsist.api.app.models.entity.IncidenciaReferenciada;
import com.naib.sinapsist.api.app.models.entity.RenderDro;
import com.naib.sinapsist.api.app.models.entity.Stand;
import com.naib.sinapsist.api.app.models.service.IEvidenciaPorcentajeArmado;
import com.naib.sinapsist.api.app.models.service.IEvidenciaServicioService;
import com.naib.sinapsist.api.app.models.service.IExpositorService;
import com.naib.sinapsist.api.app.models.service.IIncidenciaReferenciadaService;
import com.naib.sinapsist.api.app.models.service.IServiciosService;
import com.naib.sinapsist.api.app.models.service.IStandReferenciaService;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class ExpositorRestController {

	@Autowired
	private IExpositorService expositorService;

	@Autowired
	private IStandReferenciaService standService;

	@Autowired
	private IEvidenciaPorcentajeArmado evidenciaArmadoService;

	@Autowired
	private IServiciosService serviciosService;

	@Autowired
	private IEvidenciaServicioService evidenciaServicioService;

	@Autowired
	private IIncidenciaReferenciadaService incidenciaReferenciadaService;

	@Secured("ROLE_FM")
	@GetMapping("/expositor")
	public List<Expositor> listaExpositor() {
		return expositorService.findAll();
	}

	@GetMapping("/detalleExpositor/{id}")
	public ResponseEntity<?> standRefe(@PathVariable int id) {
		Map<String, Object> response = new HashMap();
		Stand standReferncia = standService.getStandReferenciaById(id);
		List<RenderDro> dro = standService.getRenderDroByStand(id);
		List<DetalleContratoServicio> servicios = serviciosService.getDetalleContratoByIdStand(id);
		List<IncidenciaReferenciada> incidencias = incidenciaReferenciadaService.getIncidenciasByIdStand(id);
		response.put("standReferencia", standReferncia);
		response.put("renderDro", dro);
		response.put("detalleContratoServicios", servicios);
		response.put("incidencia", incidencias);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping("/listaExpositores/getListaExpositores")
	public ResponseEntity<?> listaExpositoresStand(@RequestParam("UsEv") String idUsuario) {
		Map<String, Object> response = new HashMap();
		try {
			int id = Integer.parseInt(idUsuario);
			DetalleUsuario detUser = standService.getDetalleUsuario(id);
			List<DetalleFM> FmZonasByEvento = standService.getFMZonasByEvento(detUser.getEvento().getId());
			List<Stand> listaStand = standService.getListaExpositores(detUser.getEvento().getId());
			response.put("listaExpositores", listaStand);
			response.put("usersByEvento", FmZonasByEvento);
		} catch (Exception e) {
			response.put("mensaje", "El FloorManager no esta registrado a un evento activo");
			response.put("error", e.getMessage());
			return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PutMapping("/listaExpositores/updateStatusStand")
	public ResponseEntity<?> updateStatusStand(@RequestParam("listaStand") List<String> listaStand,
			@RequestParam("listaStatus") List<String> listaStatus) {
		Map<String, Object> response = new HashMap();
		int respuesta = 0;
		try {
			for (int i = 0; i < listaStand.size(); i++) {
				if (Integer.parseInt(listaStatus.get(i)) == 1) {
					respuesta = standService.updateStandToEtiquetado(Integer.parseInt(listaStand.get(i)));
				} else if (Integer.parseInt(listaStatus.get(i)) == 2) {
					respuesta = standService.updateStandToDetalle(Integer.parseInt(listaStand.get(i)));
				}
			}
		} catch (Exception e) {
			response.put("mensaje", "Error al actualizar el estatus del servicio");
			response.put("error", e.getMessage());
			if (respuesta != 0) {
				return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		response.put("mensaje", "El estatus de servicio se ha actualizado");

		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@GetMapping("/detalleExpositor/evidencias/{id}")
	public List<EvidenciaPorcentajeArmado> evidenciasArmado(@PathVariable int id) {
		return evidenciaArmadoService.findAllEvidenciasById(id);
	}

	@PostMapping("/detalleExpositor/uploadEvidenciaArmado")
	public ResponseEntity<?> uploadEvidenciaArmado(@RequestParam("foto") MultipartFile archivo,
			@RequestParam("idStandRefe") int idStandRefe, @RequestParam("idDetalleUsuario") int idDetalleUsuario,
			@RequestParam("porcentaje") String porcentaje) {
		Map<String, Object> response = new HashMap();
		EvidenciaPorcentajeArmado registro = new EvidenciaPorcentajeArmado();
		if (!archivo.isEmpty()) {
			String resultado = evidenciaArmadoService.subirEvidencia(archivo);
			if (resultado.contains(":")) {
				response.put("mensaje", "Error al subir la imagen al servidor");
				response.put("error", resultado);
				return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				registro.setImg(resultado);
				registro.setPorcentajeArmado(porcentaje);
				registro.setIdStandReferencia(idStandRefe);
				registro.setIdDetalleUsuario(idDetalleUsuario);
				registro.setRegistro(new Date());
				evidenciaArmadoService.guardarEvidenciaArmado(registro);
				response.put("evidencia", registro);
				response.put("mensaje", resultado);
			}
		}
		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@PutMapping("/detalleExpositor/evidencias/porcentajeArmado")
	public ResponseEntity<?> updatePorcentajeArmado(@RequestParam("porcentajeArmado") int porcentajeArmado,
			@RequestParam("idStandRefe") int id) {
		Map<String, Object> response = new HashMap();
		Stand objDatos;
		objDatos = standService.getStandReferenciaById(id);
		if (objDatos == null) {
			response.put("mensaje", "Error al intentar obtener datos del stand");
			return new ResponseEntity(response, HttpStatus.NOT_FOUND);
		} else {
			try {
				objDatos.setPorcentajeArmado(String.valueOf(porcentajeArmado));
				objDatos = standService.guardarRegistroStand(objDatos);
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al actualizar el avance de armado");
				response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().toString());
				return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		response.put("mensaje", "El stand ha sido actualizado con exito");
		response.put("evidenciaArmado", objDatos);
		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@GetMapping("/detalleExpositor/showEvidenciaArmado/{nombreFoto:.+}")
	public ResponseEntity<Resource> showEvidenciaArmado(@PathVariable String nombreFoto) {
		//Path rutaArchivo = Paths.get("/home/projects/evidenciasArmado/" + nombreFoto);
		 Path rutaArchivo = Paths.get("evidenciasArmado").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		/*
		 * if (!recurso.exists() || !recurso.isReadable()) { rutaArchivo =
		 * Paths.get("src/main/resources/static/images").resolve("user.png").
		 * toAbsolutePath(); try { recurso = new UrlResource(rutaArchivo.toUri()); }
		 * catch (MalformedURLException e) { e.printStackTrace(); } throw new
		 * RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto); }
		 */
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity(recurso, cabecera, HttpStatus.OK);
	}

	@GetMapping("/detalleExpositor/evidenciaServicios/{id}")
	public List<EvidenciaServicio> evidenciasServicio(@PathVariable int id) {
		return evidenciaServicioService.getEvidenciasServicio(id);
	}

	@PostMapping("/detalleExpositor/uploadEvidenciaServicio")
	public ResponseEntity<?> uploadEvidenciaServicio(@RequestParam("foto") MultipartFile archivo,
			@RequestParam("idDetalleContratoServicio") int idDetalleContratoServicio,
			@RequestParam("idDetalleUsuario") int idDetalleUsuario,
			@RequestParam("estatusServicio") String estatusServicio) {
		Map<String, Object> response = new HashMap();
		EvidenciaServicio registro = new EvidenciaServicio();
		if (!archivo.isEmpty()) {
			String resultado = serviciosService.subirEvidencia(archivo);
			if (resultado.contains(":")) {
				response.put("mensaje", "Error al subir la imagen al servidor");
				response.put("error", resultado);
				return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				registro.setImg(resultado);
				registro.setEstatusServicio(estatusServicio);
				registro.setIdDetalleContratoServicio(idDetalleContratoServicio);
				registro.setIdDetalleUsuario(idDetalleUsuario);

				registro.setRegistro(new Date());
				registro = evidenciaServicioService.guardarRegistroEvidencia(registro);
				response.put("evidencia", registro);
				response.put("mensaje", resultado);
			}
		}
		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@GetMapping("/detalleExpositor/showEvidenciaServicio/{nombreFoto:.+}")
	public ResponseEntity<Resource> showEvidenciaServicio(@PathVariable String nombreFoto) {
		//Path rutaArchivo = Paths.get("/home/projects/evidenciasServicio/" + nombreFoto);
		Path rutaArchivo = Paths.get("evidenciasServicio").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity(recurso, cabecera, HttpStatus.OK);
	}

	@PutMapping("/detalleExpositor/statusServicio")
	public ResponseEntity<?> updateStatusServicio(@RequestParam("idServicios") List<String> id,
			@RequestParam("status") List<String> status) {
		Map<String, Object> response = new HashMap();
		List<DetalleContratoServicio> objDatos = new ArrayList<DetalleContratoServicio>();
		try {
			for (int i = 0; i < id.size(); i++) {
				DetalleContratoServicio dcs = serviciosService.buscarById(Integer.parseInt(id.get(i)));
				dcs.setStatus(status.get(i));
				serviciosService.guardarStatusServicio(dcs);
				objDatos.add(dcs);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el estatus del servicio");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().toString());
			return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El estatus de servicio se ha actualizado");
		response.put("evidenciaServicio", objDatos);

		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	@GetMapping("/detalleExpositor/incidenciasStand/{id}")
	public List<IncidenciaReferenciada> getIncidenciasStand(@PathVariable int id) {
		return incidenciaReferenciadaService.getIncidenciasByIdStand(id);
	}

	@PutMapping("/detalleExpositor/incidenciasStand/status")
	public ResponseEntity<?> updateEstatusIncidencia(@RequestParam("status") List<String> status,
			@RequestParam("idIncidencia") List<String> id) {
		Map<String, Object> response = new HashMap();
		int respuesta = 0;
		try {
			for (int i = 0; i < id.size(); i++) {
				if (Integer.parseInt(status.get(i)) == 0) {
					respuesta = incidenciaReferenciadaService.updateStatusNoIniciada(Integer.parseInt(id.get(i)));
				} else if (Integer.parseInt(status.get(i)) == 1) {
					respuesta = incidenciaReferenciadaService.updateStatusProceso(Integer.parseInt(id.get(i)));
				} else if (Integer.parseInt(status.get(i)) == 2) {
					respuesta = incidenciaReferenciadaService.updateStatusTerminada(Integer.parseInt(id.get(i)));
				}
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el status de incidencia");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().toString());
			return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Estatus de incidencia ha sido actualizado con exito");
		response.put("statusIncidencia", respuesta);
		return new ResponseEntity(response, HttpStatus.CREATED);
	}

}
