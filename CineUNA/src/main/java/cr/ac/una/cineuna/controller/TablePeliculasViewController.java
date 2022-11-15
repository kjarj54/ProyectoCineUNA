/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.model.ProPeliculasDto;
import cr.ac.una.cineuna.service.ProPeliculasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class TablePeliculasViewController extends Controller  implements Initializable {

    @FXML
    private TableView<ProPeliculasDto> tbvResultados;
    @FXML
    private TableColumn<ProPeliculasDto, String> tbcNombre;
    @FXML
    private TableColumn<ProPeliculasDto, String> tbcSynop;
    @FXML
    private TableColumn<ProPeliculasDto, LocalDate> tbcFecha;
    @FXML
    private TableColumn<ProPeliculasDto, Boolean> tbcSelec;
    
    public TextField aux = new TextField();
    public TextField aux2 = new TextField();
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnatras;
    
    private MantTandasViewController menucontroller;
    @FXML
    private TableColumn<ProPeliculasDto, String> tbcID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        aux.setLayoutX(0);
        aux.setLayoutY(0);
        
        
        aux.setVisible(false);
        
       
        root.getChildren().add(aux);
        aux2.setLayoutX(0);
        aux2.setLayoutY(0);
        
        
        aux2.setVisible(false);
        
       
        root.getChildren().add(aux2);
        
        menucontroller = (MantTandasViewController) AppContext.getInstance().get("MantTandasViewController");
        
        
        tbcID.setCellValueFactory(clbck -> clbck.getValue().pelId);
        
        tbcNombre.setCellValueFactory(clbck -> clbck.getValue().pelNombre);

        tbcSynop.setCellValueFactory(clbck -> clbck.getValue().pelSynopsis);

        tbcFecha.setCellValueFactory(clbck -> clbck.getValue().pelFechaestreno);
        
        ProPeliculasService proPeliService = new ProPeliculasService();
        
        Respuesta respuesta = proPeliService.getPeliculasSinParametros();

            if (respuesta.getEstado()) {
                ObservableList<ProPeliculasDto> proClientesDtos = FXCollections.observableList((List<ProPeliculasDto>) respuesta.getResultado("ProPeliculas"));
                tbvResultados.setItems(proClientesDtos);
                tbvResultados.refresh();
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta peli", getStage(), respuesta.getMensaje());
            }
        
        
        tbvResultados.refresh();

        
        

        tbcSelec.setCellValueFactory((TableColumn.CellDataFeatures<ProPeliculasDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        //Anyade el checkbox a la columna
        tbcSelec.setCellFactory((TableColumn<ProPeliculasDto, Boolean> p) -> new TablePeliculasViewController.ButtonCell());
    }    

    @FXML
    private void OnMousePressedTbvResultados(MouseEvent event) {
    }

    @Override
    public void initialize() {
          
    }

    @FXML
    private void OnActionbtnatras(ActionEvent event) {
        //FlowController.getInstance().limpiarLoader("MantTandasView");
        FlowController.getInstance().goView("MantTandasView");
        
        
    }
    
    private class ButtonCell extends TableCell<ProPeliculasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Aceptar");
            cellButton.setStyle("-fx-background-color: #00FFA6");
            
            cellButton.setOnAction((ActionEvent t) -> {
                ProPeliculasDto emp = (ProPeliculasDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                String equipos1 = emp.getPelNombre();
                Long equipos2 = emp.getPelId();
                
                aux.setText(equipos1);
                aux2.setText(String.valueOf(equipos2));
                

                System.out.println(equipos1 + "     " );
                menucontroller.txtPeli.setText(aux.getText());
                menucontroller.txtID.setText(aux2.getText());
                //FlowController.getInstance().goView("MantTandasTableView");
                
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
