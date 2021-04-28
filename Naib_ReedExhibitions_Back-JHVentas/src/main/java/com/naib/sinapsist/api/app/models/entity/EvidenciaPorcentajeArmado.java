package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;

@Entity
public class EvidenciaPorcentajeArmado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evidencia_porcentaje_armado")
	private Integer id;

	@Column(name = "id_stand_referencia")
	private int idStandReferencia;

	@Column(name = "id_detalle_usuario")
	private int idDetalleUsuario;

	@Column(name = "porcentaje_armado")
	private String porcentajeArmado;

	@Column(name = "img")
	private String img;

	@Column(name = "registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_detalle_usuario", insertable = false, updatable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private DetalleUsuario detalleUsuario;

	@PrePersist
	public void prePresist() {
		registro = new Date();
	}

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

	public int getIdDetalleUsuario() {
		return idDetalleUsuario;
	}

	public void setIdDetalleUsuario(int idDetalleUsuario) {
		this.idDetalleUsuario = idDetalleUsuario;
	}

	public String getPorcentajeArmado() {
		return porcentajeArmado;
	}

	public void setPorcentajeArmado(String porcentajeArmado) {
		this.porcentajeArmado = porcentajeArmado;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public DetalleUsuario getDetalleUsuario() {
		return detalleUsuario;
	}

	public void setDetalleUsuario(DetalleUsuario detalleUsuario) {
		this.detalleUsuario = detalleUsuario;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
