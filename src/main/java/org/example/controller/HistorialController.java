package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.HistorialMateriales;
import org.example.model.enums.EstadoMaterial;
import org.example.service.HistorialMaterialesService;

import java.time.LocalDate;
import java.util.List;

public class HistorialController {

    private HistorialMaterialesService servicioDeHistorial;

    @FXML private TableView<HistorialMateriales> tablaHistorial;
    @FXML private TableColumn<HistorialMateriales, Integer> columnaIdRegistro;
    @FXML private TableColumn<HistorialMateriales, String> columnaAccionRealizada;
    @FXML private TableColumn<HistorialMateriales, String> columnaIdOficinaImplicada;
    @FXML private TableColumn<HistorialMateriales, String> columnaIdUsuarioResponsable;
    @FXML private TableColumn<HistorialMateriales, EstadoMaterial> columnaEstadoMaterial;
    @FXML private TableColumn<HistorialMateriales, LocalDate> columnaFechaRegistro;
    @FXML private TableColumn<HistorialMateriales, String> columnaMarcaMaterialHistorial;
    @FXML private TableColumn<HistorialMateriales, String> columnaModeloMaterialHistorial;
    @FXML private TableColumn<HistorialMateriales, String> columnaNotasHistorial;
    @FXML private Label labelAvisosHistorial;

    public void setService(HistorialMaterialesService servicio) {
        this.servicioDeHistorial = servicio;
        cargarListaHistorial();
    }

    @FXML
    private void initialize() {
        prepararTablaHistorial();
    }

    private void prepararTablaHistorial() {
        if (columnaIdRegistro != null) columnaIdRegistro.setCellValueFactory(new PropertyValueFactory<>("id"));
        if (columnaFechaRegistro != null) columnaFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        if (columnaEstadoMaterial != null) columnaEstadoMaterial.setCellValueFactory(new PropertyValueFactory<>("estado"));
        if (columnaAccionRealizada != null) columnaAccionRealizada.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        if (columnaMarcaMaterialHistorial != null) columnaMarcaMaterialHistorial.setCellValueFactory(new PropertyValueFactory<>("marca"));
        if (columnaModeloMaterialHistorial != null) columnaModeloMaterialHistorial.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        if (columnaIdOficinaImplicada != null) columnaIdOficinaImplicada.setCellValueFactory(new PropertyValueFactory<>("oficina"));
        if (columnaIdUsuarioResponsable != null) columnaIdUsuarioResponsable.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        if (columnaNotasHistorial != null) columnaNotasHistorial.setCellValueFactory(new PropertyValueFactory<>("observacion"));
    }

    public void cargarListaHistorial() {
        if (servicioDeHistorial == null || tablaHistorial == null) return;
        try {
            List<HistorialMateriales> lista = servicioDeHistorial.verTodoElHistorial();
            ObservableList<HistorialMateriales> listaParaPantalla = FXCollections.observableArrayList(lista);
            tablaHistorial.setItems(listaParaPantalla);
            if (labelAvisosHistorial != null) labelAvisosHistorial.setText("");
        } catch (Exception e) {
            if (labelAvisosHistorial != null) labelAvisosHistorial.setText("Aviso: " + e.getMessage());
        }
    }
}
