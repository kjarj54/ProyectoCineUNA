/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import cr.ac.una.cineuna.model.ProPeliculasDto;
import cr.ac.una.cineuna.model.ProSalasDto;
import cr.ac.una.cineuna.model.ProTandasDto;
import cr.ac.una.cineuna.service.ProTandasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */



public class TableTandasViewController extends Controller implements Initializable {

    ProTandasDto ss = new ProTandasDto();
    
    
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<ProTandasDto> tbvResultados;
    @FXML
    private TableColumn<ProTandasDto, String> tbcID;
    @FXML
    private TableColumn<ProTandasDto, String> tbcHoraInicio;
    @FXML
    private TableColumn<ProTandasDto, String> tbcHoraFinal;
    @FXML
    private TableColumn<ProTandasDto, LocalDate> tbcFecha;
    @FXML
    private TableColumn<ProTandasDto, String> tbcPrecio;
    @FXML
    private TableColumn<ProTandasDto, String> tbcPelicula;
    @FXML
    private TableColumn<ProTandasDto, String> tbcSala;
    @FXML
    private TableColumn<ProTandasDto, Boolean> tbcSelec;

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tbcID.setCellValueFactory(clbck -> clbck.getValue().tanId);
        
        tbcHoraInicio.setCellValueFactory(clbck -> clbck.getValue().tanHorainicio);

        tbcHoraFinal.setCellValueFactory(clbck -> clbck.getValue().tanHorafinal);

        tbcFecha.setCellValueFactory(clbck -> clbck.getValue().tanFecha);
        
        tbcPelicula.setCellValueFactory(clbck -> clbck.getValue().pelId.pelNombre);
        
        
        
        tbcSala.setCellValueFactory(clbck -> clbck.getValue().salId.salNombre);
        
        
        

        tbcPrecio.setCellValueFactory(clbck -> clbck.getValue().tanPrecio);
        
        ProTandasService proPeliService = new ProTandasService();
        Long id = (Long) AppContext.getInstance().get("ComparaBoleto");
        Respuesta respuesta = proPeliService.getTandasSinParametros(id);

            if (respuesta.getEstado()) {
                ObservableList<ProTandasDto> proClientesDtos = FXCollections.observableList((List<ProTandasDto>) respuesta.getResultado("ProTandas"));
                tbvResultados.setItems(proClientesDtos);
                tbvResultados.refresh();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta peli", getStage(), respuesta.getMensaje());
            }
        
        
        tbvResultados.refresh();
        
        
        tbcSelec.setCellValueFactory((TableColumn.CellDataFeatures<ProTandasDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        //Anyade el checkbox a la columna
        tbcSelec.setCellFactory((TableColumn<ProTandasDto, Boolean> p) -> new TableTandasViewController.ButtonCell());
    }    
    
    
    
    private class ButtonCell extends TableCell<ProTandasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Aceptar");
            cellButton.setStyle("-fx-background-color: #00FFA6");
            
            cellButton.setOnAction((ActionEvent t) -> {
                ProTandasDto emp = (ProTandasDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                
                
                tbvResultados.refresh();
                //FlowController.getInstance().goView("MantTandasView");
                
            });
        }
    }

    @Override
    public void initialize() {
        
    }

    @FXML
    private void OnMousePressedTbvResultados(MouseEvent event) {
    }
    
}
