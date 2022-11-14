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
import cr.ac.una.cineuna.util.FlowController;
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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML
    private JFXButton btnLimpar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        txtDescripcion.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtNombre.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtPrecio.setTextFormatter(Formato.getInstance().integerFormat());

        proComidasDto = new ProComidasDto();
        nuevaComida();

        tbcNombre.setCellValueFactory(clbck -> clbck.getValue().comNombre);

        tbcDescripcion.setCellValueFactory(clbck -> clbck.getValue().comDescripcion);

        tbcPrecio.setCellValueFactory(clbck -> clbck.getValue().comPrecio);

        actualizarTbv();

        tbvComidas.refresh();

        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<ProComidasDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));

        //Adding the Button to the cell
        tbcEliminar.setCellFactory((TableColumn<ProComidasDto, Boolean> p) -> new ButtonCell());
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
    
    public void actualizarTbv(){
        ProComidasService proComidasService = new ProComidasService();

        Respuesta respuesta = proComidasService.getComidas();

        if (respuesta.getEstado()) {
            ObservableList<ProComidasDto> comidasDtos = FXCollections.observableList((List<ProComidasDto>) respuesta.getResultado("ProComidas"));
            tbvComidas.setItems(comidasDtos);
            tbvComidas.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta usuarios", getStage(), respuesta.getMensaje());
        }
    }

    @Override
    public void initialize() {

    }
    
    public void limpiar(){
        
    }

    @FXML
    private void onActionBtinAceptar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar comida", getStage(), invalidos);
            } else {

                ProComidasService service = new ProComidasService();
                Respuesta respuesta = service.guardarComida(proComidasDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar comida", getStage(), respuesta.getMensaje());
                } else {
                    unbindComidas();
                    proComidasDto = (ProComidasDto) respuesta.getResultado("Comida");
                    bindComidas();

                    tbvComidas.refresh();
                    
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar comida", getStage(), "Comida actualizado correctamente.");
                }
                
                actualizarTbv();

                tbvComidas.refresh();

            }

        } catch (Exception ex) {
            Logger.getLogger(ComidasAdminViewController.class.getName()).log(Level.SEVERE, "Error guardadno la comida.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar comida", getStage(), "Ocurrio un error guardado la comida.");
        }

    }

    @FXML
    private void onActionBtnAtras(ActionEvent event) {

    }

    @FXML
    private void OnActionbtnLimpiar(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar usuario", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevaComida();
            
        }
    }

    private class ButtonCell extends TableCell<ProComidasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Eliminar");
            cellButton.setStyle("-fx-background-color: #FF5B41");

            cellButton.setOnAction((ActionEvent t) -> {//se encarga de eliminar tanto de basse de datos como de el tbv
                try {
                    ProComidasDto com = (ProComidasDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    ProComidasService proComidasService = new ProComidasService();
                    Respuesta respuesta = proComidasService.eliminarComida(com.getComId());//Elimina del todo
                    if (!respuesta.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar comida", getStage(), respuesta.getMensaje());
                    } else {
                        new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar comida", getStage(), "Comida eliminada correctamente.");
                    }
                    nuevaComida();
                    tbvComidas.getItems().remove(com);
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
