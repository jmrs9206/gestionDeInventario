package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.dao.impl.HistorialMaterialesDAOImpl;
import org.example.dao.impl.MaterialDAOImpl;
import org.example.dao.impl.OficinaDAOImpl;
import org.example.dao.impl.UsuarioDAOImpl;
import org.example.service.HistorialMaterialesService;
import org.example.service.MaterialesService;
import org.example.service.OficinasService;
import org.example.service.UsuariosService;

import java.io.IOException;

public class InventarioApp {

    public static class AplicacionVisual extends Application {

        @Override
        public void start(Stage ventanaPrincipal) throws IOException {

            UsuarioDAOImpl conexionUsuarios = new UsuarioDAOImpl();
            OficinaDAOImpl conexionOficinas = new OficinaDAOImpl();
            MaterialDAOImpl conexionMateriales = new MaterialDAOImpl();
            HistorialMaterialesDAOImpl conexionHistorial = new HistorialMaterialesDAOImpl();

            UsuariosService usuariosService = new UsuariosService(conexionUsuarios);
            OficinasService oficinasService = new OficinasService(conexionOficinas);
            MaterialesService materialesService = new MaterialesService(conexionMateriales, conexionHistorial);
            HistorialMaterialesService servicioHistorial = new HistorialMaterialesService(conexionHistorial);

            FXMLLoader cargadorDePantalla = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Parent disenoLogin = cargadorDePantalla.load();

            LoginController controladorLogin = cargadorDePantalla.getController();
            controladorLogin.asignarServicioUsuarios(usuariosService);
            controladorLogin.asignarServicioOficinas(oficinasService);
            controladorLogin.asignarServicioMateriales(materialesService);
            controladorLogin.asignarServicioHistorial(servicioHistorial);
            controladorLogin.asignarVentana(ventanaPrincipal);

            Scene escenaLogin = new Scene(disenoLogin);
            ventanaPrincipal.setTitle("Entrar - Inventario");
            ventanaPrincipal.setScene(escenaLogin);
            ventanaPrincipal.show();
        }
    }

    public static void main(String[] args) {
        Application.launch(AplicacionVisual.class, args);
    }
}
