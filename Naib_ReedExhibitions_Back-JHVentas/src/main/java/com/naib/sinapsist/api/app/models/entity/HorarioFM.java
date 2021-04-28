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
@Table(name = "Horario_FM")
public class HorarioFM implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_horario_fm")
	private Integer id;

	@Column(name = "entrada")
	@Temporal(TemporalType.TIMESTAMP)
	private Date entrada;

	@Column(name = "salida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date salida;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSalida() {
		return salida;
	}

	public void setSalida(Date salida) {
		this.salida = salida;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
