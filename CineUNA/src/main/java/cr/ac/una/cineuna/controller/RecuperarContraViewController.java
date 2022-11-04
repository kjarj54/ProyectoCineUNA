
package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.util.Mensaje;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.security.SecureRandom;
import java.util.Properties;
import javafx.scene.control.Alert;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;




/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class RecuperarContraViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnRecuperar;
    @FXML
    private JFXTextField txtCorreo;
    @FXML
    private AnchorPane PantallaRecuperar;
    @FXML
    private VBox VBoxContainer;
    @FXML
    private JFXButton btnAtras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 

   

    }    

    @Override
    public void initialize() {
        
    }
    
     // Método para generar una contraseña alfanumérica aleatoria de una longitud específica
    public static String generateRandomPassword(int len)
    {
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`
 
        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
 
        return sb.toString();
    }
 
   

    @FXML
    private void OnActionbtnRecuperar(ActionEvent event)  {
        int len = 8;
        //System.out.println(generateRandomPassword(len));
        
            
            
            try {
                Properties props = new Properties();
                props.setProperty("mail.smtp.host", "smtp.outlook.com");
                props.setProperty("mail.smtp.starttls.enable", "true");
                props.setProperty("mail.smtp.port", "587");
                props.setProperty("mail.smtp.auth", "true");
                
                Session session = Session.getDefaultInstance(props);
                
                String correoRemitente = "CineUna123@outlook.com";
                String passwordRemitente = "cine1234";
                String correoReceptor = txtCorreo.getText();
                String asunto = "CINEUNA";
                String mensaje = "Bienvenid@"+"<br><br>"+"Clave nueva: "+generateRandomPassword(len)+"<br><br>"+"http://localhost:8080/WsCineUNA/ws/ProClientesController/activarCuenta?id=4"+"<br><br>Cambio de Contraseña <b> CINEUNA</b><br><br>Att: <b>CineUna</b>";
                //mensaje.replace("[[URL]]", "http://localhost:8080/WsCineUNA/ws/ProClientesController/activarCuenta?id=4");
               
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correoRemitente));
                
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
                message.setSubject(asunto);
                message.setText(mensaje, "ISO-8859-1", "html");
                
                Transport t = session.getTransport("smtp");
                t.connect(correoRemitente, passwordRemitente);
                t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                t.close();
                new Mensaje().show(Alert.AlertType.CONFIRMATION, "Verificación", "El correo ha sido enviado exitosamente");
                
                
            } catch (MessagingException ex) {
                new Mensaje().show(Alert.AlertType.ERROR, "Verificación", "ERROR, intente de nuevo");
            }
        
        
    }
    
    
    @FXML
    private void onActionBtnAtras(ActionEvent event) {
        getStage().close();
    }
    
}
