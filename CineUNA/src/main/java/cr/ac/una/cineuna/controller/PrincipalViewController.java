
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private JFXButton btnCerrar;
    @FXML
    private JFXButton btnComidas;
    @FXML
    private BorderPane brdrPane;
    @FXML
    private JFXButton btnTanda;

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
        FlowController.getInstance().goView("MantUsuTableView");
    }

    @FXML
    private void OnActionbtnPeliculas(ActionEvent event) {
    FlowController.getInstance().goView("MantPelTableView");
    }

    @FXML
    private void OnActionbtnSalas(ActionEvent event) {
        FlowController.getInstance().goView("MantSalasView");
    }

    @FXML
    private void OnActionbtnCerrar(ActionEvent event) {
        
        FlowController.getInstance().initialize();
        FlowController.getInstance().goViewInWindow("LoginView");
        ((Stage) btnCerrar.getScene().getWindow()).close();
    }

    @FXML
    private void OnActionBtnComidas(ActionEvent event) {
        FlowController.getInstance().goView("ComidasAdminView");
    }

    @FXML
    private void OnActionbtnTANDAS(ActionEvent event) {
        FlowController.getInstance().goView("MantTandasView");
    }
    
}
