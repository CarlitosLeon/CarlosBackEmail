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
@Table(name = "Salon")
@JsonIgnoreProperties({ "detalleEvento" })
public class Salon implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salon")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_detalle_evento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private DetalleEvento detalleEvento;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "capacidad_max")
	private int capacidadMax;

	@Column(name = "ocupados")
	private int ocupados;

	@Column(name = "fondo")
	private String fondo;

	@Column(name = "zoom")
	private String zoom;

	@Column(name = "valorX")
	private String valorX;

	@Column(name = "valorY")
	private String valorY;

	@Column(name = "scalewidth")
	private String scaleWidth;

	@Column(name = "scaleheight")
	private String scaleHeight;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetalleEvento getDetalleEvento() {
		return detalleEvento;
	}

	public void setDetalleEvento(DetalleEvento detalleEvento) {
		this.detalleEvento = detalleEvento;
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

	public String getFondo() {
		return fondo;
	}

	public void setFondo(String fondo) {
		this.fondo = fondo;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public String getValorX() {
		return valorX;
	}

	public void setValorX(String valorX) {
		this.valorX = valorX;
	}

	public String getValorY() {
		return valorY;
	}

	public void setValorY(String valorY) {
		this.valorY = valorY;
	}

	public String getScaleWidth() {
		return scaleWidth;
	}

	public void setScaleWidth(String scaleWidth) {
		this.scaleWidth = scaleWidth;
	}

	public String getScaleHeight() {
		return scaleHeight;
	}

	public void setScaleHeight(String scaleHeight) {
		this.scaleHeight = scaleHeight;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
