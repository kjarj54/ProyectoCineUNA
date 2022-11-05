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
    public static String generateRandomPassword(int len) {
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    @FXML
    private void OnActionbtnRecuperar(ActionEvent event) {
        int len = 8;
        //System.out.println(generateRandomPassword(len));

        try {
            //setea las propiedades del smtp para poder enviar los emails
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.outlook.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            //proporciona el correo y contrasena del correo con el que va a ser enviado
            String correoRemitente = "CineUna123@outlook.com";
            String passwordRemitente = "cine1234";
            //Optine el correo al cual va a ser enviado el mensaje
            String correoReceptor = txtCorreo.getText();
            String asunto = "CINEUNA";
            //Llama al metodo de creacion de contrasena y se la manda a la persona y luego se la setea para que la cambie
            //String mensaje = "Bienvenid@" + "<br><br>" + "Clave nueva: " + generateRandomPassword(len) + "<br><br>" + "<br><br>Cambio de Contraseña <b> CINEUNA</b><br><br>Att: <b>CineUna</b>";

            String mensaje = "<div role=\"region\" tabindex=\"-1\" aria-label=\"Cuerpo del mensaje\" class=\"ulb23 UxthP GNqVo yxtKT allowTextSelection\">\n"
                    + "	<div>\n"
                    + "		<style type=\"text/css\">\n"
                    + "			<!--\n"
                    + "			.rps_ddee h1 {\n"
                    + "				color: #262626;\n"
                    + "				font-family: \"Enriqueta\", serif;\n"
                    + "				font-size: 30px;\n"
                    + "				line-height: 30px;\n"
                    + "				margin: 0 0 30px\n"
                    + "			}\n"
                    + "\n"
                    + "			.rps_ddee h2 {\n"
                    + "				color: #262626;\n"
                    + "				font-family: \"Enriqueta\", serif;\n"
                    + "				font-size: 18px;\n"
                    + "				font-weight: normal;\n"
                    + "				line-height: 18px;\n"
                    + "				margin: 5px;\n"
                    + "				padding: 5px 0 0\n"
                    + "			}\n"
                    + "\n"
                    + "			.rps_ddee p {\n"
                    + "				color: #333333;\n"
                    + "				font-family: Georgia, serif;\n"
                    + "				font-size: 16px;\n"
                    + "				line-height: 26px;\n"
                    + "				margin: 0 0 26px\n"
                    + "			}\n"
                    + "\n"
                    + "			.rps_ddee table {\n"
                    + "				border-collapse: collapse\n"
                    + "			}\n"
                    + "\n"
                    + "			.rps_ddee th,\n"
                    + "			.rps_ddee td {\n"
                    + "				text-align: left;\n"
                    + "				padding: 8px\n"
                    + "			}\n"
                    + "			-->\n"
                    + "		</style>\n"
                    + "		<div class=\"rps_ddee\">\n"
                    + "			<div>\n"
                    + "				<div style=\"width:600px; height:auto; margin:0px auto; border-radius:4px; border:1px lightgray solid\">\n"
                    + "					<div style=\"height:110px; background-color:#1A1A1A\">\n"
                    + "						<div style=\"top:35%; margin-left:40px\"><span\n"
                    + "								style=\"color:#FFFFFF; font-family:Enriqueta,serif; font-size:30px; font-weight:600\">Solicitud\n"
                    + "								de contraseña</span>\n"
                    + "						</div>\n"
                    + "					</div>\n"
                    + "					<div style=\"height: 200px; background-color: rgb(51, 51, 51) !important; padding: 40px;\"\n"
                    + "						data-ogsb=\"rgb(255, 255, 255)\">\n"
                    + "						<div style=\"height: 100%; border-radius: 10px; border: 1px solid transparent; background-color: rgb(63, 63, 63) !important;\"\n"
                    + "							data-ogsb=\"rgb(229, 229, 229)\">\n"
                    + "							<div style=\"color:#FFFFFF;margin:30px\">Hola por parte de CineUNA se generar una nueva clave!<br aria-hidden=\"true\"><br aria-hidden=\"true\">Su\n"
                    + "								clave nueva es: " + generateRandomPassword(len) + "<br aria-hidden=\\\"true\\\"><br\n"
                    + "									aria-hidden=\\\"true\\\">Atte: cineuna:\n"
                    + "							</div>\n"
                    + "						</div>\n"
                    + "					</div>\n"
                    + "				</div>\n"
                    + "			</div>\n"
                    + "		</div>\n"
                    + "	</div>\n"
                    + "</div>";

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
