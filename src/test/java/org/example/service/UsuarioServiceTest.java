package org.example.service;

import org.example.dao.impl.UsuarioDAOImpl;
import org.example.exception.DatosException;
import org.example.model.Usuario;
import org.example.model.enums.EstadoGeneral;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {

    @Test
    void probarSiDejaCrearUsuarioBueno() {
        UsuarioDAOImpl baseDeDatos = new UsuarioDAOImpl();
        UsuariosService servicio = new UsuariosService(baseDeDatos);

        long numeroAleatorio = System.currentTimeMillis();
        String correoUnico = "paco" + numeroAleatorio + "@test.com";

        try {
            Usuario usuarioNuevo = servicio.registrarUsuario("Paco Tecnico", correoUnico, "123");

            assertNotNull(usuarioNuevo);
            assertEquals(correoUnico, usuarioNuevo.getCorreoElectronico());
            assertEquals(EstadoGeneral.ALTA, usuarioNuevo.getEstado());

        } catch (Exception e) {
            fail("No debería haber fallado porque los datos son buenos. Error: " + e.getMessage());
        }
    }

    @Test
    void probarQueNoDejaCrearUsuarioSinNombre() {
        UsuarioDAOImpl baseDeDatos = new UsuarioDAOImpl();
        UsuariosService servicio = new UsuariosService(baseDeDatos);

        assertThrows(DatosException.class, () -> {
            servicio.registrarUsuario("", "correo@test.com", "123");
        });
    }

    @Test
    void probarLoginBueno() {
        UsuarioDAOImpl baseDeDatos = new UsuarioDAOImpl();
        UsuariosService servicio = new UsuariosService(baseDeDatos);

        try {
            // Utilizamos el correo tal y como se ha insertado en la base de datos de
            // pruebas dml.sql
            Usuario elQueEntra = servicio.comprobarLogin("admin@email.com", "1234");

            assertNotNull(elQueEntra);
            assertEquals("admin@email.com", elQueEntra.getCorreoElectronico());
        } catch (Exception e) {
            fail("El login del admin debería funcionar. Error: " + e.getMessage());
        }
    }

    @Test
    void probarLoginConClaveMala() {
        UsuarioDAOImpl baseDeDatos = new UsuarioDAOImpl();
        UsuariosService servicio = new UsuariosService(baseDeDatos);

        assertThrows(Exception.class, () -> {
            servicio.comprobarLogin("admin@email.com", "claveMala123");
        });
    }

    @Test
    void probarListarUsuarios() {
        UsuarioDAOImpl baseDeDatos = new UsuarioDAOImpl();
        UsuariosService servicio = new UsuariosService(baseDeDatos);

        try {
            List<Usuario> todos = servicio.verTodos();

            assertNotNull(todos);
            assertFalse(todos.isEmpty());
        } catch (Exception e) {
            fail("Debería dejarme ver la lista. Error: " + e.getMessage());
        }
    }
}
