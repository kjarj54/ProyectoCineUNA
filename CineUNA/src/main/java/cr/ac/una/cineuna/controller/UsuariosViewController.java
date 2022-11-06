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
            String correoReceptor = proClientesDto.getCliCorreo();
            String asunto = "CINEUNA";
            //Mensaje que va a ser enviado
            //String mensaje = "Bienvenid@" + "<br><br>" + "<br><br>" + "http://localhost:8080/WsCineUNA/ws/ProClientesController/activarCuenta?id=" + proClientesDto.getCliId() + "<br><br>Activacion de cuenta <b> CINEUNA</b><br><br>Att: <b>CineUna</b>";

             String mensaje = mensajeEmail(proClientesDto);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");

            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();
            new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Verificación", getStage(), "El correo ha sido enviado exitosamente");
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
            } 
            else {
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

    public String mensajeEmail(ProClientesDto proClientesDto) {
        return "<head>\n"
                + "  <!--[if gte mso 9]>\n"
                + "<xml>\n"
                + "  <o:OfficeDocumentSettings>\n"
                + "    <o:AllowPNG/>\n"
                + "    <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                + "  </o:OfficeDocumentSettings>\n"
                + "</xml>\n"
                + "<![endif]-->\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "  <meta name=\"x-apple-disable-message-reformatting\">\n"
                + "  <!--[if !mso]><!-->\n"
                + "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                + "  <!--<![endif]-->\n"
                + "  <title></title>\n"
                + "\n"
                + "  <style type=\"text/css\">\n"
                + "    @media only screen and (min-width: 620px) {\n"
                + "      .u-row {\n"
                + "        width: 600px !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-row .u-col {\n"
                + "        vertical-align: top;\n"
                + "      }\n"
                + "\n"
                + "      .u-row .u-col-100 {\n"
                + "        width: 600px !important;\n"
                + "      }\n"
                + "\n"
                + "    }\n"
                + "\n"
                + "    @media (max-width: 620px) {\n"
                + "      .u-row-container {\n"
                + "        max-width: 100% !important;\n"
                + "        padding-left: 0px !important;\n"
                + "        padding-right: 0px !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-row .u-col {\n"
                + "        min-width: 320px !important;\n"
                + "        max-width: 100% !important;\n"
                + "        display: block !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-row {\n"
                + "        width: calc(100% - 40px) !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-col {\n"
                + "        width: 100% !important;\n"
                + "      }\n"
                + "\n"
                + "      .u-col>div {\n"
                + "        margin: 0 auto;\n"
                + "      }\n"
                + "    }\n"
                + "\n"
                + "    body {\n"
                + "      margin: 0;\n"
                + "      padding: 0;\n"
                + "    }\n"
                + "\n"
                + "    table,\n"
                + "    tr,\n"
                + "    td {\n"
                + "      vertical-align: top;\n"
                + "      border-collapse: collapse;\n"
                + "    }\n"
                + "\n"
                + "    p {\n"
                + "      margin: 0;\n"
                + "    }\n"
                + "\n"
                + "    .ie-container table,\n"
                + "    .mso-container table {\n"
                + "      table-layout: fixed;\n"
                + "    }\n"
                + "\n"
                + "    * {\n"
                + "      line-height: inherit;\n"
                + "    }\n"
                + "\n"
                + "    a[x-apple-data-detectors='true'] {\n"
                + "      color: inherit !important;\n"
                + "      text-decoration: none !important;\n"
                + "    }\n"
                + "\n"
                + "    table,\n"
                + "    td {\n"
                + "      color: #000000;\n"
                + "    }\n"
                + "\n"
                + "    @media (max-width: 480px) {\n"
                + "      #u_content_heading_1 .v-font-size {\n"
                + "        font-size: 33px !important;\n"
                + "      }\n"
                + "\n"
                + "      #u_content_text_1 .v-container-padding-padding {\n"
                + "        padding: 40px 10px 10px !important;\n"
                + "      }\n"
                + "\n"
                + "      #u_content_text_2 .v-container-padding-padding {\n"
                + "        padding: 8px 10px 40px !important;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "\n"
                + "\n"
                + "\n"
                + "  <!--[if !mso]><!-->\n"
                + "  <link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "  <link href=\"https://fonts.googleapis.com/css?family=Pacifico&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "  <link href=\"https://fonts.googleapis.com/css2?family=Anton&display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "  <!--<![endif]-->\n"
                + "\n"
                + "</head>\n"
                + "\n"
                + "<body class=\"clean-body u_body\"\n"
                + "  style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #ffffff;color: #000000\">\n"
                + "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n"
                + "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n"
                + "  <table\n"
                + "    style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\"\n"
                + "    cellpadding=\"0\" cellspacing=\"0\">\n"
                + "    <tbody>\n"
                + "      <tr style=\"vertical-align: top\">\n"
                + "        <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
                + "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #ffffff;\"><![endif]-->\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #2f3031;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #2f3031;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div style=\"height: 100%;width: 100% !important;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n"
                + "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 140%; text-align: center;\"><span\n"
                + "                                    style=\"font-size: 40px; line-height: 56px; font-family: Anton; color: #ecf0f1;\">CineUNA</span>\n"
                + "                                </p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n"
                + "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                                <tr>\n"
                + "                                  <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n"
                + "\n"
                + "                                    <img align=\"center\" border=\"0\" src=\"https://images.unsplash.com/photo-1478720568477-152d9b164e26?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwxMTA5MDN8MHwxfHNlYXJjaHwxfHxjaW5lbWF8ZW58MXx8fHwxNjY3NjE4MzMz&ixlib=rb-4.0.3&q=80&w=1080\"\n"
                + "                                      alt=\"Hero Image\" title=\"Hero Image\"\n"
                + "                                      style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 100%;\"\n"
                + "                                      width=\"100\" />\n"
                + "\n"
                + "                                  </td>\n"
                + "                                </tr>\n"
                + "                              </table>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #2f3031;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: #2f3031;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table id=\"u_content_heading_1\" style=\"font-family:arial,helvetica,sans-serif;\"\n"
                + "                        role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <h1 class=\"v-font-size\"\n"
                + "                                style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Montserrat',sans-serif; font-size: 31px;\">\n"
                + "                                <strong>EMAIL DE ACTIVACION</strong>\n"
                + "                              </h1>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #fbfbfb;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #fbfbfb;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <table id=\"u_content_text_1\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\"\n"
                + "                        cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 35px 10px 40px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <div style=\"color: #000000; line-height: 180%; text-align: left; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"><span\n"
                + "                                    style=\"font-size: 18px; line-height: 32.4px; color: #000000;\"><strong><span\n"
                + "                                        style=\"line-height: 32.4px; font-family: Montserrat, sans-serif; font-size: 18px;\">BIENVENID@</span></strong></span>\n"
                + "                                </p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"> </p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"><span\n"
                + "                                    style=\"font-size: 18px; line-height: 32.4px; color: #000000;\"><span\n"
                + "                                      style=\"line-height: 32.4px; font-family: Montserrat, sans-serif; font-size: 18px;\">Presione el link para activar su cuenta: http://localhost:8080/WsCineUNA/ws/ProClientesController/activarCuenta?id="+proClientesDto.getCliId()+"</span></span>\n"
                + "                                </p>\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"> </p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <table id=\"u_content_text_2\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\"\n"
                + "                        cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:8px 35px 40px 40px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <div style=\"color: #000000; line-height: 180%; text-align: left; word-wrap: break-word;\">\n"
                + "                                <p style=\"font-size: 14px; line-height: 180%;\"> </p>\n"
                + "                                <p style=\"line-height: 180%; font-size: 14px;\"><br /><span\n"
                + "                                    style=\"font-size: 16px; line-height: 28.8px; font-family: Montserrat, sans-serif;\">ATTE:\n"
                + "                                    CINEUNA,</span><br /><span style=\"font-size: 18px; line-height: 32.4px;\"><span\n"
                + "                                      style=\"line-height: 32.4px; font-family: Pacifico, cursive; font-size: 18px;\">CINEUNA</span></span>\n"
                + "                                </p>\n"
                + "                              </div>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\"\n"
                + "                        cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
                + "                        <tbody>\n"
                + "                          <tr>\n"
                + "                            <td class=\"v-container-padding-padding\"\n"
                + "                              style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:arial,helvetica,sans-serif;\"\n"
                + "                              align=\"left\">\n"
                + "\n"
                + "                              <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                + "                                <tr>\n"
                + "                                  <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n"
                + "\n"
                + "                                    <img align=\"center\" border=\"0\" src=\"https://cdn.templates.unlayer.com/assets/1638520170619-12.png\"\n"
                + "                                      alt=\"border\" title=\"border\"\n"
                + "                                      style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 600px;\"\n"
                + "                                      width=\"600\" />\n"
                + "\n"
                + "                                  </td>\n"
                + "                                </tr>\n"
                + "                              </table>\n"
                + "\n"
                + "                            </td>\n"
                + "                          </tr>\n"
                + "                        </tbody>\n"
                + "                      </table>\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #2f2f2f;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image: url('%20');background-repeat: no-repeat;background-position: center top;background-color: #2f2f2f;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "\n"
                + "          <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n"
                + "            <div class=\"u-row\"\n"
                + "              style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n"
                + "              <div\n"
                + "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n"
                + "                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n"
                + "\n"
                + "                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
                + "                <div class=\"u-col u-col-100\"\n"
                + "                  style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n"
                + "                  <div\n"
                + "                    style=\"height: 100%;width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                    <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    <div\n"
                + "                      style=\"height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
                + "                      <!--<![endif]-->\n"
                + "\n"
                + "                      <!--[if (!mso)&(!IE)]><!-->\n"
                + "                    </div>\n"
                + "                    <!--<![endif]-->\n"
                + "                  </div>\n"
                + "                </div>\n"
                + "                <!--[if (mso)|(IE)]></td><![endif]-->\n"
                + "                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n"
                + "              </div>\n"
                + "            </div>\n"
                + "          </div>\n"
                + "\n"
                + "\n"
                + "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n"
                + "        </td>\n"
                + "      </tr>\n"
                + "    </tbody>\n"
                + "  </table>\n"
                + "  <!--[if mso]></div><![endif]-->\n"
                + "  <!--[if IE]></div><![endif]-->\n"
                + "</body>\n"
                + "\n"
                + "</html>";
    }

}
