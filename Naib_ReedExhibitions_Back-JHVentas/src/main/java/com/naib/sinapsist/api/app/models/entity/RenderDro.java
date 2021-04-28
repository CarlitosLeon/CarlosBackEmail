package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Render_Dro")
public class RenderDro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_render_dro")
	private Integer id;

	@Column(name = "id_stand_referencia")
	private int idStandReferencia;

	@Column(name = "ruta")
	private String ruta;

	@Column(name = "fecha_hora")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Column(name = "tipo")
	private String tipo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getIdStandReferencia() {
		return idStandReferencia;
	}

	public void setIdStandReferencia(int idStandReferencia) {
		this.idStandReferencia = idStandReferencia;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
