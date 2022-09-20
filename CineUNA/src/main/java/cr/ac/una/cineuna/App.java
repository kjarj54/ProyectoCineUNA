package cr.ac.una.cineuna;

import cr.ac.una.cineuna.util.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        //stage.setTitle("UNA CINE");
        stage.getIcons().add(new Image("cr/ac/una/cineuna/resources/icono-pantalla.png"));
        stage.setTitle("UNA CINE");
        FlowController.getInstance().goViewInWindow("LoginView");
    }

   

    public static void main(String[] args) {
        launch();
    }

}