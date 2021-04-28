package com.naib.sinapsist.api.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Detalle_Evento")
public class DetalleEvento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_evento")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_evento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "detalleEvento" })
	private Evento evento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_centro_exposicion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "detalleCentroExposicion" })
	private CentroExposicion centroExposicion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public CentroExposicion getCentroExposicion() {
		return centroExposicion;
	}

	public void setCentroExposicion(CentroExposicion centroExposicion) {
		this.centroExposicion = centroExposicion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
