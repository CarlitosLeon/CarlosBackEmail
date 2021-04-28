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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Reunion_Evento")
public class ReunionEvento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reunion_evento")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_evento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonIgnore
	private Evento evento;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "lugar")
	private String lugar;

	@Column(name = "fecha")
	private Calendar fecha;

	@Column(name = "hora")
	private Calendar hora;

	@Column(name = "estatus")
	private int status;

	@Column(name = "creacion")
	private Calendar creacion;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_reunion_evento", insertable = true, updatable = true) // Automaticamente
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<TratadoAcuerdo> temas;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_reunion_evento", insertable = true, updatable = false) // Automaticamente
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "reunion"})
	private List<BTEnvioReunion> bitacora;

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

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Calendar getHora() {
		return hora;
	}

	public void setHora(Calendar hora) {
		this.hora = hora;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Calendar getCreacion() {
		return creacion;
	}

	public void setCreacion(Calendar creacion) {
		this.creacion = creacion;
	}

	public List<TratadoAcuerdo> getTemas() {
		return temas;
	}

	public void setTemas(List<TratadoAcuerdo> temas) {
		this.temas = temas;
	}

	public List<BTEnvioReunion> getBitacora() {
		return bitacora;
	}

	public void setBitacora(List<BTEnvioReunion> bitacora) {
		this.bitacora = bitacora;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
