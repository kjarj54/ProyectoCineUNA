/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Formato;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author kevin
 */
public class MantUsuTableViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
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

        txtAdmin.setTextFormatter(Formato.getInstance().letrasFormat(1));
        txtEstado.setTextFormatter(Formato.getInstance().letrasFormat(1));
        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtUsuario.setTextFormatter(Formato.getInstance().maxLengthFormat(20));        
        
        tbcId.setCellValueFactory(clbck -> clbck.getValue().cliId);

        tbcNombre.setCellValueFactory(clbck -> clbck.getValue().cliNombre);

        tbcUsuario.setCellValueFactory(clbck -> clbck.getValue().cliUsuario);

        tbcPApellido.setCellValueFactory(clbck -> clbck.getValue().cliPApellido);

        tbcSApellido.setCellValueFactory(clbck -> clbck.getValue().cliSApellido);
        
        tbcCorreo.setCellValueFactory(clbck-> clbck.getValue().cliCorreo);
       
        ProClientesService proClientesService = new ProClientesService();
        
        Respuesta respuesta = proClientesService.getClientesSinParametros();

            if (respuesta.getEstado()) {
                ObservableList<ProClientesDto> proClientesDtos = FXCollections.observableList((List<ProClientesDto>) respuesta.getResultado("ProClientes"));
                tbvResultados.setItems(proClientesDtos);
                tbvResultados.refresh();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta usuarios", getStage(), respuesta.getMensaje());
            }

        tbvResultados.refresh();

        
        

        tbcAdmin.setCellValueFactory((TableColumn.CellDataFeatures<ProClientesDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        //Anyade el checkbox a la columna
        tbcAdmin.setCellFactory((TableColumn<ProClientesDto, Boolean> p) -> new ButtonCell());
        
        
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
        try {

            tbvResultados.getItems();
            ProClientesService proClientesService = new ProClientesService();

            String id = txtId.getText();
            String nombre = txtNombre.getText();
            String usuario = txtUsuario.getText();
            String estado = txtEstado.getText();
            String admin = txtAdmin.getText();

            if (id.isEmpty()) {
                id = "%" + txtId.getText();
            }
            if (nombre.isEmpty()) {
                nombre = "%" + txtNombre.getText();
            }
            if (usuario.isEmpty()) {
                usuario = "%" + txtUsuario.getText();
            }
            if (estado.isEmpty()) {
                estado = "%" + txtEstado.getText();
            }
            if (admin.isEmpty()) {
                admin = "%" + txtAdmin.getText();
            }

            Respuesta respuesta = proClientesService.getClientes(id, nombre, usuario, estado,admin);//manda los filtros por los que se desea buscar

            if (respuesta.getEstado()) {
                ObservableList<ProClientesDto> proClientesDtos = FXCollections.observableList((List<ProClientesDto>) respuesta.getResultado("ProClientes"));//convierte un list a observable list
                tbvResultados.setItems(proClientesDtos);
                tbvResultados.refresh();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta usuarios", getStage(), respuesta.getMensaje());
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuariosViewController.class.getName()).log(Level.SEVERE, "Error consultando los usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar usuarios", getStage(), "Ocurrio un error consultando los usuarios.");
        }

    }

    private class ButtonCell extends TableCell<ProClientesDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Editar");
            cellButton.setStyle("-fx-background-color: #00FFA6");
            
            cellButton.setOnAction((ActionEvent t) -> {
                ProClientesDto emp = (ProClientesDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                AppContext.getInstance().set("EditarUsuario", emp.getCliId());
                FlowController.getInstance().goView("MantUsuariosView");
                FlowController.getInstance().limpiarLoader("MantUsuTableView");
                tbvResultados.refresh();
                
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
