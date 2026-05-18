package org.example.service;

import org.example.dao.UsuarioDAO;
import org.example.exception.DatosException;
import org.example.model.Usuario;
import org.example.model.enums.EstadoGeneral;

import java.util.List;

public class UsuariosService {

  private final UsuarioDAO usuarios;

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

  public void darBaja(Usuario usuario) throws DatosException {
    usuarios.eliminarUsuario(usuario.getIdUsuario());
    usuario.setEstado(EstadoGeneral.BAJA);
  }

  public void darAlta(Usuario usuario) throws DatosException {
    usuario.setEstado(EstadoGeneral.ALTA);
    usuarios.modificarUsuarioExistente(usuario);
  }

  public Usuario buscarPorId(int id) throws DatosException {
    return usuarios.buscarUsuarioPorSuId(id);
  }

  public Usuario buscarPorCorreo(String correo) throws DatosException {
    if (correo == null) {
      return null;
    }
    return usuarios.buscarUsuarioPorSuCorreo(correo);
  }

  public List<Usuario> verTodos() throws DatosException {
    return usuarios.obtenerTodosLosUsuarios();
  }

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