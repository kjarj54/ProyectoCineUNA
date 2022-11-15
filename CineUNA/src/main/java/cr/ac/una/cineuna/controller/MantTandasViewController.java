/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.model.ProPeliculasDto;
import cr.ac.una.cineuna.model.ProSalasDto;
import cr.ac.una.cineuna.model.ProTandasDto;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.service.ProPeliculasService;
import cr.ac.una.cineuna.service.ProSalasService;
import cr.ac.una.cineuna.service.ProTandasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.BindingUtils;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class MantTandasViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtNombreTanda;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private JFXTimePicker HoraInicio;
    @FXML
    private JFXTimePicker HoraFinal;
    @FXML
    private JFXTextField txtPrecio;
    @FXML
    private Button btnPeli;
    @FXML
    public JFXTextField txtPeli;
    
    List<Node> requeridos = new ArrayList<>();
    
    
    JFXTextField aux = new JFXTextField();
    JFXTextField aux2 = new JFXTextField();
    
    private TablePeliculasViewController menucontroller;
    @FXML
    public TextField txtID;
    
    
    ProTandasDto peli;
    ProSalasDto salas;
    @FXML
    private AnchorPane root;
    @FXML
    private Button btnSala;
    @FXML
    public TextField txtIDSala;
    @FXML
    public JFXTextField txtSala;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        aux.setLayoutX(0);aux.setLayoutY(0);
        root.getChildren().add(aux);
        aux2.setLayoutX(300);aux2.setLayoutY(0);
        root.getChildren().add(aux2);
        aux.setVisible(false);aux2.setVisible(false);
        
        
        AppContext.getInstance().set("MantTandasViewController", this);
        
        peli = new ProTandasDto();
        salas = new ProSalasDto();
        
        indicarRequeridos();
        nuevaTnda();
        
        
        //txtPeli.setEditable(true);
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void onActionSalir(ActionEvent event) {
        aux2.setText(String.valueOf(HoraFinal.getValue()));
    }
    
    public void nuevaTnda() {
        unbindPeliculas();
        peli = new ProTandasDto();
        
        bindPeliculas(true);
        
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        
        //peli.setPelId(Long.valueOf(txtID.getText()));
        String sss = txtID.getText();
        String ssss = txtIDSala.getText();
        aux.setText(String.valueOf(HoraInicio.getValue()));
        aux2.setText(String.valueOf(HoraFinal.getValue()));
        
        try {
            ProPeliculasService service1 = new ProPeliculasService();
            Respuesta respuesta1 = service1.getPelicula(Long.parseLong(sss)); 
            ProPeliculasDto pelicula = new ProPeliculasDto();
            pelicula = (ProPeliculasDto)respuesta1.getResultado("ProPelicula");
            peli.setPelId(pelicula);
            
            
            ProSalasService service2 = new ProSalasService();
            Respuesta respuesta2 = service2.getSalas(Long.parseLong(ssss)); 
            ProSalasDto sala1 = new ProSalasDto();
            sala1 = (ProSalasDto)respuesta2.getResultado("ProSalas");
            peli.setSalId(sala1);
            
            
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tanda", getStage(), invalidos);
            } 
            else {
                ProTandasService service = new ProTandasService();
                Respuesta respuesta = service.guardarPelicula(peli);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tanda", getStage(), respuesta.getMensaje());
                } else {
                    unbindPeliculas();
                    peli = (ProTandasDto) respuesta.getResultado("Tanda");
                    
                    bindPeliculas(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar tanda", getStage(), "TANDA actualizado correctamente.");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MantTandasViewController.class.getName()).log(Level.SEVERE, "Error guardando el tanda.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar tanda", getStage(), "Ocurrio un error guardando el tanda.");
        }

       
        
    }

    @FXML
    private void OnActionbtnPeli(ActionEvent event) {
        FlowController.getInstance().goView("TablePeliculasView");
        //FlowController.getInstance().limpiarLoader("TablePeliculasView");
        
    }
    
    
    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombreTanda));
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
    
    
    public void bindPeliculas(Boolean nuevo) {
        txtNombreTanda.textProperty().bindBidirectional(peli.tanNombre);
        txtPrecio.textProperty().bindBidirectional(peli.tanPrecio);
        dpFecha.valueProperty().bindBidirectional(peli.tanFecha);
        aux.textProperty().bindBidirectional(peli.tanHorainicio);
        aux2.textProperty().bindBidirectional(peli.tanHorafinal);
        
    }

    public void unbindPeliculas() {
        txtNombreTanda.textProperty().unbindBidirectional(peli.tanNombre);
        txtPrecio.textProperty().unbindBidirectional(peli.tanPrecio);
        dpFecha.valueProperty().unbindBidirectional(peli.tanFecha);
        aux.textProperty().unbindBidirectional(peli.tanHorainicio);
        aux2.textProperty().bindBidirectional(peli.tanHorafinal);
    }

    @FXML
    private void OnActionbtnSala(ActionEvent event) {
        FlowController.getInstance().goView("TableSalaView");
    }
    
}
