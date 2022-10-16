
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class LoginViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane PantallaInicio;
    @FXML
    private JFXButton btnIngresar;
    @FXML
    private JFXTextField txtUsuarios;
    @FXML
    private JFXPasswordField txtClave;
    @FXML
    private JFXButton btnAuto;
    @FXML
    private JFXButton btnRegistrar;
    @FXML
    private VBox VBoxContainer;
    
    private Hyperlink linkRecuperarClave;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void onActionBtnAuto(ActionEvent event) {
        
    }

    @FXML
    private void onActionBtnIngresar(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("PrincipalView");
        getStage().close();
    }

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("UsuariosView");
    }

    @FXML
    private void onActionLinkRecuperarClave(ActionEvent event) {
        FlowController.getInstance().goViewInWindow("RecuperarContraView");
    }
    
}
