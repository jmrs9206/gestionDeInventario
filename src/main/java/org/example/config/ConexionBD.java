package org.example.config;

import org.example.exception.DatosException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
     * Representa la clase ConexionBD.
     */
public class ConexionBD {

  private static final String URL = "jdbc:postgresql://localhost:5432/inventario";
  private static final String USUARIO = "postgres";
  private static final String CONTRASENA = "postgres";
  /**
  * Ejecuta la acción obtenerConexion.
  * @return el resultado de tipo Connection
  * @throws DatosException si ocurre un error
  */
  public static Connection obtenerConexion() throws DatosException {
    try {
      Connection miConexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
      return miConexion;
    } catch (SQLException error) {
      throw new DatosException("No he podido conectarme a la base de datos: " + error.getMessage());
    }
  }
}
