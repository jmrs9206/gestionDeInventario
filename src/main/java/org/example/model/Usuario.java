package org.example.model;

import org.example.model.enums.EstadoGeneral;

import java.time.LocalDate;
/**
     * Representa la clase Usuario.
     */
public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String correoElectronico;
    private String contrasena;
    private EstadoGeneral estado;
    private LocalDate fechaAlta;
    /**
    * Constructor de Usuario.
    */
    public Usuario() {
    }
    /**
    * Constructor de Usuario.
    * @param idUsuario valor de idUsuario
    * @param nombreUsuario valor de nombreUsuario
    * @param correoElectronico valor de correoElectronico
    * @param contrasena valor de contrasena
    * @param estado valor de estado
    * @param fechaAlta valor de fechaAlta
    */
    public Usuario(int idUsuario, String nombreUsuario, String correoElectronico, String contrasena, EstadoGeneral estado, LocalDate fechaAlta) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
    }
    /**
    * Ejecuta la acción getIdUsuario.
    * @return el resultado de tipo int
    */
    public int getIdUsuario() {
        return idUsuario;
    }
    /**
    * Ejecuta la acción setIdUsuario.
    * @param idUsuario el parámetro idUsuario
    */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    /**
    * Ejecuta la acción getNombreUsuario.
    * @return el resultado de tipo String
    */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    /**
    * Ejecuta la acción setNombreUsuario.
    * @param nombreUsuario el parámetro nombreUsuario
    */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    /**
    * Ejecuta la acción getCorreoElectronico.
    * @return el resultado de tipo String
    */
    public String getCorreoElectronico() {
        return correoElectronico;
    }
    /**
    * Ejecuta la acción setCorreoElectronico.
    * @param correoElectronico el parámetro correoElectronico
    */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
    /**
    * Ejecuta la acción getContrasena.
    * @return el resultado de tipo String
    */
    public String getContrasena() {
        return contrasena;
    }
    /**
    * Ejecuta la acción setContrasena.
    * @param contrasena el parámetro contrasena
    */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

}
