package org.example.dao;

import org.example.dao.impl.UsuarioDAOImpl;
import org.example.exception.DatosException;
import org.example.model.Usuario;
import org.example.model.enums.EstadoGeneral;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDAOTest {

  private UsuarioDAOImpl usuarioDAO;

  @BeforeEach
  void prepararAntesDeCadaTest() {
    usuarioDAO = new UsuarioDAOImpl();
  }

  @Test
  void probarSiGuardaBienUnUsuario() {
    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setNombreUsuario("Pepe");
    nuevoUsuario.setCorreoElectronico("pepe" + System.currentTimeMillis() + "@correo.com");
    nuevoUsuario.setContrasena("secreta");
    nuevoUsuario.setEstado(EstadoGeneral.ALTA);

    try {
      usuarioDAO.crearNuevoUsuario(nuevoUsuario);
      assertTrue(nuevoUsuario.getIdUsuario() > 0, "El usuario debería tener un ID después de guardarse");
    } catch (DatosException e) {
      fail("Ha fallado el test: " + e.getMessage());
    }
  }

  @Test
  void probarBusquedaPorCorreo() {
    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setNombreUsuario("Maria");
    String correoUnico = "maria" + System.currentTimeMillis() + "@correo.com";
    nuevoUsuario.setCorreoElectronico(correoUnico);
    nuevoUsuario.setContrasena("1234");
    nuevoUsuario.setEstado(EstadoGeneral.ALTA);

    try {
      usuarioDAO.crearNuevoUsuario(nuevoUsuario);

      Usuario encontrado = usuarioDAO.buscarUsuarioPorSuCorreo(correoUnico);
      assertNotNull(encontrado, "Debería encontrar a Maria");
      assertEquals("Maria", encontrado.getNombreUsuario(), "El nombre debería coincidir");
    } catch (DatosException e) {
      fail("Ha fallado al buscar por correo: " + e.getMessage());
    }
  }

  @Test
  void probarDesactivarUsuario() {
    Usuario nuevoUsuario = new Usuario();
    nuevoUsuario.setNombreUsuario("Luis");
    nuevoUsuario.setCorreoElectronico("luis" + System.currentTimeMillis() + "@correo.com");
    nuevoUsuario.setContrasena("abc");
    nuevoUsuario.setEstado(EstadoGeneral.ALTA);

    try {
      usuarioDAO.crearNuevoUsuario(nuevoUsuario);

      usuarioDAO.eliminarUsuario(nuevoUsuario.getIdUsuario());

      Usuario encontrado = usuarioDAO.buscarUsuarioPorSuId(nuevoUsuario.getIdUsuario());
      assertEquals(EstadoGeneral.BAJA, encontrado.getEstado(), "El estado debería ser BAJA ahora");

    } catch (DatosException e) {
      fail("Fallo desactivando usuario: " + e.getMessage());
    }
  }

  @Test
  void probarSiObtieneTodos() {
    try {
      List<Usuario> todosLosUsuarios = usuarioDAO.obtenerTodosLosUsuarios();
      assertNotNull(todosLosUsuarios, "La lista no debería ser null");
      assertTrue(todosLosUsuarios.size() >= 0, "La lista puede estar vacía o tener datos");
    } catch (DatosException e) {
      fail("Error al obtener todos: " + e.getMessage());
    }
  }
}