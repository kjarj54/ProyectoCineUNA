/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import cr.ac.una.cineuna.model.ProAsientosDto;
import cr.ac.una.cineuna.model.ProSalasDto;
import cr.ac.una.cineuna.service.ProAsientosService;
import cr.ac.una.cineuna.service.ProSalasService;
import cr.ac.una.cineuna.util.AppContext;
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
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
    
    

    /**
     * Initializes the controller class.
     */
    
    ProAsientosDto asientodto;
    @FXML
    private ImageView imgFondo;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        asientodto = new ProAsientosDto();
        btnA1.setUserData("I");
        
        
        
        
        ProAsientosService asientos = new ProAsientosService();
        
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "1");
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        
        img1.setImage(ConvertToImage(asientodto.getAsiImg()));
        img2.setImage(ConvertToImage(asientodto.getAsiImg()));
        img3.setImage(ConvertToImage(asientodto.getAsiImg()));
        img4.setImage(ConvertToImage(asientodto.getAsiImg()));
        img5.setImage(ConvertToImage(asientodto.getAsiImg()));
        img6.setImage(ConvertToImage(asientodto.getAsiImg()));
        img7.setImage(ConvertToImage(asientodto.getAsiImg()));
        img8.setImage(ConvertToImage(asientodto.getAsiImg()));
        img9.setImage(ConvertToImage(asientodto.getAsiImg()));
        img10.setImage(ConvertToImage(asientodto.getAsiImg()));
        img11.setImage(ConvertToImage(asientodto.getAsiImg()));
        img12.setImage(ConvertToImage(asientodto.getAsiImg()));
        ProSalasService servicesala = new ProSalasService();
        Respuesta respuestasala= servicesala.getSalas((Long)AppContext.getInstance().get("IMAGENSALA"));
        ProSalasDto salasdto = new ProSalasDto();
        salasdto = (ProSalasDto)respuestasala.getResultado("ProSalas");
        imgFondo.setImage(ConvertToImage(salasdto.getSalImgFondo()));
        
        Respuesta respuesta20 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "1");
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnA1.setStyle("-fx-background-color: #FF0000");
            btnA1.setDisable(true);
            
        }
        asientos = new ProAsientosService();
        Respuesta respuesta1 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "2");
        
        asientodto = (ProAsientosDto)respuesta1.getResultado("ProAsientos");
        
        if(asientodto.getAsiEstado().equals("O")){
            btnA2.setStyle("-fx-background-color: #FF0000");
            btnA2.setDisable(true);
            
        }
        
        Respuesta respuesta2 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "3");
        
        asientodto = (ProAsientosDto)respuesta2.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnA3.setStyle("-fx-background-color: #FF0000");
            btnA3.setDisable(true);
            
        }
        Respuesta respuesta3 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "4");
        
        asientodto = (ProAsientosDto)respuesta3.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnB1.setStyle("-fx-background-color: #FF0000");
            btnB1.setDisable(true);
            
        }
        Respuesta respuesta4 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "5");
        
        asientodto = (ProAsientosDto)respuesta4.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnB2.setStyle("-fx-background-color: #FF0000");
            btnB2.setDisable(true);
            
        }
        Respuesta respuesta5 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "6");
        
        asientodto = (ProAsientosDto)respuesta5.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnB3.setStyle("-fx-background-color: #FF0000");
            btnB3.setDisable(true);
            
        }
        Respuesta respuesta6 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "7");
        
        asientodto = (ProAsientosDto)respuesta6.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnC1.setStyle("-fx-background-color: #FF0000");
            btnC1.setDisable(true);
            
        }
        Respuesta respuesta7 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "8");
        
        asientodto = (ProAsientosDto)respuesta7.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnC2.setStyle("-fx-background-color: #FF0000");
            btnC2.setDisable(true);
            
        }
        Respuesta respuesta8 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "9");
        
        asientodto = (ProAsientosDto)respuesta8.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnC3.setStyle("-fx-background-color: #FF0000");
            btnC3.setDisable(true);
            
        }
        Respuesta respuesta9 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "10");
        
        asientodto = (ProAsientosDto)respuesta9.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnD1.setStyle("-fx-background-color: #FF0000");
            btnD1.setDisable(true);
            
        }
        Respuesta respuesta10 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "11");
        
        asientodto = (ProAsientosDto)respuesta10.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnD2.setStyle("-fx-background-color: #FF0000");
            btnD2.setDisable(true);
            
        }
        Respuesta respuesta101 = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), "12");
        
        asientodto = (ProAsientosDto)respuesta101.getResultado("ProAsientos");
        asientos = new ProAsientosService();
        if(asientodto.getAsiEstado().equals("O")){
            btnD3.setStyle("-fx-background-color: #FF0000");
            btnD3.setDisable(true);
            
        }
        
        
    }    

    @Override
    public void initialize() {
        
    }
    
    private Image ConvertToImage(byte[] data) {
        return new Image(new ByteArrayInputStream(data));
    }

    @FXML
    private void OnActionbtnA1(ActionEvent event) {
        
        btnA1.setUserData("1");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnA1.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnA1.setStyle("-fx-background-color: #FF0000");
            
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnA2(ActionEvent event) {
        btnA2.setUserData("2");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnA2.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnA2.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnA3(ActionEvent event) {
        btnA3.setUserData("3");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnA3.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnA3.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnC1(ActionEvent event) {
        btnC1.setUserData("7");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnC1.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnC1.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnC2(ActionEvent event) {
        btnC2.setUserData("8");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnC2.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnC2.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnC3(ActionEvent event) {
    btnC3.setUserData("9");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnC3.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnC3.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnB1(ActionEvent event) {
        btnB1.setUserData("4");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnB1.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnB1.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnB2(ActionEvent event) {
        btnB2.setUserData("5");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnB2.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnB2.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnB3(ActionEvent event) {
        btnB3.setUserData("6");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnB3.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnB3.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnD1(ActionEvent event) {
        btnD1.setUserData("10");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnD1.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnD1.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnD2(ActionEvent event) {
        btnD2.setUserData("11");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnD2.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnD2.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }

    @FXML
    private void OnActionbtnD3(ActionEvent event) {
        btnD3.setUserData("12");//nombre de los asientos
        ProAsientosService asientos = new ProAsientosService();
        Respuesta respuesta = asientos.getAsientosTanId((Long)AppContext.getInstance().get("IDTANDAPARAASIENTO"), btnD3.getUserData().toString());
        //System.out.println();
        
        
        asientodto = (ProAsientosDto)respuesta.getResultado("ProAsientos");
        asientodto.setAsiEstado("O");
        respuesta = asientos.guardarAsiento(asientodto);
        
        if (new Mensaje().showConfirmation("Confirmación", getStage(), "¿Esta seguro que desea guardar el asiento?")) {
            
            btnD3.setStyle("-fx-background-color: #FF0000");
            
            //System.out.println(btnA1.getUserData().toString());
            
            /*
            asientodto.setAsiEstado("O");
            ProAsientosService service1 = new ProAsientosService();
            service1.guardarAsiento(asientodto);*/
        }
    }
    
    private byte[] SaveImage(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }

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
