package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.App;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.compress.utils.IOUtils;

/**
 * FXML Controller class
 *
 * @author kevin
 */
public class MantPeliculasViewController extends Controller implements Initializable {

    @FXML
    private ImageView imgPel;
    @FXML
    private JFXButton btnCargarImagen;
    @FXML
    private JFXTextField txtUrl;
    @FXML
    private JFXTextField txtNombrePel;
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
    @FXML
    private JFXButton btnLimpiar;

    List<Node> requeridos = new ArrayList<>();

    ProPeliculasDto proPeliculasDto;
    @FXML
    private JFXButton btnAtras;

    private MantPelTableViewController pel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        pel = (MantPelTableViewController) AppContext.getInstance().get("MantPelTableViewController");
        pel.tbvResultados.refresh();
        rdbInactivo.setUserData("I");
        rdbProximamente.setUserData("P");
        rdbSala.setUserData("S");
        txtNombrePel.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtAreaSinopsis.setTextFormatter(Formato.getInstance().maxLengthFormat(500));
        txtUrl.setTextFormatter(Formato.getInstance().maxLengthFormat(255));
        proPeliculasDto = new ProPeliculasDto();
        nuevaPelicula();

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

    private byte[] SaveImage(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
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

            Image image = new Image(new ByteArrayInputStream(SaveImage(imagFile)));
            imgPel.setImage(image);

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

    @FXML
    private void onActionLimpiar(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar pelicula", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevaPelicula();
            imgPel.setImage(null);
        }
        
    }
    

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnAtras(ActionEvent event) {
        pel.tbvResultados.refresh();
        FlowController.getInstance().limpiarLoader("MantPelTableView");
        FlowController.getInstance().goView("MantPelTableView");
        FlowController.getInstance().limpiarLoader("UpdatePelView");
        FlowController.getInstance().limpiarLoader("MantPelTableView");
        pel.tbvResultados.refresh();
        pel.tbvResultados.getItems();
    }

}
