
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class MantSalasViewController extends Controller implements Initializable {

    @FXML
    public GridPane gridPaneAsiento;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @Override
    public void initialize() {
        
    }
   
    public void llenarImagen(){
    for (int i = 0; i < gridPaneAsiento.getColumnCount(); i++) {
            for (int j = 0; j < gridPaneAsiento.getRowCount(); i++){
            imgAsiento = new ImageView();
            }
        }
    }

    @FXML
    private void onActionSalir(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
    }
    
}
