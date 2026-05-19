package org.example.service;

import org.example.dao.HistorialMaterialesDAO;
import org.example.dao.MaterialDAO;
import org.example.exception.DatosException;
import org.example.model.Material;
import org.example.model.enums.EstadoMaterial;

import java.util.List;
/**
     * Representa la clase MaterialesService.
     */
public class MaterialesService {

    private MaterialDAO materiales;
    private HistorialMaterialesDAO historialDB;
    /**
    * Constructor de MaterialesService.
    * @param db valor de db
    * @param historial valor de historial
    */
    public MaterialesService(MaterialDAO db, HistorialMaterialesDAO historial) {
        this.materiales = db;
        this.historialDB = historial;
    }

    public void registrarMaterial(String tipo, String marca, String modelo, String detalles, int idOficina, EstadoMaterial estado, int idUsuario)
            throws DatosException {
        if (tipo == null || tipo.isEmpty())
            throw new DatosException("El tipo de material es obligatorio.");
        if (marca == null || marca.isEmpty())
            throw new DatosException("La marca es obligatoria.");
        if (modelo == null || modelo.isEmpty())
            throw new DatosException("El modelo es obligatorio.");

        Material materialNuevo = new Material();
        materialNuevo.setTipoMaterial(tipo);
        materialNuevo.setMarcaMaterial(marca);
        materialNuevo.setModeloMaterial(modelo);
        materialNuevo.setDescripcion(detalles);
        materialNuevo.setIdOficina(idOficina);
        materialNuevo.setEstado(estado != null ? estado : EstadoMaterial.ALTA);

        materiales.anadirMaterial(materialNuevo);

        if (historialDB != null) {
            historialDB.registrarHistorial(
                materialNuevo.getIdMaterial(),
                idOficina,
                idUsuario,
                materialNuevo.getEstado().name(),
                "REGISTRO INICIAL"
            );
        }
    }
    /**
    * Ejecuta la acción modificarDatosMaterial.
    * @param materialAModificar el parámetro materialAModificar
    * @param idUsuario el parámetro idUsuario
    * @throws DatosException si ocurre un error
    */
    public void modificarDatosMaterial(Material materialAModificar, int idUsuario) throws DatosException {
        if (materialAModificar == null || materialAModificar.getIdMaterial() <= 0)
            throw new DatosException("El material no es válido");
        if (materialAModificar.getTipoMaterial() == null || materialAModificar.getTipoMaterial().isEmpty()) {
            throw new DatosException("El tipo no puede estar vacío");
        }

        Material anterior = materiales.buscarMaterialPorId(materialAModificar.getIdMaterial());

        materiales.cambiarDatosMaterial(materialAModificar);

        if (historialDB != null) {
            String obs = "Modificación de datos";
            if (anterior != null) {
                if (anterior.getEstado() != materialAModificar.getEstado()) {
                    obs = "Cambio de estado de " + anterior.getEstado() + " a " + materialAModificar.getEstado();
                } else if (anterior.getIdOficina() != materialAModificar.getIdOficina()) {
                    obs = "Traslado de oficina " + anterior.getIdOficina() + " a " + materialAModificar.getIdOficina();
                }
            }
            historialDB.registrarHistorial(
                materialAModificar.getIdMaterial(),
                materialAModificar.getIdOficina(),
                idUsuario,
                materialAModificar.getEstado().name(),
                obs
            );
        }
    }

    public void darDeBajaMaterial(int idMaterial, int idUsuarioQueLoDaDeBaja, int idOficinaActual)
            throws DatosException {
        if (idMaterial <= 0)
            throw new DatosException("El material no existe.");

        materiales.eliminarMaterial(idMaterial);

        if (historialDB != null) {
            historialDB.registrarHistorial(idMaterial, idOficinaActual, idUsuarioQueLoDaDeBaja, "BAJA",
                    "Baja por el usuario");
        }
    }

    public void cambiarEstado(Material material, EstadoMaterial nuevoEstado, int idUsuario)
            throws DatosException {
        if (material == null || material.getIdMaterial() <= 0)
            throw new DatosException("El material no es válido");
        
        EstadoMaterial anterior = material.getEstado();
        material.setEstado(nuevoEstado);
        materiales.cambiarDatosMaterial(material);

        if (historialDB != null) {
            historialDB.registrarHistorial(
                material.getIdMaterial(),
                material.getIdOficina(),
                idUsuario,
                nuevoEstado.name(),
                "Cambio de estado de " + anterior + " a " + nuevoEstado
            );
        }
    }
    /**
    * Ejecuta la acción verTodos.
    * @return el resultado de tipo List<Material>
    * @throws DatosException si ocurre un error
    */
    public List<Material> verTodos() throws DatosException {
        return materiales.obtenerTodosLosMateriales();
    }
}