
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.util.BindingUtils;
import cr.ac.una.cineuna.util.Formato;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class UsuariosViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane FondoUsuarios;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApellidos;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtContrasena;
    @FXML
    private JFXTextField txtCorreo;

    List<Node> requeridos = new ArrayList<>();
    ProClientesDto proClientesDto;
    @FXML
    private RadioButton rdbEspañol;
    @FXML
    private ToggleGroup tggIdioma;
    @FXML
    private RadioButton rdbEnglish;
    @FXML
    private JFXButton btnRegistrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdbEnglish.setUserData("I");
        rdbEspañol.setUserData("E");
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtApellidos.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtUsuario.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        txtContrasena.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(100));
        proClientesDto = new ProClientesDto();
        nuevoCliente();
        indicarRequeridos();
    }

    @Override
    public void initialize() {

    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombre, txtApellidos, txtContrasena, txtCorreo, txtUsuario));
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && !((JFXTextField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && !((JFXPasswordField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

    public void bindClientes(Boolean nuevo) {
        txtNombre.textProperty().bindBidirectional(proClientesDto.cliNombre);
        txtApellidos.textProperty().bindBidirectional(proClientesDto.cliPApellido);
        txtContrasena.textProperty().bindBidirectional(proClientesDto.cliClave);
        txtCorreo.textProperty().bindBidirectional(proClientesDto.cliCorreo);
        txtUsuario.textProperty().bindBidirectional(proClientesDto.cliUsuario);
        BindingUtils.bindToggleGroupToProperty(tggIdioma, proClientesDto.cliIdioma);
    }

    public void unbindClientes() {
        txtNombre.textProperty().unbindBidirectional(proClientesDto.cliNombre);
        txtApellidos.textProperty().unbindBidirectional(proClientesDto.cliPApellido);
        txtContrasena.textProperty().unbindBidirectional(proClientesDto.cliClave);
        txtCorreo.textProperty().unbindBidirectional(proClientesDto.cliCorreo);
        txtUsuario.textProperty().unbindBidirectional(proClientesDto.cliUsuario);
        BindingUtils.unbindToggleGroupToProperty(tggIdioma, proClientesDto.cliIdioma);
    }

    public void nuevoCliente() {
        unbindClientes();
        proClientesDto = new ProClientesDto();
        bindClientes(true);

    }

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar usuario", getStage(), invalidos);
            } else {

                ProClientesService service = new ProClientesService();
                Respuesta respuesta = service.guardarCliente(proClientesDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar usuario", getStage(), respuesta.getMensaje());
                } else {
                    unbindClientes();
                    proClientesDto = (ProClientesDto) respuesta.getResultado("Cliente");
                    bindClientes(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar usuario", getStage(), "Usuario actualizado correctamente.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuariosViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar usuario", getStage(), "Ocurrio un error guardando el usuario.");
        }
    }

    @FXML
    private void onActionBtnLimpiar(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar usuario", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoCliente();
          }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        getStage().close();
    }
}

