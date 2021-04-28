package com.naib.sinapsist.api.app.models.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Asignacion_Planeacion")
public class AsignacionPlaneacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_asignacion_planeacion")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_stand_referencia")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Stand stand;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_planeacion_montaje")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private PlaneacionMontaje planeacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Stand getStand() {
		return stand;
	}

	public void setStand(Stand stand) {
		this.stand = stand;
	}

	public PlaneacionMontaje getPlaneacion() {
		return planeacion;
	}

	public void setPlaneacion(PlaneacionMontaje planeacion) {
		this.planeacion = planeacion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
