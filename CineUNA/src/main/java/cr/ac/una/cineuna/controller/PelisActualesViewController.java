/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class PelisActualesViewController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private Image ConvertToImage(byte[] data) {
        return new Image(new ByteArrayInputStream(data));
    }

    @Override
    public void initialize() {
        
    }
    
}
