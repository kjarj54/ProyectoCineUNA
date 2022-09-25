/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class PrincipalViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnUsuarios;
    @FXML
    private JFXButton btnPeliculas;
    @FXML
    private JFXButton btnSalas;

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
    private void OnActionbtnUsuarios(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnPeliculas(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnSalas(ActionEvent event) {
    }
    
}
