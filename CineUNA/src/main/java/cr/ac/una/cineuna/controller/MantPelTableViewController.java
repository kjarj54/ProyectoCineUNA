/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProPeliculasDto;
import cr.ac.una.cineuna.service.ProPeliculasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Formato;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.time.LocalDate;
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

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class MantPelTableViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXButton btnFiltrar;
    @FXML
    private Label lblTitulo;
    @FXML
    private TableView<ProPeliculasDto> tbvResultados;
    @FXML
    private TableColumn<ProPeliculasDto, Boolean> tbcEditar;
    @FXML
    private TableColumn<ProPeliculasDto, String> tbcId;
    @FXML
    private TableColumn<ProPeliculasDto, String> tbcNombre;
    @FXML
    private TableColumn<ProPeliculasDto, String> tbcSynopsis;
    @FXML
    private TableColumn<ProPeliculasDto, String> tbcLink;
    @FXML
    private TableColumn<ProPeliculasDto, LocalDate> tbcFecha;
    @FXML
    private JFXButton btnAceptar;
    @FXML
    private JFXButton btnNuevo;

    private EventHandler<KeyEvent> keyEnter;

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

        txtId.setTextFormatter(Formato.getInstance().integerFormat());
        txtNombre.setTextFormatter(Formato.getInstance().maxLengthFormat(30));

        tbcId.setCellValueFactory(clbck -> clbck.getValue().pelId);
        tbcNombre.setCellValueFactory(clbck -> clbck.getValue().pelNombre);
        tbcLink.setCellValueFactory(clbck -> clbck.getValue().pelLink);
        tbcSynopsis.setCellValueFactory(clbck -> clbck.getValue().pelSynopsis);
        tbcFecha.setCellValueFactory(clbck -> clbck.getValue().pelFechaestreno);

        ProPeliculasService proPeliculasService = new ProPeliculasService();

        Respuesta respuesta = proPeliculasService.getPeliculasSinParametros();

        if (respuesta.getEstado()) {
            ObservableList<ProPeliculasDto> proPeliculasDtos = FXCollections.observableList((List<ProPeliculasDto>) respuesta.getResultado("ProPeliculas"));
            tbvResultados.setItems(proPeliculasDtos);
            tbvResultados.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta peliculas", getStage(), respuesta.getMensaje());
        }

        tbcEditar.setCellValueFactory((TableColumn.CellDataFeatures<ProPeliculasDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEditar.setCellFactory((TableColumn<ProPeliculasDto, Boolean> p) -> new ButtonCell());

    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        try{
        tbvResultados.getItems();
        ProPeliculasService proPeliculasService = new ProPeliculasService();

        String id = txtId.getText();
        String nombre = txtNombre.getText();

        if (id.isEmpty()) {
            id = "%" + txtId.getText();
        }
        if (nombre.isEmpty()) {
            nombre = "%" + txtNombre.getText();
        }

        Respuesta respuesta = proPeliculasService.getPeliculas(id,nombre);
        
        if (respuesta.getEstado()) {
            ObservableList<ProPeliculasDto> proPeliculasDtos = FXCollections.observableList((List<ProPeliculasDto>) respuesta.getResultado("ProPeliculasParam"));
            tbvResultados.setItems(proPeliculasDtos);
            tbvResultados.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta peliculas", getStage(), respuesta.getMensaje());
        }
        }catch( Exception ex){
            Logger.getLogger(MantPelTableViewController.class.getName()).log(Level.SEVERE, "Error consultando los usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consultar usuarios", getStage(), "Ocurrio un error consultando los usuarios.");
        }

    }

    @FXML
    private void OnMousePressedTbvResultados(MouseEvent event) {
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {

    }

    @FXML
    private void onActionBtnNuevo(ActionEvent event) {
        FlowController.getInstance().goView("MantPeliculasView");
    }

    @Override
    public void initialize() {

    }

    private class ButtonCell extends TableCell<ProPeliculasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("");
            cellButton.setText("Editar");
            cellButton.setStyle("-fx-background-color: #00FFA6");
            cellButton.setOnAction((ActionEvent t) -> {
                ProPeliculasDto emp = (ProPeliculasDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                AppContext.getInstance().set("EditarPelicula", emp.getPelId());
                FlowController.getInstance().goView("UpdatePelView");
                FlowController.getInstance().limpiarLoader("MantPelTableView");
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
