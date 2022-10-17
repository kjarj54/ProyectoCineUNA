package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        try {

            if (txtUsuarios.getText() == null || txtUsuarios.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", (Stage) btnIngresar.getScene().getWindow(), "Es necesario digitar un usuario para ingresar al sistema.");
            } else if (txtClave.getText() == null || txtClave.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Validación de usuario", (Stage) btnIngresar.getScene().getWindow(), "Es necesario digitar la clave para ingresar al sistema.");
            } else {
                /*ProClientesService proClientesService = new ProClientesService();
                Respuesta respuesta = proClientesService.getCliente(txtUsuarios.getText(), txtClave.getText());
                if (respuesta.getEstado()) {
                    ProClientesDto proClientesDto = (ProClientesDto) respuesta.getResultado("Empleado");
                    AppContext.getInstance().set("Usuario", proClientesDto);
                    AppContext.getInstance().set("Token", proClientesDto.getToken());
                    if (getStage().getOwner() == null) {
                        FlowController.getInstance().goViewInWindow("PrincipalView");
                    }
                    getStage().close();
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Ingreso", getStage(), respuesta.getMensaje());
                }*/
                FlowController.getInstance().goViewInWindow("PrincipalView");
                getStage().close();
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error ingresando.", ex);
        }
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
