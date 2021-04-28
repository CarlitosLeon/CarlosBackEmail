package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Expositor")
public class Expositor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_expositor")
	private Integer id;
	
	@OneToMany(mappedBy = "expositor",cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler","expositor"})
	private List<ContactoExpositor>contactoExpositor;

	@Column(name = "razon_social")
	private String razon_social;

	@Column(name = "nombre_comercial")
	private String nombre_comercial;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "email")
	private String email;

	@Column(name = "rfc")
	private String rfc;

	@Column(name = "estatus_crm")
	private int estatusCrm;

	@Column(name = "prioridad")
	private int prioridad;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "pagina_web")
	private String pagina_web;

	@Column(name = "acercade")
	private String acercaDe;

	@Column(name = "logo")
	private String logo;

	@Column(name = "pais")
	private String pais;

	@Column(name = "estado")
	private String estado;

	@Column(name = "creacion")
	private Calendar creacion;

	@PrePersist
	public void PrePersist() {
		creacion = Calendar.getInstance();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public String getNombre_comercial() {
		return nombre_comercial;
	}

	public void setNombre_comercial(String nombre_comercial) {
		this.nombre_comercial = nombre_comercial;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public int getEstatusCrm() {
		return estatusCrm;
	}

	public void setEstatusCrm(int estatusCrm) {
		this.estatusCrm = estatusCrm;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPagina_web() {
		return pagina_web;
	}

	public void setPagina_web(String pagina_web) {
		this.pagina_web = pagina_web;
	}

	public String getAcercaDe() {
		return acercaDe;
	}

	public void setAcercaDe(String acercaDe) {
		this.acercaDe = acercaDe;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Calendar getCreacion() {
		return creacion;
	}

	public void setCreacion(Calendar creacion) {
		this.creacion = creacion;
	}
	

	public List<ContactoExpositor> getContactoExpositor() {
		return contactoExpositor;
	}

	public void setContactoExpositor(List<ContactoExpositor> contactoExpositor) {
		this.contactoExpositor = contactoExpositor;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
