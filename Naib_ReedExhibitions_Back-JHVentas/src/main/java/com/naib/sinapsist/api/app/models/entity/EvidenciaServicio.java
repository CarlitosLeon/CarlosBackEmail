/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naib.sinapsist.api.app.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Synapsist-Serv
 */
@Entity
@Table(name = "evidencia_servicio")
public class EvidenciaServicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evidencia_servicio")
    private Integer id;

    @Column(name = "id_detalle_contrato_servicio")
    private int idDetalleContratoServicio;

    @Column(name = "id_detalle_usuario")
    private int idDetalleUsuario;

    @Column(name = "img")
    private String img;

    @Column(name = "estatus_servicio")
    private String estatusServicio;

    @Column(name = "registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_usuario", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DetalleUsuario detalleUsuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_contrato_servicio", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler",})
    private DetalleContratoServicio detalleContratoServicio;

    @PrePersist
    public void prePresist() {
        registro = new Date();
    }

    public int getIdEvidenciaServicio() {
        return id;
    }

    public void setIdEvidenciaServicio(int idEvidenciaServicio) {
        this.id = idEvidenciaServicio;
    }

    public int getIdDetalleContratoServicio() {
        return idDetalleContratoServicio;
    }

    public void setIdDetalleContratoServicio(int idDetalleContratoServicio) {
        this.idDetalleContratoServicio = idDetalleContratoServicio;
    }

    public int getIdDetalleUsuario() {
        return idDetalleUsuario;
    }

    public void setIdDetalleUsuario(int idDetalleUsuario) {
        this.idDetalleUsuario = idDetalleUsuario;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEstatusServicio() {
        return estatusServicio;
    }

    public void setEstatusServicio(String estatusServicio) {
        this.estatusServicio = estatusServicio;
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

    public DetalleContratoServicio getDetalleContratoServicio() {
        return detalleContratoServicio;
    }

    public void setDetalleContratoServicio(DetalleContratoServicio detalleContratoServicio) {
        this.detalleContratoServicio = detalleContratoServicio;
    }
    
    

    private static final long serialVersionUID = 1L;

}
