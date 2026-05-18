package org.example.dao;

import org.example.exception.DatosException;
import org.example.model.HistorialMateriales;

import java.util.List;

public interface HistorialMaterialesDAO {

    List<HistorialMateriales> obtenerHistorialCompleto() throws DatosException;

    void registrarHistorial(int idMaterial, int idOficina, int idUsuario, String estado, String observacion)
            throws DatosException;
}
