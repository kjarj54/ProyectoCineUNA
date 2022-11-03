package cr.ac.una.cineuna;

import cr.ac.una.cineuna.util.FlowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {   
        Locale localcurrent = Locale.getDefault();
        Locale local = new Locale("es_ES");
        
        ResourceBundle bundle = ResourceBundle.getBundle("cr/ac/una/cineuna/resources/i18n/Bundle", local);
        
        FlowController.getInstance().InitializeFlow(stage, bundle);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/cineuna/resources/icono-pantalla.png")));
        stage.setTitle("UNA CINE");
        FlowController.getInstance().goViewInWindow("LoginView");
    }

   

    public static void main(String[] args) {
        launch();
    }

}