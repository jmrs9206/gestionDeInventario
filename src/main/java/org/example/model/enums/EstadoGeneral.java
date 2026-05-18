package org.example.model.enums;

public enum EstadoGeneral {
    ALTA,
    BAJA;

    public static EstadoGeneral desdeTexto(String texto) {
        return valueOf(texto.trim().toUpperCase());
    }

}
