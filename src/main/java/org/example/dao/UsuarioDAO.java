package org.example.dao;

import org.example.exception.DatosException;
import org.example.model.Usuario;

import java.util.List;
/**
     * Define la interfaz UsuarioDAO.
     */
public interface UsuarioDAO {
    void crearNuevoUsuario(Usuario nuevoUsuario) throws DatosException;

    void modificarUsuarioExistente(Usuario usuarioModificado) throws DatosException;

    void eliminarUsuario(int idDelUsuario) throws DatosException;

    Usuario buscarUsuarioPorSuId(int idDelUsuario) throws DatosException;

    Usuario buscarUsuarioPorSuCorreo(String correoDelUsuario) throws DatosException;

    List<Usuario> obtenerTodosLosUsuarios() throws DatosException;
}