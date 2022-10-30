/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProComidasDto;
import cr.ac.una.cineuna.service.ProComidasService;
import cr.ac.una.cineuna.util.Formato;
import cr.ac.una.cineuna.util.Mensaje;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class ComidasAdminViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtPrecio;
    @FXML
    private JFXTextArea txtDescripcion;
    @FXML
    private JFXButton btnAgregar;
    @FXML
    private TableView<ProComidasDto> tbvComidas;
    @FXML
    private TableColumn<ProComidasDto, String> tbcNombre;
    @FXML
    private TableColumn<ProComidasDto, String> tbcDescripcion;
    @FXML
    private TableColumn<ProComidasDto, String> tbcPrecio;
    @FXML
    private TableColumn<ProComidasDto, Boolean> tbcEliminar;
    @FXML
    private JFXButton btnAtras;

    List<Node> requeridos = new ArrayList<>();
    ProComidasDto proComidasDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        txtDescripcion.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtNombre.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtPrecio.setTextFormatter(Formato.getInstance().integerFormat());

    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombre, txtPrecio));
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

    public void bindComidas() {
        txtDescripcion.textProperty().bindBidirectional(proComidasDto.comDescripcion);
        txtNombre.textProperty().bindBidirectional(proComidasDto.comNombre);
        txtPrecio.textProperty().bindBidirectional(proComidasDto.comPrecio);
    }

    public void unbindComidas() {
        txtDescripcion.textProperty().unbindBidirectional(proComidasDto.comDescripcion);
        txtNombre.textProperty().unbindBidirectional(proComidasDto.comNombre);
        txtPrecio.textProperty().unbindBidirectional(proComidasDto.comPrecio);
    }

    public void nuevaComida() {
        unbindComidas();
        proComidasDto = new ProComidasDto();
        bindComidas();
        txtNombre.requestFocus();

    }

    @Override
    public void initialize() {

    }

    private class ButtonCell extends TableCell<ProComidasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

            cellButton.setOnAction((ActionEvent t) -> {
                try {
                    ProComidasDto emp = (ProComidasDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    ProComidasService proComidasService = new ProComidasService();
                    

                    tbvComidas.getItems().remove(emp);
                    tbvComidas.refresh();
                    
                } catch (Exception ex) {
                    Logger.getLogger(ComidasAdminViewController.class.getName()).log(Level.SEVERE, "Error eliminando la comida.", ex);
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar comida", getStage(), "Ocurrio un error eliminando la comida.");
                }
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

}
