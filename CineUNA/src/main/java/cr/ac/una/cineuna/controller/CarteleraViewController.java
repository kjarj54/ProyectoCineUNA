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

/**
 * FXML Controller class
 *
 * @author UNA-Audivisuales
 */
public class CarteleraViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnEstrenos;
    @FXML
    private JFXButton btnCartelera;

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
    private void OnActionbtnEstrenos(ActionEvent event) {
        FlowController.getInstance().goView("EstrenosView");
    }

    @FXML
    private void OnActionbtnCartelera(ActionEvent event) {
        FlowController.getInstance().goView("PelisActualesView");
    }
    
}
