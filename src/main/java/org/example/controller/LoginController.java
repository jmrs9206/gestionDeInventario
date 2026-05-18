package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.Usuario;
import org.example.service.HistorialMaterialesService;
import org.example.service.MaterialesService;
import org.example.service.OficinasService;
import org.example.service.UsuariosService;

import java.io.IOException;

public class LoginController {
  @FXML
  private TextField inputCorreo;
  @FXML
  private PasswordField inputContrasena;
  @FXML
  private Label labelAvisosLogin;

  private UsuariosService servicioDeUsuarios;
  private OficinasService servicioDeOficinas;
  private MaterialesService servicioDeMateriales;
  private HistorialMaterialesService servicioDeHistorial;

  private Stage ventanaActual;

  public void asignarServicioUsuarios(UsuariosService servicio) {
    this.servicioDeUsuarios = servicio;
  }

  public void asignarServicioOficinas(OficinasService servicio) {
    this.servicioDeOficinas = servicio;
  }

  public void asignarServicioMateriales(MaterialesService servicio) {
    this.servicioDeMateriales = servicio;
  }

  public void asignarServicioHistorial(HistorialMaterialesService servicio) {
    this.servicioDeHistorial = servicio;
  }

  public void asignarVentana(Stage ventana) {
    this.ventanaActual = ventana;
  }

  @FXML
  private void initialize() {
    labelAvisosLogin.setText("");
  }

  @FXML
  private void pulsarBotonEntrar() {
    String correoEscrito = inputCorreo.getText();
    String claveEscrita = inputContrasena.getText();

    try {
      Usuario elQueEntra = servicioDeUsuarios.comprobarLogin(correoEscrito, claveEscrita);
      labelAvisosLogin.setText("Has entrado. ¡Hola " + elQueEntra.getNombreUsuario() + "!");

      try {
        abrirPantallaPrincipal(elQueEntra);
      } catch (IOException errorAlCargarLaPantalla) {
        labelAvisosLogin.setText("Error al cargar la pantalla principal: " + errorAlCargarLaPantalla.getMessage());
      }

    } catch (Exception errorDeLogin) {
      labelAvisosLogin.setText(errorDeLogin.getMessage());
    }
  }

  private void abrirPantallaPrincipal(Usuario usuarioQueHaEntrado) throws IOException {
    FXMLLoader cargador = new FXMLLoader(getClass().getResource("/view/dashboard.fxml"));
    Parent vista = cargador.load();

    DashboardController controladorNuevo = cargador.getController();
    controladorNuevo.guardarUsuarioQueUsaElPrograma(usuarioQueHaEntrado);
    controladorNuevo.guardarVentana(ventanaActual);

    controladorNuevo.recibirServicioUsuarios(servicioDeUsuarios);
    controladorNuevo.recibirServicioOficinas(servicioDeOficinas);
    controladorNuevo.recibirServicioMateriales(servicioDeMateriales);
    controladorNuevo.recibirServicioHistorial(servicioDeHistorial);

    controladorNuevo.prepararTablas();

    Scene nuevaEscena = new Scene(vista);
    ventanaActual.setTitle("Panel de Control - Inventario");
    ventanaActual.setScene(nuevaEscena);
    ventanaActual.show();
  }

}
