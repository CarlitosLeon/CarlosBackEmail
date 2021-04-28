package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.Calendar;

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
@Table(name = "WhatsApp")
public class WhatsAppCartera implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_whatsapp")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cartera_evento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CarteraEvento detalleCartera;

	@Column(name = "tipo")
	private int tipo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "fecha_programado")
	private Calendar fecha_programado;

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

	public CarteraEvento getDetalleCartera() {
		return detalleCartera;
	}

	public void setDetalleCartera(CarteraEvento detalleCartera) {
		this.detalleCartera = detalleCartera;
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

	public Calendar getFecha_programado() {
		return fecha_programado;
	}

	public void setFecha_programado(Calendar fecha_programado) {
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
