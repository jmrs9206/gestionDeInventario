package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.model.Material;
import org.example.model.Usuario;
import org.example.model.enums.EstadoMaterial;
import org.example.service.OficinasService;
import org.example.service.MaterialesService;

import java.util.List;

public class MaterialController {
    
    private MaterialesService servicioDeMateriales;
    private OficinasService servicioDeOficinas;

    @FXML private TableView<Material> tablaMateriales;
    @FXML private TableColumn<Material, Integer> columnaIdMaterial;
    @FXML private TableColumn<Material, String> columnaTipoMaterial;
    @FXML private TableColumn<Material, String> columnaMarcaMaterial;
    @FXML private TableColumn<Material, String> columnaModeloMaterial;
    @FXML private TableColumn<Material, String> columnaNotasMaterial;
    @FXML private TableColumn<Material, EstadoMaterial> columnaActivoMaterial;

    @FXML private TextField inputOficinaMaterial;
    @FXML private ComboBox<EstadoMaterial> comboEstadoMaterial;

    @FXML private TextField inputTipoMaterial;
    @FXML private TextField inputMarcaMaterial;
    @FXML private TextField inputModeloMaterial;
    @FXML private TextField inputNotasMaterial;
    @FXML private Label labelAvisosMaterial;

    private Material materialSeleccionado;
    private Usuario usuarioLogueado;
    private Runnable alCambiarHistorial;

    public void setServicios(MaterialesService servicio, OficinasService servicioOficinas) {
        this.servicioDeMateriales = servicio;
        this.servicioDeOficinas = servicioOficinas;
        cargarTabla();
    }

