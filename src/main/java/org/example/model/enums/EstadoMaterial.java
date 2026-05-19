package org.example.model.enums;
/**
     * Enumeración de EstadoMaterial.
     */
public enum EstadoMaterial {
    ALTA,
    BAJA,
    ROTO,
    REPARACION;
    /**
    * Ejecuta la acción desdeTexto.
    * @param texto el parámetro texto
    * @return el resultado de tipo EstadoMaterial
    */
    public static EstadoMaterial desdeTexto(String texto) {
        return valueOf(texto.trim().toUpperCase());
    }

}
