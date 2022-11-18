/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import cr.ac.una.cineuna.model.ProSalasDto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import cr.ac.una.cineuna.service.ProSalasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class TableSalaViewController extends Controller implements Initializable {

    @FXML
    private TableView<ProSalasDto> tbvResultados;
    @FXML
    private TableColumn<ProSalasDto, String> tbcID;
    @FXML
    private TableColumn<ProSalasDto, String> tbcNombre;
    @FXML
    private TableColumn<ProSalasDto, String> tbcEstado;
    @FXML
    private TableColumn<ProSalasDto, Boolean> tbcSelec;

    /**
     * Initializes the controller class.
     */
    
    private MantTandasViewController menucontroller;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        menucontroller = (MantTandasViewController) AppContext.getInstance().get("MantTandasViewController");
        tbcID.setCellValueFactory(clbck -> clbck.getValue().salId);
        
        tbcNombre.setCellValueFactory(clbck -> clbck.getValue().salNombre);

        tbcEstado.setCellValueFactory(clbck -> clbck.getValue().salEstado);
        
        
        ProSalasService proPeliService = new ProSalasService();
        
        Respuesta respuesta = proPeliService.getSalasSinParametros();

            if (respuesta.getEstado()) {
                ObservableList<ProSalasDto> proClientesDtos = FXCollections.observableList((List<ProSalasDto>) respuesta.getResultado("ProSalas"));
                tbvResultados.setItems(proClientesDtos);
                tbvResultados.refresh();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta sala", getStage(), respuesta.getMensaje());
            }
        
        
        tbvResultados.refresh();

        
        
        tbcSelec.setCellValueFactory((TableColumn.CellDataFeatures<ProSalasDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        //Anyade el checkbox a la columna
        tbcSelec.setCellFactory((TableColumn<ProSalasDto, Boolean> p) -> new TableSalaViewController.ButtonCell());
        
    }    
    
    
    private class ButtonCell extends TableCell<ProSalasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Aceptar");
            cellButton.setStyle("-fx-background-color: #00FFA6");
            
            cellButton.setOnAction((ActionEvent t) -> {
                ProSalasDto emp = (ProSalasDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                String equipos1 = emp.getSalNombre();
                Long equipos2 = emp.getSalId();
                
                
                menucontroller.txtSala.setText(equipos1);
                menucontroller.txtIDSala.setText(String.valueOf(equipos2));
                //FlowController.getInstance().goView("MantTandasTableView");
                
                tbvResultados.refresh();
                FlowController.getInstance().goView("MantTandasView");
                
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

    @Override
    public void initialize() {
        
    }

    @FXML
    private void OnMousePressedTbvResultados(MouseEvent event) {
    }
    
}
