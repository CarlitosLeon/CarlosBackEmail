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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Componente_Secundario")
public class ComponenteSecundario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_componente_secundario")
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_componente")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Componente componente;

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
	private String numero_stand;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
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

	public String getNumero_stand() {
		return numero_stand;
	}

	public void setNumero_stand(String numero_stand) {
		this.numero_stand = numero_stand;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
