package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Oficina;
import org.example.model.enums.EstadoGeneral;
import org.example.service.OficinasService;

import java.util.List;

public class OficinaController {
    
    private OficinasService servicioDeOficinas;

    @FXML private TableView<Oficina> tablaOficinas;
    @FXML private TableColumn<Oficina, Integer> columnaIdOficina;
    @FXML private TableColumn<Oficina, String> columnaNombreOficina;
    @FXML private TableColumn<Oficina, EstadoGeneral> columnaActivoOficina;

    @FXML private TextField inputNombreOficina;
    @FXML private Label labelAvisosOficina;

    private Oficina oficinaSeleccionada;

    public void setServicio(OficinasService servicio) {
        this.servicioDeOficinas = servicio;
        cargarTabla();
    }

    @FXML
    private void initialize() {
        inicializarColumnas();
        configurarSeleccionTabla();
    }

    private void inicializarColumnas() {
        if (columnaIdOficina != null) columnaIdOficina.setCellValueFactory(new PropertyValueFactory<>("idOficina"));
        if (columnaNombreOficina != null) columnaNombreOficina.setCellValueFactory(new PropertyValueFactory<>("nombreOficina"));
        if (columnaActivoOficina != null) columnaActivoOficina.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void configurarSeleccionTabla() {
        if (tablaOficinas != null) {
            tablaOficinas.getSelectionModel().selectedItemProperty().addListener((observable, viejaSeleccion, nuevaSeleccion) -> {
                if (nuevaSeleccion != null) {
                    oficinaSeleccionada = nuevaSeleccion;
                    inputNombreOficina.setText(oficinaSeleccionada.getNombreOficina());
                    labelAvisosOficina.setText("");
                }
            });
        }
    }

    public void cargarTabla() {
        if (servicioDeOficinas == null || tablaOficinas == null) return;
        try {
            List<Oficina> lista = servicioDeOficinas.verTodas();
            ObservableList<Oficina> listaParaPantalla = FXCollections.observableArrayList(lista);
            tablaOficinas.setItems(listaParaPantalla);
            labelAvisosOficina.setText("");
        } catch (Exception e) {
            labelAvisosOficina.setText("Aviso: " + e.getMessage());
        }
    }

    @FXML
    public void crearOficina() {
        String nombreQueHanEscrito = inputNombreOficina.getText();
        
        try {
            servicioDeOficinas.registrarOficina(nombreQueHanEscrito);
            inputNombreOficina.clear();
            cargarTabla();
            labelAvisosOficina.setText("¡Se ha creado la oficina!");
        } catch (Exception error) {
            labelAvisosOficina.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    public void modificarOficina() {
        if (oficinaSeleccionada == null) {
            labelAvisosOficina.setText("Selecciona una oficina primero.");
            return;
        }
        
        try {
            String nombreNuevo = inputNombreOficina.getText();
            oficinaSeleccionada.setNombreOficina(nombreNuevo);
            servicioDeOficinas.modificarDatosOficina(oficinaSeleccionada);
            
            inputNombreOficina.clear();
            
            oficinaSeleccionada = null;
            cargarTabla();
            labelAvisosOficina.setText("¡Se ha cambiado el nombre!");
        } catch (Exception error) {
            labelAvisosOficina.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    public void cerrarOficina() {
        if (oficinaSeleccionada == null) {
            labelAvisosOficina.setText("Selecciona una oficina primero.");
            return;
        }
        
        try {
            servicioDeOficinas.darBaja(oficinaSeleccionada);
            inputNombreOficina.clear();

            oficinaSeleccionada = null;
            cargarTabla();
            labelAvisosOficina.setText("¡La oficina se ha cerrado!");
        } catch (Exception error) {
            labelAvisosOficina.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    public void reabrirOficina() {
        if (oficinaSeleccionada == null) {
            labelAvisosOficina.setText("Selecciona una oficina primero.");
            return;
        }
        
        try {
            servicioDeOficinas.darAlta(oficinaSeleccionada);
            inputNombreOficina.clear();

            oficinaSeleccionada = null;
            cargarTabla();
            labelAvisosOficina.setText("¡La oficina se ha restaurado!");
        } catch (Exception error) {
            labelAvisosOficina.setText("Error: " + error.getMessage());
        }
    }
}