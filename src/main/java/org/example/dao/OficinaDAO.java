package org.example.dao;

import org.example.model.Oficina;
import org.example.exception.DatosException;

import java.util.List;

public interface OficinaDAO {
    void crearNuevaOficina(Oficina nuevaOficina) throws DatosException;

    void modificarOficina(Oficina oficinaAModificar) throws DatosException;

    void eliminarOficina(int idDeLaOficina) throws DatosException;

    Oficina buscarOficinaPorSuId(int idDeLaOficina) throws DatosException;

    List<Oficina> obtenerTodasLasOficinas() throws DatosException;
}
