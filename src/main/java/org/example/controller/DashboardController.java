package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.model.Usuario;
import org.example.service.HistorialMaterialesService;
import org.example.service.MaterialesService;
import org.example.service.OficinasService;
import org.example.service.UsuariosService;

import java.io.IOException;
/**
     * Representa la clase DashboardController.
     */
public class DashboardController {

    @FXML
    private Label textoBienvenida;
    private Usuario usuarioQueHaEntrado;
    private Stage ventanaActual;

    private UsuariosService servicioDeUsuarios;
    private OficinasService servicioDeOficinas;
    private MaterialesService servicioDeMateriales;
    private HistorialMaterialesService servicioDeHistorial;

    @FXML
    private UsuarioController vistaUsuariosController;
    @FXML
    private OficinaController vistaOficinasController;
    @FXML
    private MaterialController vistaMaterialesController;
    @FXML
    private HistorialController vistaHistorialController;
    /**
    * Ejecuta la acción guardarUsuarioQueUsaElPrograma.
    * @param usuario el parámetro usuario
    */
    public void guardarUsuarioQueUsaElPrograma(Usuario usuario) {
        this.usuarioQueHaEntrado = usuario;
        if (usuarioQueHaEntrado != null) {
            textoBienvenida.setText("¡Hola de nuevo, " + usuarioQueHaEntrado.getCorreoElectronico() + "!");
        }
    }
    /**
    * Ejecuta la acción guardarVentana.
    * @param ventana el parámetro ventana
    */
    public void guardarVentana(Stage ventana) {
        this.ventanaActual = ventana;
    }
    /**
    * Ejecuta la acción recibirServicioUsuarios.
    * @param servicio el parámetro servicio
    */
    public void recibirServicioUsuarios(UsuariosService servicio) {
        this.servicioDeUsuarios = servicio;
    }
    /**
    * Ejecuta la acción recibirServicioOficinas.
    * @param servicio el parámetro servicio
    */
    public void recibirServicioOficinas(OficinasService servicio) {
        this.servicioDeOficinas = servicio;
    }
    /**
    * Ejecuta la acción recibirServicioMateriales.
    * @param servicio el parámetro servicio
    */
    public void recibirServicioMateriales(MaterialesService servicio) {
        this.servicioDeMateriales = servicio;
    }
    /**
    * Ejecuta la acción recibirServicioHistorial.
    * @param servicio el parámetro servicio
    */
    public void recibirServicioHistorial(HistorialMaterialesService servicio) {
        this.servicioDeHistorial = servicio;
    }
    /**
    * Ejecuta la acción prepararTablas.
    */
    public void prepararTablas() {
        if (usuarioQueHaEntrado != null) {
            // Le pasamos los servicios a los controladores hijos
            if (vistaUsuariosController != null) {
                vistaUsuariosController.setService(servicioDeUsuarios);
            }
            if (vistaOficinasController != null) {
                vistaOficinasController.setServicio(servicioDeOficinas);
            }
            if (vistaMaterialesController != null) {
                vistaMaterialesController.setServicios(servicioDeMateriales, servicioDeOficinas);
                vistaMaterialesController.asignarUsuarioLogueado(usuarioQueHaEntrado);
                vistaMaterialesController.setAlCambiarHistorial(this::cargarListaHistorial);
            }
            if (vistaHistorialController != null) {
                vistaHistorialController.setService(servicioDeHistorial);
            }

            cargarListaHistorial();
        }
    }
    /**
    * Ejecuta la acción cargarListaHistorial.
    */
    public void cargarListaHistorial() {
        if (vistaHistorialController != null) {
            vistaHistorialController.cargarListaHistorial();
        }
    }

    @FXML
    /**
    * Ejecuta la acción botonCerrarSesion.
    */
    private void botonCerrarSesion() {
        try {
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Parent vista = cargador.load();

            LoginController controladorDeLogin = cargador.getController();
            controladorDeLogin.asignarServicioUsuarios(servicioDeUsuarios);
            controladorDeLogin.asignarServicioOficinas(servicioDeOficinas);
            controladorDeLogin.asignarServicioMateriales(servicioDeMateriales);
            controladorDeLogin.asignarServicioHistorial(servicioDeHistorial);
            controladorDeLogin.asignarVentana(ventanaActual);

            Scene nuevaEscena = new Scene(vista);
            ventanaActual.setTitle("Programa de Inventario");
            ventanaActual.setScene(nuevaEscena);
            ventanaActual.show();
        } catch (IOException e) {
            System.out.println("Ha fallado al intentar salir: " + e.getMessage());
        }
    }
}