/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.model.ProComidasDto;
import cr.ac.una.cineuna.model.ProFacturasDto;
import cr.ac.una.cineuna.service.ProComidasService;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class ComidasClienteViewController extends Controller implements Initializable {

    @FXML
    private Label txtComidas;
    @FXML
    private TableView<ProComidasDto> tbvComidas;
    @FXML
    private TableColumn<ProComidasDto, String> tbcNombre;
    @FXML
    private TableColumn<ProComidasDto, String> tbcDescripcion;
    @FXML
    private TableColumn<ProComidasDto, String> tbcPrecio;
    @FXML
    private TableColumn<ProComidasDto, Boolean> tbcAgregar;
    @FXML
    private TableView<ProFacturasDto> tbvFactura;
    @FXML
    private TableColumn<ProFacturasDto, String> tbcNombreFactura;
    @FXML
    private TableColumn<ProFacturasDto, String> tbcPrecioFactura;
    @FXML
    private TableColumn<ProFacturasDto, Boolean> tbcQuitar;
    @FXML
    private Label txtTotal;
    @FXML
    private JFXButton btnPagar;
    @FXML
    private JFXButton btnSalir;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tbcAgregar.setCellValueFactory((TableColumn.CellDataFeatures<ProComidasDto,Boolean >p)-> new SimpleObjectProperty(p.getValue() != null));
        
        tbcAgregar.setCellFactory((TableColumn<ProComidasDto, Boolean> p) -> new ButtonCellComida());
        
        tbcQuitar.setCellValueFactory((TableColumn.CellDataFeatures<ProFacturasDto,Boolean >p)-> new SimpleObjectProperty(p.getValue() != null));
        
        tbcQuitar.setCellFactory((TableColumn<ProFacturasDto, Boolean> p) -> new ButtonCellFactura());
    
         tbcNombre.setCellValueFactory(clbck -> clbck.getValue().comNombre);

        tbcDescripcion.setCellValueFactory(clbck -> clbck.getValue().comDescripcion);

        tbcPrecio.setCellValueFactory(clbck -> clbck.getValue().comPrecio);

        actualizarTbv();

        tbvComidas.refresh();
    }    

    @Override
    public void initialize() {
        
    }
    
    private class ButtonCellComida extends TableCell<ProComidasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellComida() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Agregar");
            cellButton.setStyle("-fx-background-color: #8EF680");
            cellButton.setOnAction((ActionEvent t) -> {
                ProComidasDto com = (ProComidasDto) ButtonCellComida.this.getTableView().getItems().get(ButtonCellComida.this.getIndex());
                tbvComidas.refresh();
                
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
    
    private class ButtonCellFactura extends TableCell<ProFacturasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellFactura() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Eliminar");
            cellButton.setStyle("-fx-background-color: #8EF680");
            cellButton.setOnAction((ActionEvent t) -> {
                ProFacturasDto fac = (ProFacturasDto) ButtonCellFactura.this.getTableView().getItems().get(ButtonCellFactura.this.getIndex());
                
                tbvFactura.getItems().remove(fac);
                tbvFactura.refresh();
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
