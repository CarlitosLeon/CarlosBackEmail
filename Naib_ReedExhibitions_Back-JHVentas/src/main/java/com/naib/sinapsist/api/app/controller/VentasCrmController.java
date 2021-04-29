package com.naib.sinapsist.api.app.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.naib.sinapsist.api.app.models.entity.ActividadExpositor;
import com.naib.sinapsist.api.app.models.entity.ArchivoContacto;
import com.naib.sinapsist.api.app.models.entity.CarteraEvento;
import com.naib.sinapsist.api.app.models.entity.ContactoExpositor;
import com.naib.sinapsist.api.app.models.entity.EmailContacto;
import com.naib.sinapsist.api.app.models.entity.Evento;
import com.naib.sinapsist.api.app.models.entity.Expositor;
import com.naib.sinapsist.api.app.models.entity.FirmaEmail;
import com.naib.sinapsist.api.app.models.entity.NotasExpositor;
import com.naib.sinapsist.api.app.models.entity.Usuario;
import com.naib.sinapsist.api.app.models.entity.WhatsAppContacto;
import com.naib.sinapsist.api.app.models.service.IActividadExpositorService;
import com.naib.sinapsist.api.app.models.service.IArchivoContactoService;
import com.naib.sinapsist.api.app.models.service.ICarteraCrmService;
import com.naib.sinapsist.api.app.models.service.IContactoExpositorService;
import com.naib.sinapsist.api.app.models.service.IEmailContactoService;
import com.naib.sinapsist.api.app.models.service.IEventoService;
import com.naib.sinapsist.api.app.models.service.IExpositorService;
import com.naib.sinapsist.api.app.models.service.IFirmaEmailService;
import com.naib.sinapsist.api.app.models.service.INotasExpositorService;
import com.naib.sinapsist.api.app.models.service.IStandReferenciaService;
import com.naib.sinapsist.api.app.models.service.IUsuarioService;
import com.naib.sinapsist.api.app.models.service.IWhatsAppContactoService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RequestMapping("/ventasProspectos")
public class VentasCrmController {

	@Autowired
	private IExpositorService expositorService;

	@Autowired
	private ICarteraCrmService carteraService;

	@Autowired
	private INotasExpositorService notasExpositorService;

	@Autowired
	private IContactoExpositorService contactoExpositorService;

	@Autowired
	private IActividadExpositorService actividadExpositorService;

	@Autowired
	private IStandReferenciaService standReferenciaService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IEventoService eventoService;

	@Autowired
	private IWhatsAppContactoService whatsAppContactoService;

	@Autowired
	private IEmailContactoService emailContactoService;

	@Autowired
	private IArchivoContactoService archivoContactoService;

