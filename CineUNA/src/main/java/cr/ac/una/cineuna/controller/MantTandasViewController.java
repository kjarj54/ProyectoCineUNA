/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class MantTandasViewController extends Controller implements Initializable {

    @FXML
    private MenuButton menuPelicula;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtNombreTanda;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTimePicker HoraInicio;
    @FXML
    private JFXTimePicker HoraFinal;
    @FXML
    private JFXTextField txtPrecio;
    @FXML
    private Button btnPeli;
    @FXML
    public JFXTextField txtPeli;

    
    private TablePeliculasViewController menucontroller;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        AppContext.getInstance().set("MantTandasViewController", this);
        
        
        
        
        //txtPeli.setEditable(true);
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void onActionSalir(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        
    }

    @FXML
    private void OnActionbtnPeli(ActionEvent event) {
        FlowController.getInstance().goView("TablePeliculasView");
        //FlowController.getInstance().limpiarLoader("TablePeliculasView");
        
    }
    
}
