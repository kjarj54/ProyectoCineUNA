/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.model.ProComidasDto;
import cr.ac.una.cineuna.model.ProFacturasDto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    }    

    @Override
    public void initialize() {
        
    }
    
    private class ButtonCellComida extends TableCell<ProComidasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellComida() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

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
    
    private class ButtonCellFactura extends TableCell<ProFacturasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellFactura() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");

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
