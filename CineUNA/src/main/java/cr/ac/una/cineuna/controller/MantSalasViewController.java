
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProAsientosDto;
import cr.ac.una.cineuna.model.ProPeliculasDto;
import cr.ac.una.cineuna.model.ProSalasDto;
import cr.ac.una.cineuna.model.ProTandasDto;
import cr.ac.una.cineuna.service.AsientoService;
import cr.ac.una.cineuna.service.ProAsientosService;
import cr.ac.una.cineuna.service.ProPeliculasService;
import cr.ac.una.cineuna.service.ProSalasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.BindingUtils;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.compress.utils.IOUtils;
/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class MantSalasViewController extends Controller implements Initializable {

    @FXML
    public ImageView imgAsiento;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXTextField txtNombreSala;
    @FXML
    private JFXButton btnAsientoSelect;
    @FXML
    private GridPane gridPaneAsientos;
    @FXML
    private JFXButton btnFondo;
    @FXML
    private ImageView imgFondo;

    /**
     * Initializes the controller class.
     */
    List<Node> requeridos = new ArrayList<>();
    List<AsientoService> asientos = new ArrayList<>();
    
    ProSalasDto proSalasdto;
    @FXML
    private RadioButton rdbInactiva;
    @FXML
    private ToggleGroup tggEstado;
    @FXML
    private RadioButton rdbActiva;
    
    ProAsientosDto asientodto;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        asientodto = new ProAsientosDto();
        rdbInactiva.setUserData("I");
        rdbActiva.setUserData("A");
        proSalasdto = new ProSalasDto();
        nuevaSala();
        indicarRequeridos();
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void onActionSalir(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar usuario", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevaSala();
            imgFondo.setImage(null);
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        
        try {
            
            
            
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar sala", getStage(), invalidos);
            } else {
                ProSalasService service = new ProSalasService();
                Respuesta respuesta = service.guardarSala(proSalasdto);
                
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar sala", getStage(), respuesta.getMensaje());
                } else {
                    unbindSalas();
                    proSalasdto = (ProSalasDto) respuesta.getResultado("Sala");
                    //asientodto = (ProAsientosDto) respuesta.getResultado("Asiento");
                    bindSalas(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar sala", getStage(), "Sala actualizada correctamente.");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(MantPeliculasViewController.class.getName()).log(Level.SEVERE, "Error guardando la sala.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar sala", getStage(), "Ocurrio un error guardando la sala.");
        }
        
    }
    
    private Image ConvertToImage(byte[] data) {
        return new Image(new ByteArrayInputStream(data));
    }

    @FXML
    private void onActionBtnAsientoSelect(ActionEvent event) throws FileNotFoundException, IOException {
        /*FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        Image img = new Image(new FileInputStream(selectedFiles.get(0)));
        if (img.getWidth()>100){
            new Mensaje().showModal(Alert.AlertType.ERROR, "Revisar Dimensiones", (Stage) btnAsientoSelect.getScene().getWindow(), "Las dimensiones de la imagen son muy grandes");
        } else {
        imgAsiento.setImage(img);
        }*/
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Busqueda Imagen");

        //Facilita la busqueda escogiendo que aparescan jpg, pgn
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

        //toma la imagen
        File imagFile = fileChooser.showOpenDialog(null);

        //comprueba y luego muestra la imagen
        if (imagFile != null) {

            asientodto.setAsiImg(SaveImage(imagFile));

            Image image = new Image(new ByteArrayInputStream(SaveImage(imagFile)));
            imgAsiento.setImage(image);

        }
        
        
        
            }


    @FXML
    private void onDragOverImgView(DragEvent event) {
        if(event.getDragboard().hasFiles()){
        event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void onDragDroppedImgView(DragEvent event) throws FileNotFoundException {
        List<File> file = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(file.get(0)));
        imgAsiento.setImage(img);
    }

    @FXML
    private void onDragDetectedImgView(MouseEvent event) {
        Dragboard db = imgAsiento.startDragAndDrop(TransferMode.ANY);
        ClipboardContent cp = new ClipboardContent() ;
        cp.putImage(imgAsiento.getImage());
        db.setContent(cp);
        event.consume();
    }

    @FXML
    private void onDragOverPane(DragEvent event) {
        if(event.getDragboard().hasImage()){
        event.acceptTransferModes(TransferMode.ANY);
        }
    }
    
    public void bindSalas(Boolean nuevo) {
        txtNombreSala.textProperty().bindBidirectional(proSalasdto.salNombre);
        BindingUtils.bindToggleGroupToProperty(tggEstado, proSalasdto.salEstado);
        txtNombreSala.textProperty().bindBidirectional(asientodto.asiNombre);
        
    }

    public void unbindSalas() {
        txtNombreSala.textProperty().unbindBidirectional(proSalasdto.salNombre);
        BindingUtils.unbindToggleGroupToProperty(tggEstado, proSalasdto.salEstado);
        txtNombreSala.textProperty().bindBidirectional(asientodto.asiNombre);
    }

    public void nuevaSala() {
        unbindSalas();
        proSalasdto = new ProSalasDto();
        bindSalas(true);
    }
    
    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombreSala));
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

    @FXML
    private void onDragDroppedPane(DragEvent event) {
        event.getDragboard().getImage();
        gridPaneAsientos.getChildren().addAll(new ImageView(imgAsiento.getImage()));
    }
    
    private byte[] SaveImage(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }

    @FXML
    private void OnActionbtnFondo(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Busqueda Imagen");

        //Facilita la busqueda escogiendo que aparescan jpg, pgn
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

        //toma la imagen
        File imagFile = fileChooser.showOpenDialog(null);

        //comprueba y luego muestra la imagen
        if (imagFile != null) {

            proSalasdto.setSalImgFondo(SaveImage(imagFile));

            Image image = new Image(new ByteArrayInputStream(SaveImage(imagFile)));
            imgFondo.setImage(image);

        }
    }
    
}
