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
@Table(name = "Cartera_Evento")
public class CarteraEvento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cartera_evento")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario vendedor;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_evento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Evento evento;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_expositor")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Expositor expositor;

	@Column(name = "fecha_hora_act")
	private Calendar actualizacion;

	@Column(name = "reubicacion")
	private boolean reubicacion;

	@PrePersist
	public void PrePersist() {
		actualizacion = Calendar.getInstance();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuario vendedor) {
		this.vendedor = vendedor;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Expositor getExpositor() {
		return expositor;
	}

	public void setExpositor(Expositor expositor) {
		this.expositor = expositor;
	}

	public Calendar getActualizacion() {
		return actualizacion;
	}

	public void setActualizacion(Calendar actualizacion) {
		this.actualizacion = actualizacion;
	}

	public boolean isReubicacion() {
		return reubicacion;
	}

	public void setReubicacion(boolean reubicacion) {
		this.reubicacion = reubicacion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
