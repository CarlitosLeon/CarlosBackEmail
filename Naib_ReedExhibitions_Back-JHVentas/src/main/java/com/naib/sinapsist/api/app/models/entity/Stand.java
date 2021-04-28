package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "stand_referencia")
public class Stand implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stand_referencia")
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_componente", insertable = true, updatable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Componente componente;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_expositor", insertable = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Expositor expositor;

	@Column(name = "porcentaje_armado")
	private String porcentajeArmado;

	@Column(name = "estatus")
	private String status;

	@Column(name = "estatus_pago")
	private String statusPago;

	@Column(name = "estatus_asignacion")
	private String estatus_asignacion;

	@OneToMany(mappedBy = "stand", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "stand" })
	private List<AsignacionFMEvento> asignacionEvento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Expositor getExpositor() {
		return expositor;
	}

	public void setExpositor(Expositor expositor) {
		this.expositor = expositor;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public String getPorcentajeArmado() {
		return porcentajeArmado;
	}

	public void setPorcentajeArmado(String porcentajeArmado) {
		this.porcentajeArmado = porcentajeArmado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(String statusPago) {
		this.statusPago = statusPago;
	}

	public String getEstatus_asignacion() {
		return estatus_asignacion;
	}

	public void setEstatus_asignacion(String estatus_asignacion) {
		this.estatus_asignacion = estatus_asignacion;
	}

	public List<AsignacionFMEvento> getAsignacionEvento() {
		return asignacionEvento;
	}

	public void setAsignacionEvento(List<AsignacionFMEvento> asignacionEvento) {
		this.asignacionEvento = asignacionEvento;
	}

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

}
