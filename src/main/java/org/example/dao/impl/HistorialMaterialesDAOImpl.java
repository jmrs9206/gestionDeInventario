package org.example.dao.impl;

import org.example.config.ConexionBD;
import org.example.dao.HistorialMaterialesDAO;
import org.example.exception.DatosException;
import org.example.model.HistorialMateriales;
import org.example.model.enums.EstadoMaterial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
     * Representa la clase HistorialMaterialesDAOImpl.
     */
public class HistorialMaterialesDAOImpl implements HistorialMaterialesDAO {

    @Override
    /**
    * Ejecuta la acción obtenerHistorialCompleto.
    * @return el resultado de tipo List<HistorialMateriales>
    * @throws DatosException si ocurre un error
    */
    public List<HistorialMateriales> obtenerHistorialCompleto() throws DatosException {
        List<HistorialMateriales> historial = new ArrayList<>();

        String sql = "SELECT * FROM vista_historial ORDER BY fecha DESC, id DESC";
        try (Connection conexion = ConexionBD.obtenerConexion();
                Statement consulta = conexion.createStatement();
                ResultSet resultado = consulta.executeQuery(sql)) {
            while (resultado.next()) {
                HistorialMateriales apunte = new HistorialMateriales();
                apunte.setId(resultado.getInt("id"));

                if (resultado.getDate("fecha") != null) {
                    apunte.setFecha(resultado.getDate("fecha").toLocalDate());
                }

                apunte.setEstado(EstadoMaterial.desdeTexto(resultado.getString("estado")));
                apunte.setTipo(resultado.getString("tipo"));
                apunte.setMarca(resultado.getString("marca"));
                apunte.setModelo(resultado.getString("modelo"));
                apunte.setOficina(resultado.getString("oficina"));
                apunte.setUsuario(resultado.getString("usuario"));
                apunte.setObservacion(resultado.getString("observacion"));

                historial.add(apunte);
            }
        } catch (SQLException e) {
            throw new DatosException("Fallo al leer la vista del historial: " + e.getMessage());
        }
        return historial;
    }

    @Override
    public void registrarHistorial(int idMaterial, int idOficina, int idUsuario, String estado, String observacion)
            throws DatosException {
        String sql = "INSERT INTO historial_materiales (id_material, id_oficina, id_usuario, estado, observacion) VALUES (?, ?, ?, ?::estado_material, ?)";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setInt(1, idMaterial);
            consulta.setInt(2, idOficina);
            consulta.setInt(3, idUsuario);
            consulta.setString(4, estado);
            consulta.setString(5, observacion);
            consulta.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Fallo al registrar el historial: " + e.getMessage());
        }
    }
}
