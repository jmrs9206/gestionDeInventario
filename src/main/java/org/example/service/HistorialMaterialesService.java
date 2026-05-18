package org.example.service;

import org.example.dao.HistorialMaterialesDAO;
import org.example.exception.DatosException;
import org.example.model.HistorialMateriales;

import java.util.List;

public class HistorialMaterialesService {

    private final HistorialMaterialesDAO historialMateriales;

    public HistorialMaterialesService(HistorialMaterialesDAO historialMateriales) {
        this.historialMateriales = historialMateriales;
    }

    public List<HistorialMateriales> verTodoElHistorial() throws DatosException {
        return historialMateriales.obtenerHistorialCompleto();
    }

    public void registrarHistorial(int idMaterial, int idOficina, int idUsuario, String estado, String observacion)
            throws DatosException {
        historialMateriales.registrarHistorial(idMaterial, idOficina, idUsuario, estado, observacion);
    }
}
