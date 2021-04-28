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
@Table(name = "Actividad_Expositor")
@JsonIgnoreProperties({"detalleCartera"})
public class ActividadExpositor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_actividad_expositor")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_cartera_evento", insertable = true, updatable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CarteraEvento detalleCartera;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "accion")
	private String accion;

	@Column(name = "fecha_hora")
	private Calendar fecha_hora;

	@PrePersist
	public void PrePersist() {
		fecha_hora = Calendar.getInstance();
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Calendar getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Calendar fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
