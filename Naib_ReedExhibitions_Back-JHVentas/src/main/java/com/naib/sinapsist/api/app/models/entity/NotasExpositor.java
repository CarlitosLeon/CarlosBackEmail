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
@Table(name = "Notas_expositor")
public class NotasExpositor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_notas_expositor")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_expositor")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Expositor expositor;

	@Column(name = "descripcion")
	private String descripcion;

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

	public Expositor getExpositor() {
		return expositor;
	}

	public void setExpositor(Expositor expositor) {
		this.expositor = expositor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
