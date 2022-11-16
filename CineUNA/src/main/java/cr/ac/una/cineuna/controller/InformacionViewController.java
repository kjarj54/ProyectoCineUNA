/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.cineuna.model.ProPeliculasDto;
import cr.ac.una.cineuna.service.ProPeliculasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.BindingUtils;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class InformacionViewController extends Controller implements Initializable {

    @FXML
    private ImageView imgPel;
    @FXML
    private WebView webView;
    @FXML
    private Label txtNombrePel;
    @FXML
    private Hyperlink txtUrl;
    @FXML
    private JFXDatePicker dpFecha;
    @FXML
    private TextArea txtAreaSinopsis;
    @FXML
    private JFXButton btnAtras;

    ProPeliculasDto proPeliculasDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        proPeliculasDto = new ProPeliculasDto();

        Long id = (Long) AppContext.getInstance().get("InfoPelicula");
        ProPeliculasService service = new ProPeliculasService();
        Respuesta respuesta = service.getPelicula(id);
        if (respuesta.getEstado()) {

            proPeliculasDto = (ProPeliculasDto) respuesta.getResultado("ProPelicula");

            String linkPel = proPeliculasDto.getPelLink();

            txtUrl.setText(linkPel);

            String cambio = "embed/";

            linkPel = linkPel.replace("watch?=", cambio);

            webView.getEngine().load(linkPel);
            
            txtNombrePel.setText(proPeliculasDto.getPelNombre());

            dpFecha.setValue(proPeliculasDto.getPelFechaestreno());
            dpFecha.setEditable(false);
            dpFecha.setPrefWidth(150);
            dpFecha.setOnMouseClicked(e -> {
                if (!dpFecha.isEditable()) {
                    dpFecha.hide();
                }
            });
            
            txtAreaSinopsis.setText(proPeliculasDto.getPelSynopsis());
            txtAreaSinopsis.setEditable(false);

            imgPel.setImage(ConvertToImage(proPeliculasDto.pelImagen.getValue()));

        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar pelicula", getStage(), respuesta.getMensaje());
        }

    }

    private Image ConvertToImage(byte[] data) {
        return new Image(new ByteArrayInputStream(data));
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAtras(ActionEvent event) {
        FlowController.getInstance().limpiarLoader("CarteleraView");
        FlowController.getInstance().goView("CarteleraView");
        FlowController.getInstance().limpiarLoader("InformacionView");
    }

}
