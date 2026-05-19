package org.example.model;

import org.example.model.enums.EstadoMaterial;

import java.time.LocalDate;
/**
     * Representa la clase Material.
     */
public class Material {

    private int idMaterial;
    private String tipoMaterial;
    private String marcaMaterial;
    private String modeloMaterial;
    private String descripcion;
    private EstadoMaterial estado;
    private LocalDate fechaAlta;
    private int idOficina;
    /**
    * Constructor de Material.
    */
    public Material() {
    }
    /**
    * Constructor de Material.
    * @param idMaterial valor de idMaterial
    * @param tipoMaterial valor de tipoMaterial
    * @param marcaMaterial valor de marcaMaterial
    * @param modeloMaterial valor de modeloMaterial
    * @param descripcion valor de descripcion
    * @param estado valor de estado
    * @param fechaAlta valor de fechaAlta
    * @param idOficina valor de idOficina
    */
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
    /**
    * Ejecuta la acción getIdMaterial.
    * @return el resultado de tipo int
    */
    public int getIdMaterial() {
        return idMaterial;
    }
    /**
    * Ejecuta la acción setIdMaterial.
    * @param idMaterial el parámetro idMaterial
    */
    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }
    /**
    * Ejecuta la acción getTipoMaterial.
    * @return el resultado de tipo String
    */
    public String getTipoMaterial() {
        return tipoMaterial;
    }
    /**
    * Ejecuta la acción setTipoMaterial.
    * @param tipoMaterial el parámetro tipoMaterial
    */
    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }
    /**
    * Ejecuta la acción getMarcaMaterial.
    * @return el resultado de tipo String
    */
    public String getMarcaMaterial() {
        return marcaMaterial;
    }
    /**
    * Ejecuta la acción setMarcaMaterial.
    * @param marcaMaterial el parámetro marcaMaterial
    */
    public void setMarcaMaterial(String marcaMaterial) {
        this.marcaMaterial = marcaMaterial;
    }
    /**
    * Ejecuta la acción getModeloMaterial.
    * @return el resultado de tipo String
    */
    public String getModeloMaterial() {
        return modeloMaterial;
    }
    /**
    * Ejecuta la acción setModeloMaterial.
    * @param modeloMaterial el parámetro modeloMaterial
    */
    public void setModeloMaterial(String modeloMaterial) {
        this.modeloMaterial = modeloMaterial;
    }
    /**
    * Ejecuta la acción getDescripcion.
    * @return el resultado de tipo String
    */
    public String getDescripcion() {
        return descripcion;
    }
    /**
    * Ejecuta la acción setDescripcion.
    * @param descripcion el parámetro descripcion
    */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

}
