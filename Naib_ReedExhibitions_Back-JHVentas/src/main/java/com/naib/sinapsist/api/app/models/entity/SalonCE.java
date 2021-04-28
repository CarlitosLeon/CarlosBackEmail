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
@Table(name = "SalonCE")
public class SalonCE implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salon_ce")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_centro_exposicion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CentroExposicion centroExposicion;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "capacidad_max")
	private int capacidadMax;

	@Column(name = "ocupados")
	private int ocupados;

	@Column(name = "poligono")
	private String poligono;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CentroExposicion getCentroExposicion() {
		return centroExposicion;
	}

	public void setCentroExposicion(CentroExposicion centroExposicion) {
		this.centroExposicion = centroExposicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidadMax() {
		return capacidadMax;
	}

	public void setCapacidadMax(int capacidadMax) {
		this.capacidadMax = capacidadMax;
	}

	public int getOcupados() {
		return ocupados;
	}

	public void setOcupados(int ocupados) {
		this.ocupados = ocupados;
	}

	public String getPoligono() {
		return poligono;
	}

	public void setPoligono(String poligono) {
		this.poligono = poligono;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
