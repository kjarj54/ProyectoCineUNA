
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
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
/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class MantSalasViewController extends Controller implements Initializable {

    @FXML
    public ImageView imgAsiento;
    @FXML
    private MenuButton menuPelicula;
    @FXML
    private MenuButton menuTanda;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void onActionSalir(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAsientoSelect(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        Image img = new Image(new FileInputStream(selectedFiles.get(0)));
        imgAsiento.setImage(img);
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
        Dragboard db = imgAsiento.startDragAndDrop(TransferMode.COPY);
        ClipboardContent cp = new ClipboardContent() ;
        cp.putImage(imgAsiento.getImage());
        db.setContent(cp);
        event.consume();
    }

    private void onDragOverPane(DragEvent event) {
        if(event.getDragboard().hasImage()){
        event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void onDragDroppedPane(DragEvent event) {
        event.getDragboard().getImage();
        gridPaneAsientos.getChildren().addAll(new ImageView(imgAsiento.getImage()));
    }
    
}
