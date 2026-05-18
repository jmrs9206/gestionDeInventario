package org.example.dao.impl;

import org.example.config.ConexionBD;
import org.example.dao.OficinaDAO;
import org.example.exception.DatosException;
import org.example.model.Oficina;
import org.example.model.enums.EstadoGeneral;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OficinaDAOImpl implements OficinaDAO {

    private Oficina mapResultSetToOficina(ResultSet resultado) throws SQLException {
        Oficina oficina = new Oficina();
        oficina.setIdOficina(resultado.getInt("id_oficina"));
        oficina.setNombreOficina(resultado.getString("nombre"));
        oficina.setEstado(EstadoGeneral.desdeTexto(resultado.getString("estado")));
        if (resultado.getDate("fecha_alta") != null) {
            oficina.setFechaAlta(resultado.getDate("fecha_alta").toLocalDate());
        }
        return oficina;
    }

    @Override
    public void crearNuevaOficina(Oficina nuevaOficina) throws DatosException {
        String sql = "INSERT INTO oficinas (nombre, estado) VALUES (?, ?::estado_general)";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            consulta.setString(1, nuevaOficina.getNombreOficina());
            consulta.setString(2, nuevaOficina.getEstado().name());

            consulta.executeUpdate();

            try (ResultSet resultadoId = consulta.getGeneratedKeys()) {
                if (resultadoId.next()) {
                    nuevaOficina.setIdOficina(resultadoId.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DatosException("Fallo al guardar la oficina: " + e.getMessage());
        }
    }

    @Override
    public void modificarOficina(Oficina oficinaAModificar) throws DatosException {
        String sql = "UPDATE oficinas SET nombre = ?, estado = ?::estado_general WHERE id_oficina = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {
            int paramIndex = 1;
            consulta.setString(paramIndex++, oficinaAModificar.getNombreOficina());
            consulta.setString(paramIndex++, oficinaAModificar.getEstado().name());
            consulta.setInt(paramIndex++, oficinaAModificar.getIdOficina());
            consulta.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Fallo al actualizar la oficina: " + e.getMessage());
        }
    }

    @Override
    public void eliminarOficina(int idDeLaOficina) throws DatosException {
        String sql = "UPDATE oficinas SET estado = 'BAJA'::estado_general WHERE id_oficina = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setInt(1, idDeLaOficina);
            consulta.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Fallo al eliminar lógicamente la oficina: " + e.getMessage());
        }
    }

    @Override
    public Oficina buscarOficinaPorSuId(int idDeLaOficina) throws DatosException {
        String sql = "SELECT * FROM oficinas WHERE id_oficina = ?";
        Oficina oficinaEncontrada = null;
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setInt(1, idDeLaOficina);
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    oficinaEncontrada = mapResultSetToOficina(resultado);
                }
            }
        } catch (SQLException e) {
            throw new DatosException("Fallo al buscar oficina por ID: " + e.getMessage());

        }
      return oficinaEncontrada;
    }

    @Override
    public List<Oficina> obtenerTodasLasOficinas() throws DatosException {
        List<Oficina> listaDeOficinas = new ArrayList<>();
        String sql = "SELECT * FROM oficinas";
        try (Connection conexion = ConexionBD.obtenerConexion();
                Statement consulta = conexion.createStatement();
                ResultSet resultado = consulta.executeQuery(sql)) {
            while (resultado.next()) {
                listaDeOficinas.add(mapResultSetToOficina(resultado));
            }
        } catch (SQLException e) {
            throw new DatosException("Fallo al obtener todas las oficinas: " + e.getMessage());
        }
        return listaDeOficinas;
    }
}