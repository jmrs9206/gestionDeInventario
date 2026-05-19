package org.example.model.enums;
/**
     * Enumeración de EstadoGeneral.
     */
public enum EstadoGeneral {
    ALTA,
    BAJA;
    /**
    * Ejecuta la acción desdeTexto.
    * @param texto el parámetro texto
    * @return el resultado de tipo EstadoGeneral
    */
    public static EstadoGeneral desdeTexto(String texto) {
        return valueOf(texto.trim().toUpperCase());
    }

}
