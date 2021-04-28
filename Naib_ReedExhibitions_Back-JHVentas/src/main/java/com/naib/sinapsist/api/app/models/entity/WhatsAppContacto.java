package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Whatsapp_contacto")
public class WhatsAppContacto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_whatsapp_contacto")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contacto_expositor")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private ContactoExpositor contacto;

	@Column(name = "tipo")
	private int tipo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "fecha_programado")
	private Date fecha_programado;

	@Column(name = "status_envio")
	private String status;

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

	public ContactoExpositor getContacto() {
		return contacto;
	}

	public void setContacto(ContactoExpositor contacto) {
		this.contacto = contacto;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_programado() {
		return fecha_programado;
	}

	public void setFecha_programado(Date fecha_programado) {
		this.fecha_programado = fecha_programado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getCreacion() {
		return creacion;
	}

	public void setCreacion(Calendar creacion) {
		this.creacion = creacion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
