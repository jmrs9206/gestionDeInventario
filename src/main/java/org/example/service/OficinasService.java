package org.example.service;

import org.example.dao.OficinaDAO;
import org.example.exception.DatosException;
import org.example.model.Oficina;
import org.example.model.enums.EstadoGeneral;

import java.util.List;

public class OficinasService {

    private OficinaDAO oficinas;

    public OficinasService(OficinaDAO oficinas) {
        this.oficinas = oficinas;
    }

    public void registrarOficina(String nombreOficina) throws DatosException {
        if (nombreOficina == null || nombreOficina.isEmpty()) {
            throw new DatosException("El nombre de la oficina no puede estar vacío");
        }

        Oficina nuevaOficina = new Oficina();
        nuevaOficina.setNombreOficina(nombreOficina);
        nuevaOficina.setEstado(EstadoGeneral.ALTA);

        oficinas.crearNuevaOficina(nuevaOficina);
    }

    public void modificarDatosOficina(Oficina oficinaAActualizar) throws DatosException {
        if (oficinaAActualizar == null || oficinaAActualizar.getIdOficina() <= 0) {
            throw new DatosException("La oficina seleccionada no es válida");
        }
        if (oficinaAActualizar.getNombreOficina() == null || oficinaAActualizar.getNombreOficina().isEmpty()) {
            throw new DatosException("El nombre no puede estar en blanco");
        }

        oficinas.modificarOficina(oficinaAActualizar);
    }

    public void darBaja(Oficina oficinaABorrar) throws DatosException {
        if (oficinaABorrar == null || oficinaABorrar.getIdOficina() <= 0) {
            throw new DatosException("No has seleccionado ninguna oficina");
        }
        oficinas.eliminarOficina(oficinaABorrar.getIdOficina());
        oficinaABorrar.setEstado(EstadoGeneral.BAJA);
    }

    public void darAlta(Oficina oficinaARestaurar) throws DatosException {
        if (oficinaARestaurar == null || oficinaARestaurar.getIdOficina() <= 0) {
            throw new DatosException("No has seleccionado ninguna oficina");
        }
        oficinaARestaurar.setEstado(EstadoGeneral.ALTA);
        oficinas.modificarOficina(oficinaARestaurar);
    }

    public List<Oficina> verTodas() throws DatosException {
        return oficinas.obtenerTodasLasOficinas();
    }
}