	@Autowired
	private IFirmaEmailService firmaEmailService;

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/crmAllExpositores/{idE}")
	public ResponseEntity<?> getAllExpositor(@PathVariable int idE) {

		List<CarteraEvento> expositorCartera = null;
		Map<String, Object> response = new HashMap<>();
		try {
			expositorCartera = carteraService.getAllExpositoresCartera(idE);
			if (expositorCartera == null) {
				response.put("message", "No hay expositores.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<CarteraEvento>>(expositorCartera, HttpStatus.OK);

	}

	@GetMapping("/crmMisExpositores/{id}/{idE}")
	public ResponseEntity<?> getMisExpositores(@PathVariable int id, @PathVariable int idE) {

		List<CarteraEvento> cartera = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cartera = carteraService.getExpositoresCartera(id, idE);
			if (cartera == null) {
				response.put("message", "No hay expositores.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<CarteraEvento>>(cartera, HttpStatus.OK);
	}

	@GetMapping("/crmConsultNotas/{idEx}")
	public ResponseEntity<?> getNotasExpositor(@PathVariable int idEx) {

		List<NotasExpositor> notas = null;
		Map<String, Object> response = new HashMap<>();

		try {
			notas = notasExpositorService.getNotasExpositor(idEx);
			if (notas == null) {
				response.put("message", "No hay notas.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<NotasExpositor>>(notas, HttpStatus.OK);

	}

	@GetMapping("/crmConsultContacto/{idEx}")
	public ResponseEntity<?> getContactoExpositor(@PathVariable int idEx) {

		List<ContactoExpositor> contacto = null;
		Map<String, Object> response = new HashMap<>();

		try {
			contacto = contactoExpositorService.getContactoExpositor(idEx);
			if (contacto == null) {
				response.put("message", "No hay contactos.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<ContactoExpositor>>(contacto, HttpStatus.OK);
	}

	@PostMapping("/crmCreateNota/{idEx}")
	public ResponseEntity<?> crmCreateNota(@RequestParam("descripcion") String descripcion, @PathVariable int idEx) {

		NotasExpositor notas = new NotasExpositor();
		Expositor exp = null;
		Map<String, Object> response = new HashMap<>();

		try {
			exp = expositorService.findById(idEx);
			notas.setExpositor(exp);
			notas.setDescripcion(descripcion);
			notasExpositorService.save(notas);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/crmConsultActividades/{idEx}/{idEv}")
	public ResponseEntity<?> getConsultActividades(@PathVariable int idEx, @PathVariable int idEv) {

		List<ActividadExpositor> actividad = null;
		Map<String, Object> response = new HashMap<>();

		try {
			actividad = actividadExpositorService.getActividadesExpositor(idEx, idEv);
			if (actividad == null) {
				response.put("message", "No hay actividades.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<ActividadExpositor>>(actividad, HttpStatus.OK);
	}

	@PostMapping("/crmCreateActividad/{idC}")
	public ResponseEntity<?> crmCreateActividad(@RequestParam("descripcion") String descripcion,
			@RequestParam("tipo") String tipo, @RequestParam("accion") String accion, @PathVariable int idC) {

		ActividadExpositor act = new ActividadExpositor();
		CarteraEvento cart = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cart = carteraService.findById(idC);
			act.setDetalleCartera(cart);
			act.setDescripcion(descripcion);
			act.setAccion(accion);
			act.setTipo(tipo);
			actividadExpositorService.save(act);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/crmCountsStand/{status}/{idEv}")
	public ResponseEntity<?> getCountStand(@PathVariable int status, @PathVariable int idEv) {

		int stand = 0;
		Map<String, Object> response = new HashMap<>();

		try {
			String s = String.valueOf(status);
			stand = standReferenciaService.countStatusCrm(s, idEv);
			if (stand == 0) {
				response.put("message", "No hay stands en este status.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(stand, HttpStatus.OK);
	}

	@PostMapping("/crmUpdateStatusCrm/{estatusAnt}/{estatus}/{idEx}/{idC}")
	public ResponseEntity<?> updtStatusCrm(@PathVariable int estatusAnt, @PathVariable int estatus,
			@PathVariable int idEx, @PathVariable int idC) {

		Map<String, Object> response = new HashMap<>();
		Expositor exp = new Expositor();
		ActividadExpositor act = new ActividadExpositor();
		CarteraEvento cart = null;
		String cadenaEstatusAnt = "";
		String cadenaEstatus = "";
		try {
			exp = expositorService.findById(idEx);
			exp.setEstatusCrm(estatus);
			exp = expositorService.save(exp);
			if (estatus == 0) {
				cadenaEstatus = "Prospecto";
			} else if (estatus == 1) {
				cadenaEstatus = "Contactado";
			} else if (estatus == 2) {
				cadenaEstatus = "Posible cliente";
			}
			if (estatusAnt == 0) {
				cadenaEstatusAnt = "Prospecto";
			} else if (estatusAnt == 1) {
				cadenaEstatusAnt = "Contactado";
			} else if (estatusAnt == 2) {
				cadenaEstatusAnt = "Posible cliente";
			}
			cart = carteraService.findById(idC);
			act.setDetalleCartera(cart);
			act.setDescripcion(cadenaEstatusAnt);
			act.setAccion(cadenaEstatus);
			act.setTipo("4");
			actividadExpositorService.save(act);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Expositor>(exp, HttpStatus.OK);
	}

	@PostMapping("/crmUpdatePrioridad/{estatusAnt}/{estatus}/{idEx}/{idC}")
	public ResponseEntity<?> updtStatusPrioridad(@PathVariable int estatusAnt, @PathVariable int estatus,
			@PathVariable int idEx, @PathVariable int idC) {

		Map<String, Object> response = new HashMap<>();
		Expositor exp = new Expositor();
		ActividadExpositor act = new ActividadExpositor();
		CarteraEvento cart = null;
		String cadenaEstatusAnt = "";
		String cadenaEstatus = "";
		try {
			exp = expositorService.findById(idEx);
			exp.setPrioridad(estatus);
			exp = expositorService.save(exp);

			if (estatus == 0) {
				cadenaEstatus = "Baja";
			} else if (estatus == 1) {
				cadenaEstatus = "Media";
			} else if (estatus == 2) {
				cadenaEstatus = "Alta";
			}
			if (estatusAnt == 0) {
				cadenaEstatusAnt = "Baja";
			} else if (estatusAnt == 1) {
				cadenaEstatusAnt = "Media";
			} else if (estatusAnt == 2) {
				cadenaEstatusAnt = "Alta";
			}
			cart = carteraService.findById(idC);
			act.setDetalleCartera(cart);
			act.setDescripcion(cadenaEstatusAnt);
			act.setAccion(cadenaEstatus);
			act.setTipo("5");
			actividadExpositorService.save(act);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Expositor>(exp, HttpStatus.OK);
	}

	@PostMapping("/crmDeleteExpo/{idEx}")
	public ResponseEntity<?> deleteExpo(@PathVariable int idEx) {

		Map<String, Object> response = new HashMap<>();
		try {

			expositorService.deleteById(idEx);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/crmConsVendedores/{idEv}")
	public ResponseEntity<?> getVendedores(@PathVariable int idEv) {

		List<Usuario> usu = null;
		Map<String, Object> response = new HashMap<>();

		try {

			usu = usuarioService.findUserVendedor(idEv);
			if (usu == null) {
				response.put("message", "No hay vendedores.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Usuario>>(usu, HttpStatus.OK);
	}

	@GetMapping("/crmConsRelacionVendedor/{idEx}")
	public ResponseEntity<?> getRelacionVendedor(@PathVariable int idEx) {

		List<CarteraEvento> ce = null;
		Map<String, Object> response = new HashMap<>();

		try {
			ce = carteraService.getRelacionVendedor(idEx);
			if (ce == null) {
				response.put("message", "No tiene vendedor asignado.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<CarteraEvento>>(ce, HttpStatus.OK);
	}

	@PostMapping("/crmSaveCartera/{idV}/{idEx}/{idEv}/{idCa}")
	public ResponseEntity<?> saveCartera(@PathVariable int idV, @PathVariable int idEx, @PathVariable int idEv,
			@PathVariable int idCa) {

		Map<String, Object> response = new HashMap<>();
		CarteraEvento cartera = new CarteraEvento();
		Usuario usuario = new Usuario();
		Evento evento = new Evento();
		Expositor expositor = new Expositor();
		try {
			carteraService.deleteById(idCa);
			usuario = usuarioService.findById(idV);
			evento = eventoService.findById(idEv);
			expositor = expositorService.findById(idEx);
			cartera.setEvento(evento);
			cartera.setVendedor(usuario);
			cartera.setExpositor(expositor);
			cartera.setReubicacion(false);
			carteraService.save(cartera);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CarteraEvento>(cartera, HttpStatus.OK);
	}

	@PostMapping("/crmDeleteContacto/{idC}")
	public ResponseEntity<?> deleteContacto(@PathVariable int idC) {

		Map<String, Object> response = new HashMap<>();
		try {

			contactoExpositorService.deleteById(idC);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/crmUpdateContactoFoto/{idC}")
	public ResponseEntity<?> updateContactoConFoto(@PathVariable int idC, @RequestParam("foto") MultipartFile archivo,
			@RequestParam("nombre") String nombre, @RequestParam("telefono") String telefono,
			@RequestParam("email") String email) {

		Map<String, Object> response = new HashMap<>();
		ContactoExpositor contacto = new ContactoExpositor();
		ContactoExpositor newContacto = new ContactoExpositor();
		try {
			if (!archivo.isEmpty()) {
				String resultado = contactoExpositorService.subirImgContacto(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir la imagen al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					contacto = contactoExpositorService.findById(idC);
					if (!contacto.getImg().equals("nouser.png")) {
						contactoExpositorService.delteImgContacto(contacto.getImg());
					}
					contacto.setNombre(nombre);
					contacto.setImg(resultado);
					contacto.setTelefono(telefono);
					contacto.setEmail(email);
					newContacto = contactoExpositorService.save(contacto);

					response.put("contacto", newContacto);
					response.put("img", resultado);
				}
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/crmUpdateContacto/{idC}")
	public ResponseEntity<?> updateContacto(@PathVariable int idC, @RequestParam("nombre") String nombre,
			@RequestParam("telefono") String telefono, @RequestParam("email") String email) {

		Map<String, Object> response = new HashMap<>();
		ContactoExpositor contacto = new ContactoExpositor();
		ContactoExpositor newContacto = new ContactoExpositor();
		try {

			contacto = contactoExpositorService.findById(idC);
			contacto.setNombre(nombre);
			contacto.setTelefono(telefono);
			contacto.setEmail(email);
			newContacto = contactoExpositorService.save(contacto);

			response.put("contacto", newContacto);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getImgContacto/{nameImg:.+}")
	public ResponseEntity<Resource> viewImgContacto(@PathVariable String nameImg) {
		// Path rutaArchivo = Paths.get("/home/projects/imgContactoExpositor/"+nameImg);

		Path rutaArchivo = Paths.get("imgContactoExpositor").resolve(nameImg).toAbsolutePath();
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

	@GetMapping("/getImgExpositor/{nameImg:.+}")
	public ResponseEntity<Resource> viewImgExpositor(@PathVariable String nameImg) {
		// Path rutaArchivo = Paths.get("/home/projects/imgExpositor/"+nameImg);
		Path rutaArchivo = Paths.get("imgExpositor").resolve(nameImg).toAbsolutePath();
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

	@PostMapping("/crmUpdateExpositorFoto/{idEx}")
	public ResponseEntity<?> updateExpositorFoto(@PathVariable int idEx, @RequestParam("foto") MultipartFile archivo,
			@RequestParam("razonSocial") String razonSocial, @RequestParam("nombreComercial") String nombreComercial,
			@RequestParam("direccion") String direccion, @RequestParam("rfc") String rfc,
			@RequestParam("telefono") String telefono, @RequestParam("correo") String correo,
			@RequestParam("paginaWeb") String paginaWeb, @RequestParam("pais") String pais,
			@RequestParam("estado") String estado, @RequestParam("acercaDe") String acercaDe) {

		Map<String, Object> response = new HashMap<>();
		Expositor expositor = new Expositor();
		Expositor newExpositor = new Expositor();
		try {
			if (!archivo.isEmpty()) {
				String resultado = expositorService.subirImgExpositor(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir la imagen al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					expositor = expositorService.findById(idEx);

					if (!expositor.getLogo().equals("nouser.png")) {
						expositorService.delteImgExpositor(expositor.getLogo());
					}
					expositor.setRazon_social(razonSocial);
					expositor.setNombre_comercial(nombreComercial);
					expositor.setDireccion(direccion);
					expositor.setRfc(rfc);
					expositor.setTelefono(telefono);
					expositor.setEmail(correo);
					expositor.setPagina_web(paginaWeb);
					expositor.setPais(pais);
					expositor.setEstado(estado);
					expositor.setAcercaDe(acercaDe);
					expositor.setLogo(resultado);

					newExpositor = expositorService.save(expositor);

					response.put("expositor", newExpositor);

				}
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/crmUpdateExpositor/{idEx}")
	public ResponseEntity<?> updateExpositor(@PathVariable int idEx, @RequestParam("razonSocial") String razonSocial,
			@RequestParam("nombreComercial") String nombreComercial, @RequestParam("direccion") String direccion,
			@RequestParam("rfc") String rfc, @RequestParam("telefono") String telefono,
			@RequestParam("correo") String correo, @RequestParam("paginaWeb") String paginaWeb,
			@RequestParam("pais") String pais, @RequestParam("estado") String estado,
			@RequestParam("acercaDe") String acercaDe) {

		Map<String, Object> response = new HashMap<>();
		Expositor expositor = new Expositor();
		Expositor newExpositor = new Expositor();
		try {

			expositor = expositorService.findById(idEx);
			expositor.setRazon_social(razonSocial);
			expositor.setNombre_comercial(nombreComercial);
			expositor.setDireccion(direccion);
			expositor.setRfc(rfc);
			expositor.setTelefono(telefono);
			expositor.setEmail(correo);
			expositor.setPagina_web(paginaWeb);
			expositor.setPais(pais);
			expositor.setEstado(estado);
			expositor.setAcercaDe(acercaDe);

			newExpositor = expositorService.save(expositor);

			response.put("expositor", newExpositor);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getTieneStandsAsignacion/{idEx}")
	public ResponseEntity<?> getTieneStandsAsignacion(@PathVariable int idEx) {

		Map<String, Object> response = new HashMap<>();
		Integer numAsignacion = 0;

		try {

			numAsignacion = standReferenciaService.consultaSiTieneStands(idEx);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(numAsignacion, HttpStatus.OK);
	}

	@PostMapping("/crmSaveExpositor/{idU}/{idEv}")
	public ResponseEntity<?> saveExpositor(@RequestBody Expositor expositor, @PathVariable int idU,
			@PathVariable int idEv) {

		Map<String, Object> response = new HashMap<>();
		Expositor newExpositor = new Expositor();
		Usuario usu = new Usuario();
		Evento eve = new Evento();
		CarteraEvento carte = new CarteraEvento();
		try {

			expositor.setLogo("nouser.png");
			newExpositor = expositorService.save(expositor);

			usu = usuarioService.findById(idU);
			eve = eventoService.findById(idEv);

			carte.setVendedor(usu);
			carte.setEvento(eve);
			carte.setExpositor(newExpositor);
			carte.setReubicacion(false);
			carteraService.save(carte);

			response.put("expositor", newExpositor);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Expositor>(newExpositor, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Expositor>(newExpositor, HttpStatus.OK);
	}

	@PostMapping("/crmSaveFotoExpositor/{idEx}")
	public ResponseEntity<?> saveFotoExpositor(@RequestParam("foto") MultipartFile archivo, @PathVariable int idEx) {

		Map<String, Object> response = new HashMap<>();
		Expositor newExpositor = new Expositor();

		try {

			if (!archivo.isEmpty()) {
				String resultado = expositorService.subirImgExpositor(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir la imagen al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					newExpositor = expositorService.findById(idEx);
					newExpositor.setLogo(resultado);
					newExpositor = expositorService.save(newExpositor);

					response.put("expositor", newExpositor);
				}
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Expositor>(newExpositor, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Expositor>(newExpositor, HttpStatus.OK);
	}

	@PostMapping("/crmSaveContactoExpositor/{idEx}")
	public ResponseEntity<?> saveContactoExpositor(@PathVariable int idEx,
			@RequestBody List<ContactoExpositor> contactos) {

		Map<String, Object> response = new HashMap<>();

		try {

			List<ContactoExpositor> con = new ArrayList<ContactoExpositor>();

			for (int i = 0; i < contactos.size(); i++) {

				ContactoExpositor contacto = contactos.get(i);
				Expositor exp = expositorService.findById(idEx);

				contacto.setExpositor(exp);
				contacto.setImg(contacto.getImg());

				ContactoExpositor conEx = contactoExpositorService.save(contacto);

				con.add(conEx);
			}

			response.put("contactos", con);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/crmSaveFotoContacto/{idC}")
	public ResponseEntity<?> saveFotoContacto(@RequestParam(name = "archivo") MultipartFile archivo,
			@PathVariable int idC) {

		Map<String, Object> response = new HashMap<>();
		ContactoExpositor conEx = new ContactoExpositor();

		try {

			conEx = contactoExpositorService.findById(idC);

			if (!archivo.isEmpty()) {
				String resultado = contactoExpositorService.subirImgContacto(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir la imagen al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					conEx.setImg(resultado);
					conEx = contactoExpositorService.save(conEx);

					response.put("contacto", conEx);
				}
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getRfcUnico/{rfc}")
	public ResponseEntity<?> getRfcUnico(@PathVariable String rfc) {

		Map<String, Object> response = new HashMap<>();
		Integer countRfc = 0;

		try {

			countRfc = expositorService.consRfc(rfc);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Integer>(countRfc, HttpStatus.OK);
	}

	@PostMapping("/crmSaveContactoFoto/{idEx}")
	public ResponseEntity<?> saveSaveContactoFoto(@RequestParam(name = "foto") MultipartFile foto,
			@PathVariable int idEx, @RequestParam String nombre, @RequestParam String telefono,
			@RequestParam String email) {

		Map<String, Object> response = new HashMap<>();
		ContactoExpositor conEx = new ContactoExpositor();
		Expositor ex = new Expositor();

		try {

			ex = expositorService.findById(idEx);

			if (!foto.isEmpty()) {
				String resultado = contactoExpositorService.subirImgContacto(foto);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir la imagen al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					conEx.setImg(resultado);
					conEx.setExpositor(ex);
					conEx.setNombre(nombre);
					conEx.setEmail(email);
					conEx.setTelefono(telefono);
					conEx = contactoExpositorService.save(conEx);

					response.put("contacto", conEx);
				}
			} else {

				conEx.setExpositor(ex);
				conEx.setNombre(nombre);
				conEx.setEmail(email);
				conEx.setTelefono(telefono);
				conEx = contactoExpositorService.save(conEx);

				response.put("contacto", conEx);
			}

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/crmSaveContacto/{idEx}")
	public ResponseEntity<?> saveSaveContacto(@PathVariable int idEx, @RequestParam String nombre,
			@RequestParam String telefono, @RequestParam String email) {

		Map<String, Object> response = new HashMap<>();
		ContactoExpositor conEx = new ContactoExpositor();
		Expositor ex = new Expositor();

		try {

			ex = expositorService.findById(idEx);
			conEx.setExpositor(ex);
			conEx.setNombre(nombre);
			conEx.setEmail(email);
			conEx.setTelefono(telefono);
			conEx = contactoExpositorService.save(conEx);

			response.put("contacto", conEx);

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/crm/Historial/Exp/{idCE}")
	private ResponseEntity<?> getHistorialExpositor(@PathVariable int idCE) {
		Map<String, Object> response = new HashMap<>();
		List<ActividadExpositor> ae = null;
		try {
			ae = actividadExpositorService.getAllHistorialExp(idCE);

		} catch (Exception e) {
			response.put("mensaje", e.getMessage() + ": " + e.getStackTrace());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<ActividadExpositor>>(ae, HttpStatus.OK);
	}

	@GetMapping("/Mensajes/{idwEx}")
	public ResponseEntity<?> getwhatsContacto(@PathVariable int idwEx) {

		List<WhatsAppContacto> what = null;
		Map<String, Object> response = new HashMap<>();

		try {
			what = whatsAppContactoService.getwhatsContacto(idwEx);
			if (what == null) {
				response.put("message", "No hay Mensajes.");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorC", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<WhatsAppContacto>>(what, HttpStatus.OK);

	}

	@PostMapping("/crmCreateWha/{idEx}")
	public ResponseEntity<?> crmCreateWha(@RequestBody WhatsAppContacto waContacto, @PathVariable int idEx) {

		WhatsAppContacto whatsAppContacto = null;
		ContactoExpositor cExp = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cExp = contactoExpositorService.findById(idEx);
			waContacto.setContacto(cExp);
			whatsAppContacto = whatsAppContactoService.save(waContacto);
			/*
			 * whatsAppContacto.setTipo(1); whatsAppContacto.setDescripcion("hola");
			 * whatsAppContacto.setFecha_programado(today); whatsAppContacto.setStatus("1");
			 */

		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "éxito!");
		response.put("Producto", whatsAppContacto);
		return new ResponseEntity<WhatsAppContacto>(whatsAppContacto, HttpStatus.CREATED);
	}

	@PostMapping("/crmPdf/{fCha}/{idEx}")
	public ResponseEntity<?> savePdf(@RequestParam(name = "descripcion") MultipartFile file, @PathVariable String fCha,
			@PathVariable int idEx) {
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(fCha);
			System.out.println(date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map<String, Object> response = new HashMap<>();
		WhatsAppContacto WCon = new WhatsAppContacto();
		ContactoExpositor cExp = null;

		try {
			cExp = contactoExpositorService.findById(idEx);

			if (!file.isEmpty()) {
				String resultado = whatsAppContactoService.subirPdfContacto(file);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir pdf al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					if (date1 != null) {
						WCon.setTipo(1);
						WCon.setContacto(cExp);
						WCon.setDescripcion(resultado);
						WCon.setStatus("0");
						WCon.setFecha_programado(date1);
						WCon = whatsAppContactoService.save(WCon);
					}

				}
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "éxito al subir Pdf");
		response.put("mensaje", WCon);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	/** E M A I L ´ S*/

	@GetMapping("/Firmas")
	public List<FirmaEmail> index() {
		return firmaEmailService.finAll();
	}

	@GetMapping("/emailCEpo")
	public List<ContactoExpositor> vindex() {
		return contactoExpositorService.findAll();
	}

	@PostMapping("/saveFirmas")
	public ResponseEntity<?> createFirma(@RequestParam(name = "archivo") MultipartFile archivo,
			@RequestParam String nombre, @RequestParam String descripcion) {
		Map<String, Object> response = new HashMap<>();
		FirmaEmail firmaEma = new FirmaEmail();

		try {
			if (!archivo.isEmpty()) {
				String resultado = firmaEmailService.subirArchivo(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir el archivo al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					firmaEma.setArchivo(resultado);
					firmaEma.setNombre(nombre);
					firmaEma.setDescripcion(descripcion);
					firmaEma = firmaEmailService.save(firmaEma);
					response.put("Firma", firmaEma);
				}
			}
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@PostMapping("/savefirmaSimg")
	public ResponseEntity<?> createfirmaImg(@RequestParam String descripcion,@RequestParam String nombre) {
		Map<String, Object> response = new HashMap<>();
		FirmaEmail firmaEma = new FirmaEmail();

		try {
					firmaEma.setNombre(nombre);
					firmaEma.setDescripcion(descripcion);
					firmaEma = firmaEmailService.save(firmaEma);
					response.put("Firma", firmaEma);
				
			
		} catch (DataAccessException e) {
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	@GetMapping("/Firmas/{idE}")
	public ResponseEntity<?> firmaShow(@PathVariable int idE) {

		Map<String, Object> response = new HashMap<>();
		FirmaEmail firmaEmail = null;

		try {
			firmaEmail = firmaEmailService.findById(idE);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar busqueda");
			response.put("errorC", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (firmaEmail == null) {
			response.put("Mensaje",
					"La firma con ID: ".concat(Integer.toString(idE).concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<FirmaEmail>(firmaEmail, HttpStatus.OK);
	}

	@PutMapping("/UpdateFirmas/{idE}")
	public ResponseEntity<?> updateFirma(@PathVariable int idE, @RequestParam(name = "archivo") MultipartFile archivo,
			@RequestParam String nombre, @RequestParam String descripcion) {

		FirmaEmail firmaActual = firmaEmailService.findById(idE);
		FirmaEmail firmaUpdate = null;
		FirmaEmail firmaEma = new FirmaEmail();

		Map<String, Object> response = new HashMap<>();

		if (firmaActual == null) {
			response.put("Mensaje",
					"La firma con ID: ".concat(Integer.toString(idE).concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		try {

			if (!archivo.isEmpty()) {
				String resultado = firmaEmailService.subirArchivo(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir el archivo al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					String archivoAnterior = firmaActual.getArchivo();
					System.out.println(archivoAnterior);
					if (archivoAnterior != null && archivoAnterior.length() > 0) {
						Path rutaArchivo = Paths.get("uploadsCRM/imgFirma").resolve(archivoAnterior).toAbsolutePath();
						File fAnterior = rutaArchivo.toFile();
						if (fAnterior.exists() && fAnterior.canRead()) {
							fAnterior.delete();
						}

					}

					firmaActual.setArchivo(resultado);
					firmaActual.setNombre(nombre);
					firmaActual.setDescripcion(descripcion);

					response.put("Firma", firmaEma);

					firmaUpdate = firmaEmailService.save(firmaActual);

				}
			}
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actulizar");
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "Firma actualizada");
		response.put("firma", firmaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PutMapping("/updateSined/{idE}")
	public ResponseEntity<?> updateSined(@PathVariable int idE, @RequestParam String nombre,
			@RequestParam String descripcion) {

		FirmaEmail firmaActual = firmaEmailService.findById(idE);
		FirmaEmail firmaUpdate = null;
		FirmaEmail firmaEma = new FirmaEmail();

		Map<String, Object> response = new HashMap<>();

		if (firmaActual == null) {
			response.put("Mensaje",
					"La firma con ID: ".concat(Integer.toString(idE).concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		try {
			firmaActual.setNombre(nombre);
			firmaActual.setDescripcion(descripcion);

			response.put("Firma", firmaEma);

			firmaUpdate = firmaEmailService.save(firmaActual);

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actulizar");
			response.put("errorH", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "Firma actualizada");
		response.put("firma", firmaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PostMapping("/saveEmail/{ideCon}")
	public ResponseEntity<?> createEmail(@PathVariable int ideCon, @RequestBody EmailContacto emailContacto) {

		ContactoExpositor cExpositor = null;
		EmailContacto emailNew = null;

		Map<String, Object> response = new HashMap<>();
		try {

			if (ideCon != 0) {
				cExpositor = contactoExpositorService.findById(ideCon);
				if (cExpositor != null) {
					emailContacto.setContacto(cExpositor);
					emailNew = emailContactoService.save(emailContacto);

				}

			}

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al registar Email");
			response.put("errorC", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Email creado con éxito!");
		response.put("Email", emailNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/saveArchivo/{idEc}")
	public ResponseEntity<?> createArchivo(@RequestParam(name = "archivo") MultipartFile archivo,
			@PathVariable int idEc) {

		EmailContacto emailNew = null;
		ArchivoContacto aContacto = new ArchivoContacto();

		Map<String, Object> response = new HashMap<>();
		try {

			emailNew = emailContactoService.findById(idEc);

			if (!archivo.isEmpty()) {
				String resultado = archivoContactoService.subirArchivo(archivo);
				if (resultado.contains(":")) {
					response.put("mensaje", "Error al subir el archivo al servidor");
					response.put("error", resultado);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					if (emailNew != null) {
						aContacto.setEmail(emailNew);
						aContacto.setArchivo(resultado);
						aContacto = archivoContactoService.save(aContacto);
					}
				}
			}

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al registar Email");
			response.put("errorC", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Email creado con éxito!");
		response.put("Email", aContacto);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/sendFile/{ideCon}")
	public ResponseEntity<?> sendEmailProgramed(@PathVariable int ideCon)
			throws MessagingException, UnsupportedEncodingException, MalformedURLException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		Map<String, Object> response = new HashMap<>();
		EmailContacto eContacto = null;
		List<ArchivoContacto> files = null;

		try {
			eContacto = emailContactoService.findById(ideCon);
			Thread.sleep(2000);

			helper.setFrom("linaresalberto1998@gmail.com", eContacto.getAsunto());
			helper.setTo(eContacto.getContacto().getEmail());
			helper.setSubject(eContacto.getAsunto());

			String mailContent = "<p>" + eContacto.getDescripcion() + "</p>";
			String firma = "";
			
			if (!eContacto.getFirma().equals(null)) {
				firma += eContacto.getFirma().getDescripcion();
			if(!eContacto.getFirma().getArchivo().isEmpty()) {
				firma +="<hr><br></span></p><img style='height:200px; width:auto; text-align:center;' border='0' src='cid:"+
						(String.valueOf(eContacto.getFirma().getId()).concat(eContacto.getFirma().getNombre()))
						+"'/></div>";
			}

			}

			mailContent += firma;
			helper.setText(mailContent, true);
			if(!eContacto.getFirma().getArchivo().isEmpty()) {
				File f = new
						File("/home/shack/Documentos/CrmJH/Naib_ReedExhibitions_Back-JHVentas/uploadsCRM/imgFirma/"+eContacto.getFirma().getArchivo());
				
				helper.addInline((String.valueOf(eContacto.getFirma().getId()).concat(eContacto.getFirma().getNombre())),
						f);
				
			}

			// helper.addCc("2517360000clinaresl@gmail.com","alberlinares98@gmail.com");

			files = emailContactoService.getFiles(eContacto.getId());

			if (files.size() != 0) {
				for (ArchivoContacto archivoContacto : files) {
					// Path rutaArchivo =
					// Paths.get("/home/projects/imgExpositor/"+archivoContacto.getArchivo());
					Path rutaArchivo = Paths.get("uploadsCRM/archivosEmail").resolve(archivoContacto.getArchivo())
							.toAbsolutePath();

					try {
						Resource recurso = new UrlResource(rutaArchivo.toUri());
						if (recurso.exists() && recurso.isReadable()) {
							helper.addAttachment(recurso.getFilename(), recurso);

						}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}

				}

			}

			mailSender.send(message);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar busqueda");
			response.put("errorC", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		return new ResponseEntity<EmailContacto>(eContacto, HttpStatus.OK);
	}

	@GetMapping("/ArchivosEmail/{idaC}")
	public ResponseEntity<?> getarchivoContacto(@PathVariable int idaC) {
		List<ArchivoContacto> archivoC = null;
		Map<String, Object> response = new HashMap<>();
		try {
			archivoC = archivoContactoService.getarchivoContacto(idaC);
			if (archivoC == null) {
				response.put("Mensaje", "No hay Archivos");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorC", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ArchivoContacto>>(archivoC, HttpStatus.OK);
	}

	@GetMapping("/Email/{ideCon}")
	public ResponseEntity<?> getemailContacto(@PathVariable int ideCon) {

		List<EmailContacto> email = null;
		Map<String, Object> response = new HashMap<>();

		try {
			email = emailContactoService.getemailContacto(ideCon);
			if (email == null) {
				response.put("mensaje", "No Hay email");
				response.put("status", 0);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (DataAccessException e) {
			response.put("errorC", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<EmailContacto>>(email, HttpStatus.OK);
	}

	@PostMapping("/deleteEmail/{idE}")
	public ResponseEntity<?> delete(@PathVariable int idE) {
		Map<String, Object> response = new HashMap<>();
		try {
			emailContactoService.deleteById(idE);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar email de la base de datos");
			response.put("errorC", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "Email Borrado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getImgFirma/{nombreFirma:.+}")
	public ResponseEntity<Resource> viewImgFirma(@PathVariable String nombreFirma) {
		// Path rutaArchivo = Paths.get("/home/projects/imgContactoExpositor/"+nameImg);
		Path rutaArchivo = Paths.get("uploadsCRM/imgFirma").resolve(nombreFirma).toAbsolutePath();
		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error: No se pudo cargar la imagen: " + nombreFirma);
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
}
