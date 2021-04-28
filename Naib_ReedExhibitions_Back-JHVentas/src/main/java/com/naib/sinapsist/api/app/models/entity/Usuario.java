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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Usuario")
@JsonIgnoreProperties({ "detalleUsuario" })
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id_usuario")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_rol", insertable = false, updatable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Rol rol;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido_paterno")
	private String aPaterno;

	@JsonIgnore
	@Column(name = "apellido_materno")
	private String aMaterno;

	@JsonIgnore
	@Column(name = "contrasenia")
	private String password;

	@Column(name = "imagen")
	private String img;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "numero_seguro")
	private String numero_seguro;

	@Column(name = "estatus")
	private Boolean status;

	@JsonIgnore
	@Column(name = "creacion")
	private Date registro;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({ "usuario", "detalleUsuario" })
	private List<DetalleUsuario> detalleUsuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getaPaterno() {
		return aPaterno;
	}

	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}

	public String getaMaterno() {
		return aMaterno;
	}

	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNumero_seguro() {
		return numero_seguro;
	}

	public void setNumero_seguro(String numero_seguro) {
		this.numero_seguro = numero_seguro;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public List<DetalleUsuario> getDetalleUsuario() {
		return detalleUsuario;
	}

	public void setDetalleUsuario(List<DetalleUsuario> detalleUsuario) {
		this.detalleUsuario = detalleUsuario;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
