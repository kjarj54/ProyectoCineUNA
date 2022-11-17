/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class PrincipalUsuariosViewController extends Controller implements Initializable {

    @FXML
    private BorderPane Fondo;
    @FXML
    private JFXButton btnUsuario;
    @FXML
    private JFXButton btnComidas;
    @FXML
    private JFXButton btnPeliculas;
    @FXML
    private JFXButton btnReservar;
    @FXML
    private JFXButton btnCerrar;

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
    private void OnActionbtnUsuario(ActionEvent event) {
        FlowController.getInstance().goView("MantUsuClientesView");
    }

    @FXML
    private void OnActionBtnComidas(ActionEvent event) {
        FlowController.getInstance().goView("ComidasClienteView");
    }

    @FXML
    private void OnActionbtnPeliculas(ActionEvent event) {
        FlowController.getInstance().goView("CarteleraView");
    }

    @FXML
    private void OnActionbtnReservar(ActionEvent event) {
        FlowController.getInstance().goView("SalaView");
    }

    @FXML
    private void OnActionbtnCerrar(ActionEvent event) {
        FlowController.getInstance().initialize();
        FlowController.getInstance().goViewInWindow("LoginView");
        ((Stage) btnCerrar.getScene().getWindow()).close();
    }
    
}
