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
import cr.ac.una.cineuna.util.FlowController;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class EstrenosViewController extends Controller implements Initializable {

    @FXML
    private VBox vbox;

    ListView<ProPeliculasDto> listView = new ListView<ProPeliculasDto>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        ProPeliculasService proPeliculasService = new ProPeliculasService();
        Respuesta respuesta = proPeliculasService.getPeliculasEstado("P");

        if (respuesta.getEstado()) {

            ObservableList<ProPeliculasDto> proPeliculasDtos = FXCollections.observableList((List<ProPeliculasDto>) respuesta.getResultado("ProPeliculasEstado"));
            listView.setItems(proPeliculasDtos);


            listView.setCellFactory(new Callback<ListView<ProPeliculasDto>, ListCell<ProPeliculasDto>>() {// se setea un call back para anyadir el boton y las demas coasas 
                @Override
                public ListCell<ProPeliculasDto> call(ListView<ProPeliculasDto> p) {
                    return new Celda(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            });

            vbox.getChildren().add(listView);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Consulta peliculas", getStage(), respuesta.getMensaje());
        }

    }

    private Image ConvertToImage(byte[] data) {
        return new Image(new ByteArrayInputStream(data));// convierte el arreglo de byte a un Image
    }

    private class Celda extends ListCell<ProPeliculasDto> {
        
        
        //Creacion de las cosas que se necesitan
        final JFXButton cellButton = new JFXButton();
        private Label nomPel = new Label();
        private JFXDatePicker dpFecha= new JFXDatePicker();
        private ImageView imgView = new ImageView();
        HBox hbox = new HBox();
        
        
        Celda() {
            //Hbox
            hbox.getChildren().addAll(imgView ,nomPel,dpFecha,cellButton);// Se agregan los componentes al hbox para poder mostrarlos
            hbox.setSpacing(20);
            
            //Button
            cellButton.setPrefWidth(50);
            cellButton.setText("Info");
            cellButton.setOnAction((ActionEvent t) -> {
                ProPeliculasDto emp = (ProPeliculasDto) Celda.this.getListView().getItems().get(Celda.this.getIndex());
                AppContext.getInstance().set("InfoPelicula", emp.getPelId());;
                FlowController.getInstance().goView("InfoPeliculasView");
                
            });
        }

        @Override
        protected void updateItem(ProPeliculasDto t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                
                //Image view
                imgView.setImage(ConvertToImage(t.getPelImagen()));//convierte la imagen de los datos que tiene la clase pelicula
                imgView.setFitHeight(80);
                imgView.setFitWidth(100);
                
                //Label
                nomPel.setText(t.getPelNombre());
                
                
                //Date picker
                dpFecha.setValue(t.getPelFechaestreno());
                dpFecha.setEditable(false);
                dpFecha.setPrefWidth(150);
                dpFecha.setOnMouseClicked(e->{
                    if(!dpFecha.isEditable()){
                        dpFecha.hide();
                    }
                });
                
                //Componente a mostrar
                setGraphic(hbox);//se muestra el contenedor de componentes
            }
        }
    }


    @Override
    public void initialize() {
        
    }
    
}
