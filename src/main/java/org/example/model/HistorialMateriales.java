package org.example.model;

import org.example.model.enums.EstadoMaterial;

import java.time.LocalDate;
/**
     * Representa la clase HistorialMateriales.
     */
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
    /**
    * Constructor de HistorialMateriales.
    */
    public HistorialMateriales() {
    }
    /**
    * Constructor de HistorialMateriales.
    * @param id valor de id
    * @param fecha valor de fecha
    * @param estado valor de estado
    * @param tipo valor de tipo
    * @param marca valor de marca
    * @param modelo valor de modelo
    * @param oficina valor de oficina
    * @param usuario valor de usuario
    * @param observacion valor de observacion
    */
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
    /**
    * Ejecuta la acción getId.
    * @return el resultado de tipo int
    */
    public int getId() {
        return id;
    }
    /**
    * Ejecuta la acción setId.
    * @param id el parámetro id
    */
    public void setId(int id) {
        this.id = id;
    }
    /**
    * Ejecuta la acción getFecha.
    * @return el resultado de tipo LocalDate
    */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
    * Ejecuta la acción setFecha.
    * @param fecha el parámetro fecha
    */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    /**
    * Ejecuta la acción getEstado.
    * @return el resultado de tipo EstadoMaterial
    */
    public EstadoMaterial getEstado() {
        return estado;
    }
    /**
    * Ejecuta la acción setEstado.
    * @param estado el parámetro estado
    */
    public void setEstado(EstadoMaterial estado) {
        this.estado = estado;
    }
    /**
    * Ejecuta la acción getTipo.
    * @return el resultado de tipo String
    */
    public String getTipo() {
        return tipo;
    }
    /**
    * Ejecuta la acción setTipo.
    * @param tipo el parámetro tipo
    */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
    * Ejecuta la acción getMarca.
    * @return el resultado de tipo String
    */
    public String getMarca() {
        return marca;
    }
    /**
    * Ejecuta la acción setMarca.
    * @param marca el parámetro marca
    */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
    * Ejecuta la acción getModelo.
    * @return el resultado de tipo String
    */
    public String getModelo() {
        return modelo;
    }
    /**
    * Ejecuta la acción setModelo.
    * @param modelo el parámetro modelo
    */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    /**
    * Ejecuta la acción getOficina.
    * @return el resultado de tipo String
    */
    public String getOficina() {
        return oficina;
    }
    /**
    * Ejecuta la acción setOficina.
    * @param oficina el parámetro oficina
    */
    public void setOficina(String oficina) {
        this.oficina = oficina;
    }
    /**
    * Ejecuta la acción getUsuario.
    * @return el resultado de tipo String
    */
    public String getUsuario() {
        return usuario;
    }
    /**
    * Ejecuta la acción setUsuario.
    * @param usuario el parámetro usuario
    */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /**
    * Ejecuta la acción getObservacion.
    * @return el resultado de tipo String
    */
    public String getObservacion() {
        return observacion;
    }
    /**
    * Ejecuta la acción setObservacion.
    * @param observacion el parámetro observacion
    */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
