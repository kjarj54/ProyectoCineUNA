
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class MantPeliculasViewController extends Controller implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private JFXButton btnCargarImagen;
    @FXML
    private TextArea txtAreaSinopsis;

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
    private void onActionCargarImagen(ActionEvent event) {
    }
    
}