    public void asignarUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
    }

    public void setAlCambiarHistorial(Runnable alCambiarHistorial) {
        this.alCambiarHistorial = alCambiarHistorial;
    }

    @FXML
    private void initialize() {
        inicializarColumnas();
        configurarSeleccionTabla();
        if (comboEstadoMaterial != null) {
            comboEstadoMaterial.setItems(FXCollections.observableArrayList(EstadoMaterial.values()));
        }
    }

    private void inicializarColumnas() {
        if (columnaIdMaterial != null) columnaIdMaterial.setCellValueFactory(new PropertyValueFactory<>("idMaterial"));
        if (columnaTipoMaterial != null) columnaTipoMaterial.setCellValueFactory(new PropertyValueFactory<>("tipoMaterial"));
        if (columnaMarcaMaterial != null) columnaMarcaMaterial.setCellValueFactory(new PropertyValueFactory<>("marcaMaterial"));
        if (columnaModeloMaterial != null) columnaModeloMaterial.setCellValueFactory(new PropertyValueFactory<>("modeloMaterial"));
        if (columnaNotasMaterial != null) columnaNotasMaterial.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        if (columnaActivoMaterial != null) columnaActivoMaterial.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void configurarSeleccionTabla() {
        if (tablaMateriales != null) {
            tablaMateriales.getSelectionModel().selectedItemProperty().addListener((observable, viejaSeleccion, nuevaSeleccion) -> {
                if (nuevaSeleccion != null) {
                    materialSeleccionado = nuevaSeleccion;
                    inputTipoMaterial.setText(materialSeleccionado.getTipoMaterial());
                    inputMarcaMaterial.setText(materialSeleccionado.getMarcaMaterial());
                    inputModeloMaterial.setText(materialSeleccionado.getModeloMaterial());
                    inputNotasMaterial.setText(materialSeleccionado.getDescripcion());
                    inputOficinaMaterial.setText(String.valueOf(materialSeleccionado.getIdOficina()));
                    comboEstadoMaterial.setValue(materialSeleccionado.getEstado());
                    labelAvisosMaterial.setText("");
                }
            });
        }
    }

    public void cargarTabla() {
        if (servicioDeMateriales == null || tablaMateriales == null) return;
        try {
            List<Material> lista = servicioDeMateriales.verTodos();
            ObservableList<Material> listaParaPantalla = FXCollections.observableArrayList(lista);
            tablaMateriales.setItems(listaParaPantalla);
            labelAvisosMaterial.setText("");
        } catch (Exception e) {
            labelAvisosMaterial.setText("Aviso: " + e.getMessage());
        }
    }

    @FXML
    public void crearMaterial() {
        try {
            String tipo = inputTipoMaterial.getText();
            String marca = inputMarcaMaterial.getText();
            String modelo = inputModeloMaterial.getText();
            String notas = inputNotasMaterial.getText();
            
            String oficinaStr = inputOficinaMaterial.getText();
            if (oficinaStr == null || oficinaStr.isEmpty()) {
                labelAvisosMaterial.setText("Introduce un ID de oficina.");
                return;
            }
            
            int idOficina;
            try {
                idOficina = Integer.parseInt(oficinaStr);
            } catch (NumberFormatException e) {
                labelAvisosMaterial.setText("El ID de oficina debe ser un número.");
                return;
            }

            EstadoMaterial estado = comboEstadoMaterial.getValue();
            if (estado == null) {
                estado = EstadoMaterial.ALTA;
            }

            int idUsuario = (usuarioLogueado != null) ? usuarioLogueado.getIdUsuario() : 1;

            servicioDeMateriales.registrarMaterial(tipo, marca, modelo, notas, idOficina, estado, idUsuario);
            
            limpiarCampos();
            cargarTabla();
            labelAvisosMaterial.setText("¡Material guardado!");
            
            if (alCambiarHistorial != null) {
                alCambiarHistorial.run();
            }
        } catch (Exception error) {
            labelAvisosMaterial.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    public void modificarMaterial() {
        if (materialSeleccionado == null) {
            labelAvisosMaterial.setText("Selecciona un material primero.");
            return;
        }
        try {
            materialSeleccionado.setTipoMaterial(inputTipoMaterial.getText());
            materialSeleccionado.setMarcaMaterial(inputMarcaMaterial.getText());
            materialSeleccionado.setModeloMaterial(inputModeloMaterial.getText());
            materialSeleccionado.setDescripcion(inputNotasMaterial.getText());
            
            String oficinaStr = inputOficinaMaterial.getText();
            if (oficinaStr == null || oficinaStr.isEmpty()) {
                labelAvisosMaterial.setText("Introduce un ID de oficina.");
                return;
            }
            
            int idOficina;
            try {
                idOficina = Integer.parseInt(oficinaStr);
            } catch (NumberFormatException e) {
                labelAvisosMaterial.setText("El ID de oficina debe ser un número.");
                return;
            }
            materialSeleccionado.setIdOficina(idOficina);

            EstadoMaterial estado = comboEstadoMaterial.getValue();
            if (estado != null) {
                materialSeleccionado.setEstado(estado);
            }

            int idUsuario = (usuarioLogueado != null) ? usuarioLogueado.getIdUsuario() : 1;
            
            servicioDeMateriales.modificarDatosMaterial(materialSeleccionado, idUsuario);

            limpiarCampos();
            materialSeleccionado = null;
            cargarTabla();
            labelAvisosMaterial.setText("¡Datos cambiados!");
            
            if (alCambiarHistorial != null) {
                alCambiarHistorial.run();
            }
        } catch (Exception error) {
            labelAvisosMaterial.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    public void darDeBajaMaterial() {
        if (materialSeleccionado == null) {
            labelAvisosMaterial.setText("Selecciona un material primero.");
            return;
        }
        int idUsuario = (usuarioLogueado != null) ? usuarioLogueado.getIdUsuario() : 1;
        try {
            servicioDeMateriales.darDeBajaMaterial(
                    materialSeleccionado.getIdMaterial(),
                    idUsuario,
                    materialSeleccionado.getIdOficina()
            );

            limpiarCampos();
            materialSeleccionado = null;
            cargarTabla();
            labelAvisosMaterial.setText("¡Se ha dado de baja el material!");
            
            if (alCambiarHistorial != null) {
                alCambiarHistorial.run();
            }
        } catch (Exception error) {
            labelAvisosMaterial.setText("Error: " + error.getMessage());
        }
    }

    @FXML
    public void darDeAltaMaterial() {
        if (materialSeleccionado == null) {
            labelAvisosMaterial.setText("Selecciona un material primero.");
            return;
        }
        int idUsuario = (usuarioLogueado != null) ? usuarioLogueado.getIdUsuario() : 1;
        try {
            servicioDeMateriales.cambiarEstado(materialSeleccionado, EstadoMaterial.ALTA, idUsuario);

            limpiarCampos();
            materialSeleccionado = null;
            cargarTabla();
            labelAvisosMaterial.setText("¡Se ha dado de alta el material!");
            
            if (alCambiarHistorial != null) {
                alCambiarHistorial.run();
            }
        } catch (Exception error) {
            labelAvisosMaterial.setText("Error: " + error.getMessage());
        }
    }

    private void limpiarCampos() {
        inputTipoMaterial.clear();
        inputMarcaMaterial.clear();
        inputModeloMaterial.clear();
        inputNotasMaterial.clear();
        inputOficinaMaterial.clear();
        if (comboEstadoMaterial != null) {
            comboEstadoMaterial.getSelectionModel().clearSelection();
        }
    }
}