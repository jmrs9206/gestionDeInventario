package org.example.service;

import org.example.dao.HistorialMaterialesDAO;
import org.example.exception.DatosException;
import org.example.model.HistorialMateriales;

import java.util.List;
/**
     * Representa la clase HistorialMaterialesService.
     */
public class HistorialMaterialesService {

    private final HistorialMaterialesDAO historialMateriales;
    /**
    * Constructor de HistorialMaterialesService.
    * @param historialMateriales valor de historialMateriales
    */
    public HistorialMaterialesService(HistorialMaterialesDAO historialMateriales) {
        this.historialMateriales = historialMateriales;
    }
    /**
    * Ejecuta la acción verTodoElHistorial.
    * @return el resultado de tipo List<HistorialMateriales>
    * @throws DatosException si ocurre un error
    */
    public List<HistorialMateriales> verTodoElHistorial() throws DatosException {
        return historialMateriales.obtenerHistorialCompleto();
    }

    public void registrarHistorial(int idMaterial, int idOficina, int idUsuario, String estado, String observacion)
            throws DatosException {
        historialMateriales.registrarHistorial(idMaterial, idOficina, idUsuario, estado, observacion);
    }
}
