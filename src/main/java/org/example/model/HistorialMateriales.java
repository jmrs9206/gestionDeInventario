package org.example.model;

import org.example.model.enums.EstadoMaterial;

import java.time.LocalDate;

public class HistorialMateriales {

    private int id;
    private LocalDate fecha;
    private EstadoMaterial estado;
    private String tipo;
    private String marca;
    private String modelo;
    private String oficina;
    private String usuario;
    private String observacion;

    public HistorialMateriales() {
    }

    public HistorialMateriales(int id, LocalDate fecha, EstadoMaterial estado, String tipo, String marca, String modelo, String oficina, String usuario, String observacion) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.oficina = oficina;
        this.usuario = usuario;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public EstadoMaterial getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaterial estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
