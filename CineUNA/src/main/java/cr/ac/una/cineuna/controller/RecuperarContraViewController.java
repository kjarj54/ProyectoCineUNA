package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;

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

    }

    @Override
    public void initialize() {

    }

    @FXML
    private void OnActionbtnRecuperar(ActionEvent event) {
        try {
            if (txtCorreo.getText() == null || txtCorreo.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Recuperacion de clave", (Stage) btnRecuperar.getScene().getWindow(), "Es necesario digitar un ccorreo para enviar la restauracion");
            } else {
                ProClientesService proClientesService = new ProClientesService();
                Respuesta respuesta = proClientesService.recuperarClave(txtCorreo.getText());
                if (respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Recuperacion de clave", (Stage) btnRecuperar.getScene().getWindow(), "El correo fue enviado correctamente.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Recuperacion de clave", (Stage) btnRecuperar.getScene().getWindow(), "Ocurrio un error enviando el correo de recuperacion.");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, "Error enviando el correo.", ex);
        }

    }

    @FXML
    private void onActionBtnAtras(ActionEvent event) {
        getStage().close();
    }

}
