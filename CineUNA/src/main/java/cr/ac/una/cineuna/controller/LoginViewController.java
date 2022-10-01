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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class LoginViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane PantallaInicio;
    @FXML
    private ImageView imagen1;
    @FXML
    private ImageView imagen2;
    @FXML
    private JFXButton btnIngresar;
    @FXML
    private JFXButton btnResgistrarse;
    @FXML
    private JFXButton btnRecuperar;

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
    private void OnActionbtnIngresar(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("PrincipalView");
        getStage().close();
    }

    @FXML
    private void OnActionbtnResgistrarse(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("UsuariosView");
    }

    @FXML
    private void OnActionbtnRecuperar(ActionEvent event) {
    }
    
}
