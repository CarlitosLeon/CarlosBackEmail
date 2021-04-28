package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Detalle_FM")
public class DetalleFM implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_fm")
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_zona")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Zona zona;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_detalle_usuario")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private DetalleUsuario detalleUsuario;

	@Column(name = "equipo")
	private String equipo;

	@Column(name = "detalle")
	private String detalle;

	@Column(name = "creacion")
	private Calendar creacion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_detalle_fm", insertable = true, updatable = true) // Automaticamente
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<HorarioFM> horarios;

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

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public DetalleUsuario getDetalleUsuario() {
		return detalleUsuario;
	}

	public void setDetalleUsuario(DetalleUsuario detalleUsuario) {
		this.detalleUsuario = detalleUsuario;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Calendar getCreacion() {
		return creacion;
	}

	public void setCreacion(Calendar creacion) {
		this.creacion = creacion;
	}

	public List<HorarioFM> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioFM> horarios) {
		this.horarios = horarios;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
