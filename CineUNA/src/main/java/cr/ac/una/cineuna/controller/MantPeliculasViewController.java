package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProPeliculasDto;
import cr.ac.una.cineuna.util.Formato;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.compress.utils.IOUtils;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class MantPeliculasViewController extends Controller implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private JFXButton btnCargarImagen;
    @FXML
    private TextArea txtAreaSinopsis;
    @FXML
    private ImageView imgPel;
    @FXML
    private JFXTextField txtUrlEspa;
    @FXML
    private JFXTextField txtUrlIngles;
    @FXML
    private JFXTextField txtNombrePel;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXComboBox<String> menuTanda;
    @FXML
    private JFXDatePicker dpFecha;

    List<Node> requeridos = new ArrayList<>();
    ProPeliculasDto proPeliculasDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        menuTanda.getItems().add("S - En Sala");
        menuTanda.getItems().add("I - Inactiva");
        menuTanda.getItems().add("P - Proximamente");
        txtNombrePel.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtAreaSinopsis.setTextFormatter(Formato.getInstance().maxLengthFormat(500));
        txtUrlEspa.setTextFormatter(Formato.getInstance().maxLengthFormat(200));
        txtUrlIngles.setTextFormatter(Formato.getInstance().maxLengthFormat(200));
        
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombrePel, txtUrlEspa, txtUrlIngles));
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
        txtUrlEspa.textProperty().bindBidirectional(proPeliculasDto.pelLink);
        txtUrlIngles.textProperty().bindBidirectional(proPeliculasDto.pelLink);
    }

    public void unbindPeliculas() {
        txtNombrePel.textProperty().unbindBidirectional(proPeliculasDto.pelNombre);
        txtAreaSinopsis.textProperty().unbindBidirectional(proPeliculasDto.pelSynopsis);
        txtUrlEspa.textProperty().unbindBidirectional(proPeliculasDto.pelLink);
        txtUrlIngles.textProperty().unbindBidirectional(proPeliculasDto.pelLink);

    }

    public void nuevaPelicula() {
        unbindPeliculas();
        proPeliculasDto = new ProPeliculasDto();
        bindPeliculas(true);
        txtNombrePel.requestFocus();
    }

    private Image ConvertToImage(byte[] data) {
        return new Image(new ByteArrayInputStream(data));
    }

    private byte[] SaveImage(Image imagen) throws IOException {
        System.out.println(imagen);
        String image = imagen.getUrl().substring(6, imagen.getUrl().length());
        System.out.println(image);
        File file = new File(image);
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionCargarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Busqueda Imagen");

        //Facilita la busqueda escogiendo que aparescan jpg, pgn
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

        //toma la imagen
        File imagFile = fileChooser.showOpenDialog(null);

        //comprueba y luego muestra la imagen
        if (imagFile != null) {

            Image image = new Image("file:" + imagFile.getAbsolutePath());
            imgPel.setImage(image);

        }

    }

    @FXML
    private void onActionGuardar(ActionEvent event) {

    }

    @FXML
    private void onActionLimpiar(ActionEvent event) {
    }

}
