
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class RecuperarContraViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnRecuperar;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private AnchorPane PantallaRecuperar;
    @FXML
    private VBox VBoxContainer;
    @FXML
    private JFXButton btnAtras;

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
    private void OnActionbtnRecuperar(ActionEvent event) {
    }
    
    @FXML
    private void onActionBtnAtras(ActionEvent event) {
        getStage().close();
    }
    
}
