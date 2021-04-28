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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BT_Envio_Reunion")
public class BTEnvioReunion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bt_envio_reunion")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_reunion_evento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "bitacora" })
	private ReunionEvento reunion;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_agenda")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Agenda agenda;

	@Column(name = "fecha_hora")
	private Calendar fecha_hora;

	@Column(name = "estatus")
	private int status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReunionEvento getReunion() {
		return reunion;
	}

	public void setReunion(ReunionEvento reunion) {
		this.reunion = reunion;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Calendar getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Calendar fecha_hora) {
		this.fecha_hora = fecha_hora;
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
