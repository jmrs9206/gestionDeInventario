package org.example.dao;

import org.example.exception.DatosException;
import org.example.model.Material;

import java.util.List;
/**
     * Define la interfaz MaterialDAO.
     */
public interface MaterialDAO {
    void anadirMaterial(Material materialNuevo) throws DatosException;

    void cambiarDatosMaterial(Material materialModificado) throws DatosException;

    void eliminarMaterial(int idMaterial) throws DatosException;

    Material buscarMaterialPorId(int idMaterial) throws DatosException;

    List<Material> obtenerTodosLosMateriales() throws DatosException;
}