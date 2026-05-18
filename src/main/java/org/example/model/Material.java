package org.example.model;

import org.example.model.enums.EstadoMaterial;

import java.time.LocalDate;

public class Material {

    private int idMaterial;
    private String tipoMaterial;
    private String marcaMaterial;
    private String modeloMaterial;
    private String descripcion;
    private EstadoMaterial estado;
    private LocalDate fechaAlta;
    private int idOficina;

    public Material() {
    }

    public Material(int idMaterial, String tipoMaterial, String marcaMaterial, String modeloMaterial, String descripcion, EstadoMaterial estado, LocalDate fechaAlta, int idOficina) {
        this.idMaterial = idMaterial;
        this.tipoMaterial = tipoMaterial;
        this.marcaMaterial = marcaMaterial;
        this.modeloMaterial = modeloMaterial;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
        this.idOficina = idOficina;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public String getMarcaMaterial() {
        return marcaMaterial;
    }

    public void setMarcaMaterial(String marcaMaterial) {
        this.marcaMaterial = marcaMaterial;
    }

    public String getModeloMaterial() {
        return modeloMaterial;
    }

    public void setModeloMaterial(String modeloMaterial) {
        this.modeloMaterial = modeloMaterial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoMaterial getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaterial estado) {
        this.estado = estado;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

}
