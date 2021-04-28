package com.naib.sinapsist.api.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.PrePersist;

@Entity
@Table(name = "Evento")
@JsonIgnoreProperties({ "detalleEvento", "usuarios" })
public class Evento implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evento")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "logo")
	private String logo;

	@Column(name = "montaje_inicio")
	private Date inicioMontaje;

	@Column(name = "montaje_fin")
	private Date finMontaje;

	@Column(name = "evento_inicio")
	private Date inicioEvento;

	@Column(name = "evento_fin")
	private Date finEvento;

	@Column(name = "desmontaje_inicio")
	private Date inicioDesmontaje;

	@Column(name = "desmontaje_fin")
	private Date finDesmontaje;

	@Column(name = "estatus")
	private String status;

	@Column(name = "creacion")
	private Calendar creacion;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "evento" })
	private List<DetalleUsuario> usuarios;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "detalleEvento" })
	private List<DetalleEvento> detalleEvento;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Date getInicioMontaje() {
		return inicioMontaje;
	}

	public void setInicioMontaje(Date inicioMontaje) {
		this.inicioMontaje = inicioMontaje;
	}

	public Date getFinMontaje() {
		return finMontaje;
	}

	public void setFinMontaje(Date finMontaje) {
		this.finMontaje = finMontaje;
	}

	public Date getInicioEvento() {
		return inicioEvento;
	}

	public void setInicioEvento(Date inicioEvento) {
		this.inicioEvento = inicioEvento;
	}

	public Date getFinEvento() {
		return finEvento;
	}

	public void setFinEvento(Date finEvento) {
		this.finEvento = finEvento;
	}

	public Date getInicioDesmontaje() {
		return inicioDesmontaje;
	}

	public void setInicioDesmontaje(Date inicioDesmontaje) {
		this.inicioDesmontaje = inicioDesmontaje;
	}

	public Date getFinDesmontaje() {
		return finDesmontaje;
	}

	public void setFinDesmontaje(Date finDesmontaje) {
		this.finDesmontaje = finDesmontaje;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getCreacion() {
		return creacion;
	}

	public void setCreacion(Calendar creacion) {
		this.creacion = creacion;
	}

	public List<DetalleUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<DetalleUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<DetalleEvento> getDetalleEvento() {
		return detalleEvento;
	}

	public void setDetalleEvento(List<DetalleEvento> detalleEvento) {
		this.detalleEvento = detalleEvento;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
