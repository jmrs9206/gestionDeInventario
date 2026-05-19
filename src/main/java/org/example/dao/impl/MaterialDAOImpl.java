package org.example.dao.impl;

import org.example.config.ConexionBD;
import org.example.dao.MaterialDAO;
import org.example.exception.DatosException;
import org.example.model.Material;
import org.example.model.enums.EstadoMaterial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
     * Representa la clase MaterialDAOImpl.
     */
public class MaterialDAOImpl implements MaterialDAO {
  /**
  * Ejecuta la acción mapResultSetToMaterial.
  * @param resultado el parámetro resultado
  * @return el resultado de tipo Material
  * @throws SQLException si ocurre un error
  */
  private Material mapResultSetToMaterial(ResultSet resultado) throws SQLException {
    Material material = new Material();
    material.setIdMaterial(resultado.getInt("id_material"));
    material.setTipoMaterial(resultado.getString("tipo"));
    material.setMarcaMaterial(resultado.getString("marca"));
    material.setModeloMaterial(resultado.getString("modelo"));
    material.setDescripcion(resultado.getString("descripcion"));
    material.setEstado(EstadoMaterial.desdeTexto(resultado.getString("estado")));
    if (resultado.getDate("fecha_alta") != null) {
      material.setFechaAlta(resultado.getDate("fecha_alta").toLocalDate());
    }
    material.setIdOficina(resultado.getInt("id_oficina"));
    return material;
  }

  @Override
  /**
  * Ejecuta la acción anadirMaterial.
  * @param materialNuevo el parámetro materialNuevo
  * @throws DatosException si ocurre un error
  */
  public void anadirMaterial(Material materialNuevo) throws DatosException {
    String sql = "INSERT INTO materiales (tipo, marca, modelo, descripcion, estado, id_oficina) VALUES (?, ?, ?, ?, ?::estado_material, ?)";
    try (Connection conexion = ConexionBD.obtenerConexion();
        PreparedStatement consulta = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      consulta.setString(1, materialNuevo.getTipoMaterial());
      consulta.setString(2, materialNuevo.getMarcaMaterial());
      consulta.setString(3, materialNuevo.getModeloMaterial());
      consulta.setString(4, materialNuevo.getDescripcion());
      consulta.setString(5, materialNuevo.getEstado().name());

      if (materialNuevo.getIdOficina() > 0) {
        consulta.setInt(6, materialNuevo.getIdOficina());
      } else {
        consulta.setNull(6, Types.INTEGER);
      }

      consulta.executeUpdate();

      try (ResultSet resultadoId = consulta.getGeneratedKeys()) {
        if (resultadoId.next()) {
          materialNuevo.setIdMaterial(resultadoId.getInt(1));
        }
      }
    } catch (SQLException e) {
      throw new DatosException("Ha fallado al añadir el material: " + e.getMessage());
    }
  }

  @Override
  /**
  * Ejecuta la acción cambiarDatosMaterial.
  * @param materialModificado el parámetro materialModificado
  * @throws DatosException si ocurre un error
  */
  public void cambiarDatosMaterial(Material materialModificado) throws DatosException {
    StringBuilder sqlBuilder = new StringBuilder(
        "UPDATE materiales SET tipo = ?, marca = ?, modelo = ?, descripcion = ?, estado = ?::estado_material");
    if (materialModificado.getIdOficina() > 0) {
      sqlBuilder.append(", id_oficina = ?");
    } else {
      sqlBuilder.append(", id_oficina = NULL");
    }
    sqlBuilder.append(" WHERE id_material = ?");

    try (Connection conexion = ConexionBD.obtenerConexion();
        PreparedStatement consulta = conexion.prepareStatement(sqlBuilder.toString())) {
      int paramIndex = 1;
      consulta.setString(paramIndex++, materialModificado.getTipoMaterial());
      consulta.setString(paramIndex++, materialModificado.getMarcaMaterial());
      consulta.setString(paramIndex++, materialModificado.getModeloMaterial());
      consulta.setString(paramIndex++, materialModificado.getDescripcion());
      consulta.setString(paramIndex++, materialModificado.getEstado().name());

      if (materialModificado.getIdOficina() > 0) {
        consulta.setInt(paramIndex++, materialModificado.getIdOficina());
      }

      consulta.setInt(paramIndex++, materialModificado.getIdMaterial());

      consulta.executeUpdate();
    } catch (SQLException e) {
      throw new DatosException("Ha fallado al cambiar los datos del material: " + e.getMessage());
    }
  }

  @Override
  /**
  * Ejecuta la acción buscarMaterialPorId.
  * @param idMaterial el parámetro idMaterial
  * @return el resultado de tipo Material
  * @throws DatosException si ocurre un error
  */
  public Material buscarMaterialPorId(int idMaterial) throws DatosException {
    String sql = "SELECT * FROM materiales WHERE id_material = ?";
    Material materialEncontrado = null;
    try (Connection conexion = ConexionBD.obtenerConexion();
        PreparedStatement consulta = conexion.prepareStatement(sql)) {
      consulta.setInt(1, idMaterial);
      try (ResultSet resultado = consulta.executeQuery()) {
        if (resultado.next()) {
          materialEncontrado = mapResultSetToMaterial(resultado);
        }
      }
    } catch (SQLException e) {
      throw new DatosException("Fallo al buscar el material: " + e.getMessage());
    }
    return materialEncontrado;
  }

  @Override
  /**
  * Ejecuta la acción eliminarMaterial.
  * @param idMaterial el parámetro idMaterial
  * @throws DatosException si ocurre un error
  */
  public void eliminarMaterial(int idMaterial) throws DatosException {
    String sql = "UPDATE materiales SET estado = 'BAJA'::estado_material WHERE id_material = ?";
    try (Connection conexion = ConexionBD.obtenerConexion();
        PreparedStatement consulta = conexion.prepareStatement(sql)) {
      consulta.setInt(1, idMaterial);
      consulta.executeUpdate();
    } catch (SQLException e) {
      throw new DatosException("Fallo al eliminar el material: " + e.getMessage());
    }
  }

  @Override
  /**
  * Ejecuta la acción obtenerTodosLosMateriales.
  * @return el resultado de tipo List<Material>
  * @throws DatosException si ocurre un error
  */
  public List<Material> obtenerTodosLosMateriales() throws DatosException {
    List<Material> listaDeMateriales = new ArrayList<>();
    String sql = "SELECT * FROM materiales";
    try (Connection conexion = ConexionBD.obtenerConexion();
        Statement consulta = conexion.createStatement();
        ResultSet resultado = consulta.executeQuery(sql)) {
      while (resultado.next()) {
        listaDeMateriales.add(mapResultSetToMaterial(resultado));
      }
    } catch (SQLException e) {
      throw new DatosException("No he podido obtener todos los materiales: " + e.getMessage());
    }
    return listaDeMateriales;
  }

}