/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.util.AppContext;
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
public class MantUsuClientesViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtClave;
    @FXML
    private JFXTextField txtPApellido;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private JFXTextField txtSApellido;
    @FXML
    private RadioButton rdbEspañol;
    @FXML
    private RadioButton rdbEnglish;
    @FXML
    private ToggleGroup tggIdioma;
    @FXML
    private JFXButton btnAtras;
    @FXML
    private JFXButton btnGuardar;

    List<Node> requeridos = new ArrayList<>();
    ProClientesDto proClientesDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdbEnglish.setUserData("I");
        rdbEspañol.setUserData("E");
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPApellido.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtSApellido.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtUsuario.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        txtClave.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(100));
        proClientesDto = new ProClientesDto();
        ProClientesService service = new ProClientesService();
        Long id = (Long) AppContext.getInstance().get("UsuarioId");

        Respuesta respuesta = service.getCliente(id);

        if (respuesta.getEstado()) {
            unbindClientes();
            proClientesDto = (ProClientesDto) respuesta.getResultado("ProCliente");
            bindClientes(false);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar cliente", getStage(), respuesta.getMensaje());
        }
        indicarRequeridos();

    }

    @Override
    public void initialize() {

    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombre, txtPApellido, txtClave, txtCorreo, txtUsuario));
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
        txtPApellido.textProperty().bindBidirectional(proClientesDto.cliPApellido);
        txtSApellido.textProperty().bindBidirectional(proClientesDto.cliSApellido);
        txtClave.textProperty().bindBidirectional(proClientesDto.cliClave);
        txtCorreo.textProperty().bindBidirectional(proClientesDto.cliCorreo);
        txtUsuario.textProperty().bindBidirectional(proClientesDto.cliUsuario);
        BindingUtils.bindToggleGroupToProperty(tggIdioma, proClientesDto.cliIdioma);
    }

    public void unbindClientes() {
        txtNombre.textProperty().unbindBidirectional(proClientesDto.cliNombre);
        txtPApellido.textProperty().unbindBidirectional(proClientesDto.cliPApellido);
        txtSApellido.textProperty().unbindBidirectional(proClientesDto.cliSApellido);
        txtClave.textProperty().unbindBidirectional(proClientesDto.cliClave);
        txtCorreo.textProperty().unbindBidirectional(proClientesDto.cliCorreo);
        txtUsuario.textProperty().unbindBidirectional(proClientesDto.cliUsuario);
        BindingUtils.unbindToggleGroupToProperty(tggIdioma, proClientesDto.cliIdioma);
    }

    public void nuevoCliente() {
        unbindClientes();
        proClientesDto = new ProClientesDto();
        bindClientes(true);
        txtUsuario.requestFocus();
    }


    @FXML
    private void OnActionBtnAtras(ActionEvent event) {
        
    }

    @FXML
    private void OnActionBtnGuardar(ActionEvent event) {
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

}
