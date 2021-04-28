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
@Table(name = "Componente")
public class Componente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_componente")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_salon")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Salon salon;

	@Column(name = "stroke")
	private String stroke;

	@Column(name = "ptop")
	private String ptop;

	@Column(name = "pleft")
	private String pleft;

	@Column(name = "width")
	private String width;

	@Column(name = "height")
	private String height;

	@Column(name = "fill")
	private String fill;

	@Column(name = "numero_stand")
	private String numeroStand;

	@Column(name = "agrupacion")
	private String agrupacion;

	@Column(name = "count_agrupacion")
	private int count_agrupacion;

	@Column(name = "status")
	private int status;

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

	public String getStroke() {
		return stroke;
	}

	public void setStroke(String stroke) {
		this.stroke = stroke;
	}

	public String getPtop() {
		return ptop;
	}

	public void setPtop(String ptop) {
		this.ptop = ptop;
	}

	public String getPleft() {
		return pleft;
	}

	public void setPleft(String pleft) {
		this.pleft = pleft;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getFill() {
		return fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}

	public String getNumeroStand() {
		return numeroStand;
	}

	public void setNumeroStand(String numeroStand) {
		this.numeroStand = numeroStand;
	}

	public String getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(String agrupacion) {
		this.agrupacion = agrupacion;
	}

	public int getCount_agrupacion() {
		return count_agrupacion;
	}

	public void setCount_agrupacion(int count_agrupacion) {
		this.count_agrupacion = count_agrupacion;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}