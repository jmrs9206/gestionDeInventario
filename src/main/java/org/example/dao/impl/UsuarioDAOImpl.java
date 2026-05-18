package org.example.dao.impl;

import org.example.config.ConexionBD;
import org.example.dao.UsuarioDAO;
import org.example.exception.DatosException;
import org.example.model.Usuario;
import org.example.model.enums.EstadoGeneral;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {

    private Usuario mapResultSetToUsuario(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(resultado.getInt("id_usuario"));
        usuario.setNombreUsuario(resultado.getString("nombre"));
        usuario.setCorreoElectronico(resultado.getString("email"));
        usuario.setContrasena(resultado.getString("password"));
        usuario.setEstado(EstadoGeneral.desdeTexto(resultado.getString("estado")));
        if (resultado.getDate("fecha_alta") != null) {
            usuario.setFechaAlta(resultado.getDate("fecha_alta").toLocalDate());
        }
        return usuario;
    }

    @Override
    public void crearNuevoUsuario(Usuario nuevoUsuario) throws DatosException {
        String sql = "INSERT INTO usuarios (nombre, email, password, estado) VALUES (?, ?, ?, ?::estado_general)";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            consulta.setString(1, nuevoUsuario.getNombreUsuario());
            consulta.setString(2, nuevoUsuario.getCorreoElectronico());
            consulta.setString(3, nuevoUsuario.getContrasena());
            consulta.setString(4, nuevoUsuario.getEstado().name());

            consulta.executeUpdate();

            try (ResultSet resultadoId = consulta.getGeneratedKeys()) {
                if (resultadoId.next()) {
                    nuevoUsuario.setIdUsuario(resultadoId.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DatosException("Ha fallado al guardar el usuario en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public void modificarUsuarioExistente(Usuario usuarioModificado) throws DatosException {
        StringBuilder sqlBuilder = new StringBuilder(
                "UPDATE usuarios SET nombre = ?, email = ?, estado = ?::estado_general");
        if (usuarioModificado.getContrasena() != null && !usuarioModificado.getContrasena().isEmpty()) {
            sqlBuilder.append(", password = ?");
        }
        sqlBuilder.append(" WHERE id_usuario = ?");

        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sqlBuilder.toString())) {
            int paramIndex = 1;
            consulta.setString(paramIndex++, usuarioModificado.getNombreUsuario());
            consulta.setString(paramIndex++, usuarioModificado.getCorreoElectronico());
            consulta.setString(paramIndex++, usuarioModificado.getEstado().name());

            if (usuarioModificado.getContrasena() != null && !usuarioModificado.getContrasena().isEmpty()) {
                consulta.setString(paramIndex++, usuarioModificado.getContrasena());
            }

            consulta.setInt(paramIndex++, usuarioModificado.getIdUsuario());

            consulta.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Fallo al actualizar el usuario: " + e.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(int idDelUsuario) throws DatosException {
        String sql = "UPDATE usuarios SET estado = 'BAJA'::estado_general WHERE id_usuario = ?";
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setInt(1, idDelUsuario);
            consulta.executeUpdate();
        } catch (SQLException e) {
            throw new DatosException("Fallo al eliminar al usuario: " + e.getMessage());
        }
    }

    @Override
    public Usuario buscarUsuarioPorSuId(int idDelUsuario) throws DatosException {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        Usuario usuarioEncontrado = null;
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setInt(1, idDelUsuario);
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    usuarioEncontrado = mapResultSetToUsuario(resultado);
                }
            }
        } catch (SQLException e) {
            throw new DatosException("Fallo al buscar el usuario: " + e.getMessage());
        }
        return usuarioEncontrado;
    }

    @Override
    public Usuario buscarUsuarioPorSuCorreo(String correoDelUsuario) throws DatosException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuarioEncontrado = null;
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql)) {
            consulta.setString(1, correoDelUsuario);
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    usuarioEncontrado = mapResultSetToUsuario(resultado);
                }
            }
        } catch (SQLException e) {
            throw new DatosException("Fallo buscando al usuario por el correo: " + e.getMessage());
        }
        return usuarioEncontrado;
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() throws DatosException {
        String sql = "SELECT * FROM usuarios ORDER BY id_usuario";
        List<Usuario> listaUsuarios = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement consulta = conexion.prepareStatement(sql);
                ResultSet resultado = consulta.executeQuery()) {
            while (resultado.next()) {
                listaUsuarios.add(mapResultSetToUsuario(resultado));
            }
        } catch (SQLException e) {
            throw new DatosException("Fallo obteniendo la lista de todos los usuarios: " + e.getMessage());
        }
        return listaUsuarios;
    }
}