/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.model.ProComidasDto;
import cr.ac.una.cineuna.model.ProFacturasDto;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.service.ProComidasService;
import cr.ac.una.cineuna.service.ProFacturasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private TableView<ProComidasDto> tbvFactura;
    @FXML
    private TableColumn<ProComidasDto, String> tbcNombreFactura;
    @FXML
    private TableColumn<ProComidasDto, String> tbcPrecioFactura;
    @FXML
    private TableColumn<ProComidasDto, Boolean> tbcQuitar;
    @FXML
    private Label txtTotal;
    @FXML
    private JFXButton btnPagar;
    @FXML
    private JFXButton btnSalir;

    ProFacturasDto proFacturasDto;

    ProComidasDto proComidasDto;
    
    ProClientesDto proClienteDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        tbcAgregar.setCellValueFactory((TableColumn.CellDataFeatures<ProComidasDto, Boolean> p) -> new SimpleObjectProperty(p.getValue() != null));

        tbcAgregar.setCellFactory((TableColumn<ProComidasDto, Boolean> p) -> new ButtonCellComida());

        tbcQuitar.setCellValueFactory((TableColumn.CellDataFeatures<ProComidasDto, Boolean> p) -> new SimpleObjectProperty(p.getValue() != null));

        tbcQuitar.setCellFactory((TableColumn<ProComidasDto, Boolean> p) -> new ButtonCellFactura());

        tbcNombre.setCellValueFactory(clbck -> clbck.getValue().comNombre);

        tbcDescripcion.setCellValueFactory(clbck -> clbck.getValue().comDescripcion);

        tbcPrecio.setCellValueFactory(clbck -> clbck.getValue().comPrecio);

        tbcNombreFactura.setCellValueFactory(clbck -> clbck.getValue().comNombre);

        tbcPrecioFactura.setCellValueFactory(clbck -> clbck.getValue().comPrecio);

        actualizarTbv();

        tbvComidas.refresh();
        
        proFacturasDto = new ProFacturasDto();
        
        proClienteDto = new ProClientesDto();
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void OnActionBtnPagar(ActionEvent event) {
        try {
            ProFacturasService service = new ProFacturasService();
            ProClientesService serviceCli = new ProClientesService();
            
            Long cli = (Long) AppContext.getInstance().get("UsuarioId");
            
            Respuesta respuesta2 = serviceCli.getCliente(cli);
            proClienteDto = (ProClientesDto) respuesta2.getResultado("ProCliente");
            
            String totalString  = txtTotal.getText();
            
            totalString = totalString.replaceAll("\\,00", "");

            Long total = Long.parseLong(totalString);
            
            
            proFacturasDto.setFacTotal(total);
            proFacturasDto.setCliId(proClienteDto);
            
            Respuesta respuesta = service.guardarFactura(proFacturasDto);
            
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Generar la factura", getStage(), respuesta.getMensaje());
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Generar la factura", getStage(), "Factura generada correctamente.");
                tbvFactura.getItems().clear();
            }

        } catch (Exception ex) {
            Logger.getLogger(ComidasClienteViewController.class.getName()).log(Level.SEVERE, "Error generando la factura.");
            new Mensaje().showModal(Alert.AlertType.ERROR, "Generar la factura", getStage(), "Ocurrio un error generando la factura.");
        }

    }

    public void Suma() {
        double total1 = 0d;
        for (int i = 0; i < tbvFactura.getItems().size(); i++) {
            DecimalFormat df = new DecimalFormat("########.00");
            Double valor2 = new Double(tbvFactura.getItems().get(i).getComPrecio().toString().replace(",", "."));
            total1 += valor2;
            txtTotal.setText(String.valueOf(df.format(Double.valueOf(total1))));
        }
    }

    @FXML
    private void OnActionBtnCancelar(ActionEvent event) {
    }

    private class ButtonCellComida extends TableCell<ProComidasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellComida() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Agregar");
            cellButton.setStyle("-fx-background-color: #FFAD5B");
            cellButton.setOnAction((ActionEvent t) -> {
                ProComidasDto com = (ProComidasDto) ButtonCellComida.this.getTableView().getItems().get(ButtonCellComida.this.getIndex());
                com.setModificado(true);
                tbvFactura.getItems().add(com);
                tbvComidas.refresh();
                tbvFactura.refresh();
                proFacturasDto.getComidas().add(com);
                Suma();

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

    public void actualizarTbv() {
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

    private class ButtonCellFactura extends TableCell<ProComidasDto, Boolean> {

        final Button cellButton = new Button();

        ButtonCellFactura() {
            cellButton.setPrefWidth(500);
            cellButton.getStyleClass().add("jfx-btnimg-tbveliminar");
            cellButton.setText("Eliminar");
            cellButton.setStyle("-fx-background-color: #8EF680");
            cellButton.setOnAction((ActionEvent t) -> {
                ProComidasDto fac = (ProComidasDto) ButtonCellFactura.this.getTableView().getItems().get(ButtonCellFactura.this.getIndex());
                proFacturasDto.getComidasEliminadas().add(fac);
                tbvFactura.getItems().remove(fac);
                tbvFactura.refresh();
                Suma();
                if (tbvFactura.getItems().isEmpty()) {
                    txtTotal.setText("0");
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
