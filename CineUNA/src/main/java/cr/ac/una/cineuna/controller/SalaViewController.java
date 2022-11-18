/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.model.ProAsientosDto;
import cr.ac.una.cineuna.service.ProAsientosService;
import cr.ac.una.cineuna.util.Mensaje;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.commons.compress.utils.IOUtils;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class SalaViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button btnA1;
    @FXML
    private ImageView img1;
    @FXML
    private Button btnA2;
    @FXML
    private ImageView img2;
    @FXML
    private Button btnA3;
    @FXML
    private ImageView img3;
    @FXML
    private Button btnC1;
    @FXML
    private ImageView img7;
    @FXML
    private Button btnC2;
    @FXML
    private ImageView img8;
    @FXML
    private Button btnC3;
    @FXML
    private ImageView img9;
    @FXML
    private Button btnB1;
    @FXML
    private ImageView img4;
    @FXML
    private Button btnB2;
    @FXML
    private ImageView img5;
    @FXML
    private Button btnB3;
    @FXML
    private ImageView img6;
    @FXML
    private Button btnD1;
    @FXML
    private ImageView img10;
    @FXML
    private Button btnD2;
    @FXML
    private ImageView img11;
    @FXML
    private Button btnD3;
    @FXML
    private ImageView img12;
    @FXML
    private JFXButton btnImagenAsiento;
    
    

    /**
     * Initializes the controller class.
     */
    
    ProAsientosDto asientodto;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        asientodto = new ProAsientosDto();
        btnA1.setUserData("O");
        
        
        
    }    

    @Override
    public void initialize() {
        
    }

    @FXML
    private void OnActionbtnA1(ActionEvent event) {
        
        btnA1.setUserData("1");
        
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnA1.setStyle("-fx-background-color: #00FFA6");
            btnA1.setOnAction((ActionEvent e)->{
        
        }); 
            System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnA2(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnA3(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnC1(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnC2(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnC3(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnB1(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnB2(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnB3(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnD1(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnD2(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnD3(ActionEvent event) {
    }
    
    private byte[] SaveImage(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }

    @FXML
    private void OnActionbtnImagenAsiento(ActionEvent event) throws IOException {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Busqueda Imagen");

        //Facilita la busqueda escogiendo que aparescan jpg, pgn
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

        //toma la imagen
        File imagFile = fileChooser.showOpenDialog(null);

        //comprueba y luego muestra la imagen
        if (imagFile != null) {

            //proPeliculasDto.setPelImagen(SaveImage(imagFile));

            Image image = new Image(new ByteArrayInputStream(SaveImage(imagFile)));
            img1.setImage(image);img2.setImage(image);img3.setImage(image);img4.setImage(image);
            img5.setImage(image);img6.setImage(image);img7.setImage(image);img8.setImage(image);
            img9.setImage(image);img10.setImage(image);img11.setImage(image);img12.setImage(image);
            
    }
        
        
        
        
    }
    
}
