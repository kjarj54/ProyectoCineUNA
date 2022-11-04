package cr.ac.una.cineuna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.service.ProClientesService;
import cr.ac.una.cineuna.util.BindingUtils;
import cr.ac.una.cineuna.util.Formato;
import cr.ac.una.cineuna.util.Mensaje;
import cr.ac.una.cineuna.util.Respuesta;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class UsuariosViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane FondoUsuarios;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtUsuario;
    @FXML
    private JFXTextField txtCorreo;

    List<Node> requeridos = new ArrayList<>();
    ProClientesDto proClientesDto;
    @FXML
    private RadioButton rdbEspañol;
    @FXML
    private ToggleGroup tggIdioma;
    @FXML
    private RadioButton rdbEnglish;
    @FXML
    private JFXButton btnRegistrar;
    @FXML
    private JFXTextField txtPApellido;
    @FXML
    private JFXTextField txtSApellido;
    @FXML
    private JFXPasswordField txtContra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rdbEnglish.setUserData("I");
        rdbEspañol.setUserData("E");
        txtNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPApellido.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtSApellido.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtUsuario.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        txtContra.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txtCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(100));
        proClientesDto = new ProClientesDto();
        nuevoCliente();
        indicarRequeridos();
    }

    @Override
    public void initialize() {

    }

    public void enviarCorreo() {
        //provide recipient's email ID
        String to = "diegocj92@gmail.com";
        //provide sender's email ID
        String from = "cineuna.smtp@gmail.com";
        //provide Mailtrap's username
        final String username = "cineuna.smtp@gmail.com";
        //provide Mailtrap's password
        final String password = "smtp.Cineuna";
        //provide Mailtrap's host address
        String host = "smtp.gmail.com";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            //set email subject field
            message.setSubject("Here comes Jakarta Mail!");
            //set the content of the email message
            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombre, txtPApellido, txtContra, txtCorreo, txtUsuario));
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

    public void bindClientes(Boolean nuevo) {
        txtNombre.textProperty().bindBidirectional(proClientesDto.cliNombre);
        txtPApellido.textProperty().bindBidirectional(proClientesDto.cliPApellido);
        txtSApellido.textProperty().bindBidirectional(proClientesDto.cliSApellido);
        txtContra.textProperty().bindBidirectional(proClientesDto.cliClave);
        txtCorreo.textProperty().bindBidirectional(proClientesDto.cliCorreo);
        txtUsuario.textProperty().bindBidirectional(proClientesDto.cliUsuario);
        BindingUtils.bindToggleGroupToProperty(tggIdioma, proClientesDto.cliIdioma);
    }

    public void unbindClientes() {
        txtNombre.textProperty().unbindBidirectional(proClientesDto.cliNombre);
        txtPApellido.textProperty().unbindBidirectional(proClientesDto.cliPApellido);
        txtSApellido.textProperty().unbindBidirectional(proClientesDto.cliSApellido);
        txtContra.textProperty().unbindBidirectional(proClientesDto.cliClave);
        txtCorreo.textProperty().unbindBidirectional(proClientesDto.cliCorreo);
        txtUsuario.textProperty().unbindBidirectional(proClientesDto.cliUsuario);
        BindingUtils.unbindToggleGroupToProperty(tggIdioma, proClientesDto.cliIdioma);
    }

    public void nuevoCliente() {
        unbindClientes();
        proClientesDto = new ProClientesDto();
        bindClientes(true);
        txtUsuario.requestFocus();
    }

    public void correoActivacion(ProClientesDto proClientesDto) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.outlook.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            String correoRemitente = "CineUna123@outlook.com";
            String passwordRemitente = "cine1234";
            String correoReceptor = proClientesDto.getCliCorreo();
            String asunto = "CINEUNA";
            String mensaje = "Bienvenid@" + "<br><br>" + "<br><br>" + "http://localhost:8080/WsCineUNA/ws/ProClientesController/activarCuenta?id=" + proClientesDto.getCliId() + "<br><br>Activacion de cuenta <b> CINEUNA</b><br><br>Att: <b>CineUna</b>";

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");

            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();
        } catch (Exception ex) {
            Logger.getLogger(UsuariosViewController.class.getName()).log(Level.SEVERE, "Error enviando el correo.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Enviar Correo", getStage(), "Ocurrio un error enviando el correo.");
        }
    }

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar usuario", getStage(), invalidos);
            } else {
                ProClientesService service = new ProClientesService();
                Respuesta respuesta = service.guardarCliente(proClientesDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar usuario", getStage(), respuesta.getMensaje());
                } else {
                    unbindClientes();
                    proClientesDto = (ProClientesDto) respuesta.getResultado("Cliente");
                    bindClientes(false);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar usuario", getStage(), "Usuario actualizado correctamente.");
                    correoActivacion(proClientesDto);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuariosViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar usuario", getStage(), "Ocurrio un error guardando el usuario.");
        }

    }

    @FXML
    private void onActionBtnLimpiar(ActionEvent event) {
        if (new Mensaje().showConfirmation("Limpiar usuario", getStage(), "¿Esta seguro que desea limpiar el registro?")) {
            nuevoCliente();
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        getStage().close();
    }
}
