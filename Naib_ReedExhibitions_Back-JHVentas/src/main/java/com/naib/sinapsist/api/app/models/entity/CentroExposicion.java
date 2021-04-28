package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "Centro_Exposicion")
public class CentroExposicion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_centro_exposicion")
	private Integer id;

	@Column(name = "nombre_recinto")
	private String nombre;

	@Column(name = "ciudad")
	private String ciudad;

	@Column(name = "av_calle")
	private String avCalle;

	@Column(name = "colonia")
	private String colonia;

	@Column(name = "municipio")
	private String municipio;

	@Column(name = "numero")
	private String numero;

	@Column(name = "cp")
	private String cp;

	@Column(name = "direccion_maps")
	private String direccion;

	@Column(name = "creacion")
	private Calendar creacion;

	@OneToMany(mappedBy = "centroExposicion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleEvento> detalleCentroExposicion;

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

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getAvCalle() {
		return avCalle;
	}

	public void setAvCalle(String avCalle) {
		this.avCalle = avCalle;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Calendar getCreacion() {
		return creacion;
	}

	public void setCreacion(Calendar creacion) {
		this.creacion = creacion;
	}

	public List<DetalleEvento> getDetalleCentroExposicion() {
		return detalleCentroExposicion;
	}

	public void setDetalleCentroExposicion(List<DetalleEvento> detalleCentroExposicion) {
		this.detalleCentroExposicion = detalleCentroExposicion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
