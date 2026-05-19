package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Usuario;
import org.example.model.enums.EstadoGeneral;
import org.example.service.UsuariosService;

import java.util.List;
/**
     * Representa la clase UsuarioController.
     */
public class UsuarioController {
    
    private UsuariosService servicioDeUsuarios;

    @FXML private TableView<Usuario> tablaUsuarios;
    @FXML private TableColumn<Usuario, Integer> columnaIdUsuario;
    @FXML private TableColumn<Usuario, String> columnaCorreoUsuario;
    @FXML private TableColumn<Usuario, EstadoGeneral> columnaActivoUsuario;

    @FXML private TextField inputNombreUsuario;
    @FXML private TextField inputCorreoUsuario;
    @FXML private PasswordField inputContrasenaUsuario;
    @FXML private Label labelAvisosUsuario;

    private Usuario usuarioSeleccionado;
    /**
    * Ejecuta la acción setService.
    * @param servicio el parámetro servicio
    */
    public void setService(UsuariosService servicio) {
        this.servicioDeUsuarios = servicio;
        cargarTabla();
    }

    @FXML
    /**
    * Ejecuta la acción initialize.
    */
    private void initialize() {
        inicializarColumnas();
        configurarSeleccionTabla();
    }
    /**
    * Ejecuta la acción inicializarColumnas.
    */
    private void inicializarColumnas() {
        if (columnaIdUsuario != null) columnaIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        if (columnaCorreoUsuario != null) columnaCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoElectronico"));
        if (columnaActivoUsuario != null) columnaActivoUsuario.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
    /**
    * Ejecuta la acción configurarSeleccionTabla.
    */
    private void configurarSeleccionTabla() {
        if (tablaUsuarios != null) {
            tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((observable, viejaSeleccion, nuevaSeleccion) -> {
                if (nuevaSeleccion != null) {
                    usuarioSeleccionado = nuevaSeleccion;
                    inputNombreUsuario.setText(usuarioSeleccionado.getNombreUsuario());
                    inputCorreoUsuario.setText(usuarioSeleccionado.getCorreoElectronico());
                    labelAvisosUsuario.setText("");
                }
            });
        }
    }
    /**
    * Ejecuta la acción cargarTabla.
    */
    public void cargarTabla() {
        if (servicioDeUsuarios == null || tablaUsuarios == null) return;
        try {
            List<Usuario> lista = servicioDeUsuarios.verTodos();
            ObservableList<Usuario> listaParaPantalla = FXCollections.observableArrayList(lista);
            tablaUsuarios.setItems(listaParaPantalla);
            labelAvisosUsuario.setText("");
        } catch (Exception e) {
            labelAvisosUsuario.setText("Error al cargar la tabla: " + e.getMessage());
        }
    }

    @FXML
    /**
    * Ejecuta la acción crearUsuario.
    */
    public void crearUsuario() {
        try {
            String nombre = inputNombreUsuario.getText();
            String correo = inputCorreoUsuario.getText();
            String clave = inputContrasenaUsuario.getText();

            if (nombre == null || nombre.isEmpty()) {
                labelAvisosUsuario.setText("El nombre no puede estar vacío.");
                return;
            }

            servicioDeUsuarios.registrarUsuario(nombre, correo, clave);
            limpiarCampos();
            cargarTabla();
            labelAvisosUsuario.setText("¡Usuario guardado!");
        } catch (Exception error) {
            labelAvisosUsuario.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    /**
    * Ejecuta la acción modificarUsuario.
    */
    public void modificarUsuario() {
        if (usuarioSeleccionado == null) {
            labelAvisosUsuario.setText("Selecciona un usuario primero.");
            return;
        }
        try {
            usuarioSeleccionado.setNombreUsuario(inputNombreUsuario.getText());
            usuarioSeleccionado.setCorreoElectronico(inputCorreoUsuario.getText());
            
            if (!inputContrasenaUsuario.getText().isEmpty()) {
                usuarioSeleccionado.setContrasena(inputContrasenaUsuario.getText());
            }

            servicioDeUsuarios.modificarDatosUsuario(usuarioSeleccionado);
            limpiarCampos();
            usuarioSeleccionado = null;
            cargarTabla();
            labelAvisosUsuario.setText("¡Datos cambiados!");
        } catch (Exception error) {
            labelAvisosUsuario.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    /**
    * Ejecuta la acción darDeBajaUsuario.
    */
    public void darDeBajaUsuario() {
        if (usuarioSeleccionado == null) {
            labelAvisosUsuario.setText("Selecciona un usuario primero.");
            return;
        }
        try {
            servicioDeUsuarios.darBaja(usuarioSeleccionado);
            limpiarCampos();
            usuarioSeleccionado = null;
            cargarTabla();
            labelAvisosUsuario.setText("¡Se ha dado de baja el usuario!");
        } catch (Exception error) {
            labelAvisosUsuario.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    /**
    * Ejecuta la acción darDeAltaUsuario.
    */
    public void darDeAltaUsuario() {
        if (usuarioSeleccionado == null) {
            labelAvisosUsuario.setText("Selecciona un usuario primero.");
            return;
        }
        try {
            servicioDeUsuarios.darAlta(usuarioSeleccionado);
            limpiarCampos();
            usuarioSeleccionado = null;
            cargarTabla();
            labelAvisosUsuario.setText("¡Se ha dado de alta el usuario!");
        } catch (Exception error) {
            labelAvisosUsuario.setText("Error: " + error.getMessage());
        }
    }
    /**
    * Ejecuta la acción limpiarCampos.
    */
    private void limpiarCampos() {
        inputNombreUsuario.clear();
        inputCorreoUsuario.clear();
        inputContrasenaUsuario.clear();
    }
}