/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.service.ProClientesService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class MantUsuTableViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private VBox vbxParametros;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtEstado;
    @FXML
    private JFXTextField txtAdmin;
    @FXML
    private JFXButton btnFiltrar;
    @FXML
    private Label lblTitulo;
    @FXML
    private TableView<ProClientesDto> tbvResultados;
    @FXML
    private TableColumn<ProClientesDto, String> tbcId;
    @FXML
    private TableColumn<ProClientesDto, String> tbcNombre;
    @FXML
    private TableColumn<ProClientesDto, String> tbcUsuario;
    @FXML
    private TableColumn<ProClientesDto, String> tbcPApellido;
    @FXML
    private JFXButton btnAceptar;

    private EventHandler<KeyEvent> keyEnter;
    @FXML
    private TableColumn<ProClientesDto, Boolean> tbcAdmin;
    @FXML
    private TableColumn<ProClientesDto, String> tbcEstado;
    @FXML
    private TableColumn<ProClientesDto, String> tbcSApellido;
    @FXML
    private TableColumn<ProClientesDto, String> tbcCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        keyEnter = (KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnFiltrar.fire();
            }
        };
        
        tbcId.setCellValueFactory(clbck-> clbck.getValue().cliId);
        
        tbcNombre.setCellValueFactory(clbck-> clbck.getValue().cliNombre);
        
        tbcUsuario.setCellValueFactory(clbck-> clbck.getValue().cliUsuario);
        
        tbcPApellido.setCellValueFactory(clbck-> clbck.getValue().cliPApellido);
        
        tbcSApellido.setCellValueFactory(clbck-> clbck.getValue().cliSApellido);
        
        tbvResultados.getColumns().add(tbcId);
        tbvResultados.getColumns().add(tbcNombre);
        tbvResultados.getColumns().add(tbcUsuario);
        tbvResultados.getColumns().add(tbcPApellido);
        tbvResultados.getColumns().add(tbcSApellido);
        tbvResultados.refresh();
        
        Callback<TableColumn<ProClientesDto, Boolean>, TableCell<ProClientesDto, Boolean>> boolenaCellFactory = new Callback<TableColumn<ProClientesDto, Boolean>, TableCell<ProClientesDto, Boolean>>() {
            @Override
            public TableCell<ProClientesDto, Boolean> call(TableColumn<ProClientesDto, Boolean> p) {
                return new CheckBoxCell();
            }
        };
        
        tbcAdmin.setCellFactory(boolenaCellFactory);
        //Anyade el checkbox a la columna
        tbcAdmin.setCellFactory((TableColumn<ProClientesDto, Boolean> p) -> new CheckBoxCell());
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void OnMousePressedTbvResultados(MouseEvent event) {

    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {

    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        tbvResultados.getItems();
        ProClientesService proClientesService = new ProClientesService();
        
        String id = txtId.getText();
    }

    private class CheckBoxCell extends TableCell<ProClientesDto, Boolean> {

        final CheckBox cellCheckBox = new CheckBox();

        CheckBoxCell() {
            cellCheckBox.setPrefWidth(500);
            cellCheckBox.getStyleClass().add("jfx-btnimg-tbveliminar");

        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellCheckBox);
            }
        }
    }

}
