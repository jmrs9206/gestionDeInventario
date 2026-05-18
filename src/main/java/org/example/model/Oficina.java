package org.example.model;

import org.example.model.enums.EstadoGeneral;

import java.time.LocalDate;

public class Oficina {

    private int idOficina;
    private String nombreOficina;
    private EstadoGeneral estado;
    private LocalDate fechaAlta;

    public Oficina() {
    }

    public Oficina(int idOficina, String nombreOficina, EstadoGeneral estado, LocalDate fechaAlta) {
        this.idOficina = idOficina;
        this.nombreOficina = nombreOficina;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    public String getNombreOficina() {
        return nombreOficina;
    }

    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }

    public EstadoGeneral getEstado() {
        return estado;
    }

    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    @Override
    public String toString() {
        return nombreOficina;
    }
}
