package org.example.model;

import org.example.model.enums.EstadoGeneral;

import java.time.LocalDate;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String correoElectronico;
    private String contrasena;
    private EstadoGeneral estado;
    private LocalDate fechaAlta;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombreUsuario, String correoElectronico, String contrasena, EstadoGeneral estado, LocalDate fechaAlta) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

}
