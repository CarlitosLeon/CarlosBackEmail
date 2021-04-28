package com.naib.sinapsist.api.app.models.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "Incidencia")
public class Incidencia implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_incidencia")
	private Integer ticket;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_detalle_usuario", insertable = true, updatable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "evento" })
	private DetalleUsuario idDetalleUsuario;

	@Column(name = "tipo_incidencia")
	private String tipoIncidencia;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "subcategoria")
	private String subcategoria;

	@Column(name = "reporta")
	private String reporta;

	@Column(name = "nombre_reporta")
	private String nombreReporta;

	@Column(name = "telefono_reporta")
	private String telefonoReporta;

	@Column(name = "seguimiento")
	private String seguimiento;

	@Column(name = "empresa_seguimiento")
	private String empresaSeguimiento;

	@Column(name = "nombre_seguimiento")
	private String nombreSeguimiento;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estatus")
	private String status;

	@Column(name = "registro_fecha_hora")
	private Date registro;

	@PrePersist
	public void PrePersist() {
		registro = new Date();
	}

	@JsonIgnoreProperties({ "incidencia" })
	@OneToMany(mappedBy = "incidencia", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<IncidenciaReferenciada> detalleReferencia;

	@OneToMany(mappedBy = "incidencia", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "incidencia", "detalleEvento" })
	private List<IncidenciaGeneral> detalleGeneral;

	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public String getReporta() {
		return reporta;
	}

	public void setReporta(String reporta) {
		this.reporta = reporta;
	}

	public DetalleUsuario getIdDetalleUsuario() {
		return idDetalleUsuario;
	}

	public void setIdDetalleUsuario(DetalleUsuario idDetalleUsuario) {
		this.idDetalleUsuario = idDetalleUsuario;
	}

	public String getTipoIncidencia() {
		return tipoIncidencia;
	}

	public void setTipoIncidencia(String tipoIncidencia) {
		this.tipoIncidencia = tipoIncidencia;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}

	public String getNombreReporta() {
		return nombreReporta;
	}

	public void setNombreReporta(String nombreReporta) {
		this.nombreReporta = nombreReporta;
	}

	public String getTelefonoReporta() {
		return telefonoReporta;
	}

	public void setTelefonoReporta(String telefonoReporta) {
		this.telefonoReporta = telefonoReporta;
	}

	public String getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}

	public String getEmpresaSeguimiento() {
		return empresaSeguimiento;
	}

	public void setEmpresaSeguimiento(String empresaSeguimiento) {
		this.empresaSeguimiento = empresaSeguimiento;
	}

	public String getNombreSeguimiento() {
		return nombreSeguimiento;
	}

	public void setNombreSeguimiento(String nombreSeguimiento) {
		this.nombreSeguimiento = nombreSeguimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public List<IncidenciaReferenciada> getDetalleReferencia() {
		return detalleReferencia;
	}

	public void setDetalleReferencia(List<IncidenciaReferenciada> detalleReferencia) {
		this.detalleReferencia = detalleReferencia;
	}

	public List<IncidenciaGeneral> getDetalleGeneral() {
		return detalleGeneral;
	}

	public void setDetalleGeneral(List<IncidenciaGeneral> detalleGeneral) {
		this.detalleGeneral = detalleGeneral;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
