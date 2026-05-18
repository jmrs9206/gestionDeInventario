package org.example.model.enums;

public enum EstadoMaterial {
    ALTA,
    BAJA,
    ROTO,
    REPARACION;

    public static EstadoMaterial desdeTexto(String texto) {
        return valueOf(texto.trim().toUpperCase());
    }

}
