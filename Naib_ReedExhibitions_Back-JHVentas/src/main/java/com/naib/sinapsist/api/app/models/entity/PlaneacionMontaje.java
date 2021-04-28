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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Planeacion_Montaje")
public class PlaneacionMontaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_planeacion_montaje")
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_salon")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Salon salon;

	@Column(name = "fecha_hora")
	private Calendar fecha_hora;

	@Column(name = "color")
	private String color;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public Calendar getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Calendar fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
