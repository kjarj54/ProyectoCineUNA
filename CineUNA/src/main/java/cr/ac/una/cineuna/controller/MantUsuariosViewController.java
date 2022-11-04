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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class MantUsuariosViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtClave;
    @FXML
    private JFXTextField txtPApellido;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXTextField txtSApellido;
    @FXML
    private RadioButton rdbEspañol;
    @FXML
    private ToggleGroup tggIdioma1;
    @FXML
    private RadioButton rdbEnglish;
    @FXML
    private ToggleGroup tggIdioma;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private JFXButton btnAtras;
    @FXML
    private JFXButton btnGuardar;

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
    private void OnActionBtnCancelar(ActionEvent event) {
    }

    @FXML
    private void OnActionBtnAtras(ActionEvent event) {
    }

    @FXML
    private void OnActionBtnGuardar(ActionEvent event) {
    }
    
}
