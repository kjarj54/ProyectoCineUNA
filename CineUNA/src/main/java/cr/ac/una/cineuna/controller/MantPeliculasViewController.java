
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        menuTanda.getItems().add("S - En Sala");
        menuTanda.getItems().add("I - Inactiva");
        menuTanda.getItems().add("P - Proximamente");
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
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
        
        //toma la imagen
        File imagFile = fileChooser.showOpenDialog(null);
        
        //comprueba y luego muestra la imagen
        if(imagFile != null){
            
          Image image = new Image("file:"+imagFile.getAbsolutePath());
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
