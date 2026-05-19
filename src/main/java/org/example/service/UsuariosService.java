package org.example.service;

import org.example.dao.UsuarioDAO;
import org.example.exception.DatosException;
import org.example.model.Usuario;
import org.example.model.enums.EstadoGeneral;

import java.util.List;
/**
     * Representa la clase UsuariosService.
     */
public class UsuariosService {

  private final UsuarioDAO usuarios;
  /**
  * Constructor de UsuariosService.
  * @param usuarios valor de usuarios
  */
  public UsuariosService(UsuarioDAO usuarios) {
    this.usuarios = usuarios;
  }

  public Usuario registrarUsuario(String nombre, String correo, String clave)
      throws DatosException {
    if (nombre == null || nombre.isEmpty() || correo == null || correo.isEmpty() || clave == null
        || clave.isEmpty()) {
      throw new DatosException("Faltan datos para crear el usuario.");
    }
    if (buscarPorCorreo(correo) != null) {
      throw new DatosException("Ese correo ya está registrado.");
    }

    Usuario usuarioNuevo = new Usuario();
    usuarioNuevo.setNombreUsuario(nombre);
    usuarioNuevo.setCorreoElectronico(correo);
    usuarioNuevo.setContrasena(clave);
    usuarioNuevo.setEstado(EstadoGeneral.ALTA);

    usuarios.crearNuevoUsuario(usuarioNuevo);
    return usuarioNuevo;
  }
  /**
  * Ejecuta la acción modificarDatosUsuario.
  * @param usuarioAModificar el parámetro usuarioAModificar
  * @return el resultado de tipo Usuario
  * @throws DatosException si ocurre un error
  */
  public Usuario modificarDatosUsuario(Usuario usuarioAModificar) throws DatosException {
    if (usuarioAModificar == null || usuarioAModificar.getNombreUsuario() == null
        || usuarioAModificar.getNombreUsuario().isEmpty()) {
      throw new DatosException("Faltan datos para poder modificar al usuario.");
    }

    Usuario otroUsuarioConEseCorreo = buscarPorCorreo(usuarioAModificar.getCorreoElectronico());
    if (otroUsuarioConEseCorreo != null
        && otroUsuarioConEseCorreo.getIdUsuario() != usuarioAModificar.getIdUsuario()) {
      throw new DatosException("Ese correo ya está registrado.");
    }

    usuarios.modificarUsuarioExistente(usuarioAModificar);
    return usuarioAModificar;
  }
  /**
  * Ejecuta la acción darBaja.
  * @param usuario el parámetro usuario
  * @throws DatosException si ocurre un error
  */
  public void darBaja(Usuario usuario) throws DatosException {
    usuarios.eliminarUsuario(usuario.getIdUsuario());
    usuario.setEstado(EstadoGeneral.BAJA);
  }
  /**
  * Ejecuta la acción darAlta.
  * @param usuario el parámetro usuario
  * @throws DatosException si ocurre un error
  */
  public void darAlta(Usuario usuario) throws DatosException {
    usuario.setEstado(EstadoGeneral.ALTA);
    usuarios.modificarUsuarioExistente(usuario);
  }
  /**
  * Ejecuta la acción buscarPorId.
  * @param id el parámetro id
  * @return el resultado de tipo Usuario
  * @throws DatosException si ocurre un error
  */
  public Usuario buscarPorId(int id) throws DatosException {
    return usuarios.buscarUsuarioPorSuId(id);
  }
  /**
  * Ejecuta la acción buscarPorCorreo.
  * @param correo el parámetro correo
  * @return el resultado de tipo Usuario
  * @throws DatosException si ocurre un error
  */
  public Usuario buscarPorCorreo(String correo) throws DatosException {
    if (correo == null) {
      return null;
    }
    return usuarios.buscarUsuarioPorSuCorreo(correo);
  }
  /**
  * Ejecuta la acción verTodos.
  * @return el resultado de tipo List<Usuario>
  * @throws DatosException si ocurre un error
  */
  public List<Usuario> verTodos() throws DatosException {
    return usuarios.obtenerTodosLosUsuarios();
  }
  /**
  * Ejecuta la acción comprobarLogin.
  * @param correo el parámetro correo
  * @param clave el parámetro clave
  * @return el resultado de tipo Usuario
  * @throws DatosException si ocurre un error
  */
  public Usuario comprobarLogin(String correo, String clave) throws DatosException {
    if (correo == null || correo.isEmpty() || clave == null || clave.isEmpty()) {
      throw new DatosException("El correo o la contraseña no pueden estar vacíos");
    }
    Usuario usuarioEncontrado = buscarPorCorreo(correo);
    if (usuarioEncontrado == null) {
      throw new DatosException("El correo o la contraseña son incorrectos");
    }
    if (!usuarioEncontrado.getContrasena().equals(clave)) {
      throw new DatosException("El correo o la contraseña son incorrectos");
    }
    if (usuarioEncontrado.getEstado() != EstadoGeneral.ALTA) {
      throw new DatosException("Ese usuario está dado de baja, no puede entrar");
    }
    return usuarioEncontrado;
  }
}