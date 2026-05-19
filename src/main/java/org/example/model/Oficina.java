package org.example.model;

import org.example.model.enums.EstadoGeneral;

import java.time.LocalDate;
/**
     * Representa la clase Oficina.
     */
public class Oficina {

    private int idOficina;
    private String nombreOficina;
    private EstadoGeneral estado;
    private LocalDate fechaAlta;
    /**
    * Constructor de Oficina.
    */
    public Oficina() {
    }
    /**
    * Constructor de Oficina.
    * @param idOficina valor de idOficina
    * @param nombreOficina valor de nombreOficina
    * @param estado valor de estado
    * @param fechaAlta valor de fechaAlta
    */
    public Oficina(int idOficina, String nombreOficina, EstadoGeneral estado, LocalDate fechaAlta) {
        this.idOficina = idOficina;
        this.nombreOficina = nombreOficina;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
    }
    /**
    * Ejecuta la acción getIdOficina.
    * @return el resultado de tipo int
    */
    public int getIdOficina() {
        return idOficina;
    }
    /**
    * Ejecuta la acción setIdOficina.
    * @param idOficina el parámetro idOficina
    */
    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }
    /**
    * Ejecuta la acción getNombreOficina.
    * @return el resultado de tipo String
    */
    public String getNombreOficina() {
        return nombreOficina;
    }
    /**
    * Ejecuta la acción setNombreOficina.
    * @param nombreOficina el parámetro nombreOficina
    */
    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }
    /**
    * Ejecuta la acción getEstado.
    * @return el resultado de tipo EstadoGeneral
    */
    public EstadoGeneral getEstado() {
        return estado;
    }
    /**
    * Ejecuta la acción setEstado.
    * @param estado el parámetro estado
    */
    public void setEstado(EstadoGeneral estado) {
        this.estado = estado;
    }
    /**
    * Ejecuta la acción getFechaAlta.
    * @return el resultado de tipo LocalDate
    */
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
    /**
    * Ejecuta la acción setFechaAlta.
    * @param fechaAlta el parámetro fechaAlta
    */
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


    @Override
    /**
    * Ejecuta la acción toString.
    * @return el resultado de tipo String
    */
    public String toString() {
        return nombreOficina;
    }
}
