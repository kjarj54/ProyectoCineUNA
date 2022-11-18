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
import cr.ac.una.cineuna.model.ProPeliculasDto;
import cr.ac.una.cineuna.service.ProPeliculasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.BindingUtils;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Formato;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.compress.utils.IOUtils;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class UpdatePelViewController extends Controller implements Initializable {

    @FXML
    private ImageView imgPel;
    @FXML
    private JFXButton btnCargarImagen;
    @FXML
    private JFXTextField txtNombrePel;
    @FXML
    private JFXTextField txtUrl;
    @FXML
    private RadioButton rdbProximamente;
    @FXML
    private ToggleGroup tggEstado;
    @FXML
    private RadioButton rdbSala;
    @FXML
    private RadioButton rdbInactivo;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private TextArea txtAreaSinopsis;
    @FXML
    private JFXButton btnGuardar;

    List<Node> requeridos = new ArrayList<>();

    ProPeliculasDto proPeliculasDto;
    
    

    @FXML
    private JFXButton btnAtras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdbInactivo.setUserData("I");
        rdbProximamente.setUserData("P");
        rdbSala.setUserData("S");
        txtNombrePel.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtAreaSinopsis.setTextFormatter(Formato.getInstance().maxLengthFormat(500));
        txtUrl.setTextFormatter(Formato.getInstance().maxLengthFormat(255));
        proPeliculasDto = new ProPeliculasDto();
        
        

        Long id = (Long) AppContext.getInstance().get("EditarPelicula");
        ProPeliculasService service = new ProPeliculasService();
        Respuesta respuesta = service.getPelicula(id);
        if (respuesta.getEstado()) {
            unbindPeliculas();
            proPeliculasDto = (ProPeliculasDto) respuesta.getResultado("ProPelicula");
            imgPel.setImage(ConvertToImage(proPeliculasDto.pelImagen.getValue()));
            bindPeliculas(false);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar pelicula", getStage(), respuesta.getMensaje());
        }
        indicarRequeridos();
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombrePel, txtUrl));
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
        txtNombrePel.textProperty().bindBidirectional(proPeliculasDto.pelNombre);
        txtAreaSinopsis.textProperty().bindBidirectional(proPeliculasDto.pelSynopsis);
        txtUrl.textProperty().bindBidirectional(proPeliculasDto.pelLink);
        BindingUtils.bindToggleGroupToProperty(tggEstado, proPeliculasDto.pelEstado);
        dpFecha.valueProperty().bindBidirectional(proPeliculasDto.pelFechaestreno);
    }

    public void unbindPeliculas() {
        txtNombrePel.textProperty().unbindBidirectional(proPeliculasDto.pelNombre);
        txtAreaSinopsis.textProperty().unbindBidirectional(proPeliculasDto.pelSynopsis);
        txtUrl.textProperty().unbindBidirectional(proPeliculasDto.pelLink);
        BindingUtils.unbindToggleGroupToProperty(tggEstado, proPeliculasDto.pelEstado);
        dpFecha.valueProperty().unbindBidirectional(proPeliculasDto.pelFechaestreno);
    }

    public void nuevaPelicula() {
        unbindPeliculas();
        proPeliculasDto = new ProPeliculasDto();
        bindPeliculas(true);
    }

    private Image ConvertToImage(byte[] data) {
        return new Image(new ByteArrayInputStream(data));
    }

    private byte[] SaveImage(File file) throws IOException {//Resive un file del filechoser       
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());//de la ruta lo pasa a un FileInputStream
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);//El fileSream lo convierte a un arreglo de bytes
        return imageInBytes;
    }

    @FXML
    private void onActionCargarImagen(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Busqueda Imagen");

        //Facilita la busqueda escogiendo que aparescan jpg, pgn
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

        //toma la imagen
        File imagFile = fileChooser.showOpenDialog(null);

        //comprueba y luego muestra la imagen
        if (imagFile != null) {

            proPeliculasDto.setPelImagen(SaveImage(imagFile));

            Image image = new Image(new ByteArrayInputStream(SaveImage(imagFile)));//le setea el arreglo de bytes al Image
            imgPel.setImage(image);//Coloca el image en el imageView

        }

    }

    @FXML
    private void onActionGuardar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pelicula", getStage(), invalidos);
            } else {
                ProPeliculasService service = new ProPeliculasService();
                Respuesta respuesta = service.guardarPelicula(proPeliculasDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pelicula", getStage(), respuesta.getMensaje());
                } else {
                    unbindPeliculas();
                    proPeliculasDto = (ProPeliculasDto) respuesta.getResultado("Pelicula");
                    bindPeliculas(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar pelicula", getStage(), "Pelicula actualizada correctamente.");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MantPeliculasViewController.class.getName()).log(Level.SEVERE, "Error guardando la pelicula.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar pelicula", getStage(), "Ocurrio un error guardando la pelicula.");
        }
    }

    private void onActionLimpiar(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar usuario", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevaPelicula();
            imgPel.setImage(null);
        }
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionAtras(ActionEvent event) {
        
       
      FlowController.getInstance().goView("MantPelTableView");
      FlowController.getInstance().limpiarLoader("UpdatePelView");
      FlowController.getInstance().limpiarLoader("MantPelTableView");
      
      
      
        
    }

}
