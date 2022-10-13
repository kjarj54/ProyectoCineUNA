/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class UsuariosViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane FondoUsuarios;
    @FXML
    private JFXButton btnRegistarar;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApellidos;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtContrasena;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXTextField txtIdioma;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void OnActionbtnRegistarar(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnLimpiar(ActionEvent event) {
        txtApellidos.clear();txtContrasena.clear();txtCorreo.clear();txtIdioma.clear();txtNombre.clear();txtUsuario.clear();
    }

    @FXML
    private void OnActionbtnSalir(ActionEvent event) {
        getStage().close();
    }
    
}
