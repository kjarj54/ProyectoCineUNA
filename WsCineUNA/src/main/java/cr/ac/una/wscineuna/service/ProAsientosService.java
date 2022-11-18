/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.ProAsientos;
import cr.ac.una.wscineuna.model.ProAsientosDto;
import cr.ac.una.wscineuna.model.ProClientes;
import cr.ac.una.wscineuna.model.ProClientesDto;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.net.UnknownHostException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class ProAsientosService {

    private static final Logger LOG = Logger.getLogger(ProAsientosService.class.getName());

    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;

    public Respuesta getAsiento(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProAsientos.findByAsiId", ProAsientos.class);
            qryActividad.setParameter("asiId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProAsiento", new ProAsientosDto((ProAsientos) qryActividad.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un asiento con las credenciales ingresadas.", "validarAsiento NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "validarAsiento " + ex.getMessage());
        }
    }

    public Respuesta guardarAsiento(ProAsientosDto proAsientosDto) {
        try {

            ProAsientos proAsientos;
            if (proAsientosDto.getAsiId() != null && proAsientosDto.getAsiId() > 0) {
                proAsientos = em.find(ProAsientos.class, proAsientosDto.getAsiId());
                if (proAsientos == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asiento a modificar.", "guardarAsiento NoResultException");
                }
                proAsientos.actualizarAsiento(proAsientosDto);
                proAsientos = em.merge(proAsientos);
            } else {
                proAsientos = new ProAsientos(proAsientosDto);
                em.persist(proAsientos);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Asiento", new ProAsientosDto(proAsientos));

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el asiento.", "guardarAsiento " + ex.getMessage());
        }
    }

    public Respuesta getAsientos() {
        try {
            
            Query qryAsientos = em.createNamedQuery("ProAsientos.findAll", ProAsientos.class);
            List<ProAsientos> asientos = qryAsientos.getResultList();


            List<ProAsientosDto> asientosDto = new ArrayList<>();
            for (ProAsientos asiento : asientos) {
                asientosDto.add(new ProAsientosDto(asiento));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProAsientos", asientosDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen asientos con los criterios ingresados.", "getAsientos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "getAsientos " + ex.getMessage());
        }
    }
    
     public Respuesta getAsientosTanId(Long tanId, String nombre) {
        try {
            
            Query qryAsientos = em.createNamedQuery("ProAsientos.findAll", ProAsientos.class);
            List<ProAsientos> asientos = qryAsientos.getResultList();

            //asientos = asientos.stream().filter(a-> a.getTanId().getTanId().equals(tanId) && a.getAsiNombre().equals(nombre)).collect(Collectors.toList());
                     
            List<ProAsientosDto> asientosDto = new ArrayList<>();
            
            
            for (ProAsientos asiento : asientos) {
                asientosDto.add(new ProAsientosDto(asiento));
            }
            
            asientosDto =asientosDto.stream().filter(a-> a.getTanId().getTanId().equals(tanId) && a.getAsiNombre().equals(nombre)).collect(Collectors.toList());
            
            ProAsientosDto asientoDto = asientosDto.get(0);
            

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProAsientos", asientoDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen asientos con los criterios ingresados.", "getAsientos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "getAsientos " + ex.getMessage());
        }
    }

    public Respuesta eliminarAsiento(Long id) {
        try {
            
            ProAsientos asientos;
            if (id != null && id > 0) {
                asientos = em.find(ProAsientos.class, id);
                if (asientos == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el asiento a eliminar.", "eliminarAsiento NoResultException");
                }
                em.remove(asientos);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el asiento a eliminar.", "eliminarAsiento NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el asiento porque tiene relaciones con otros registros.", "eliminarAsiento " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el asiento.", "eliminarAsiento " + ex.getMessage());
        }
    }
    
    public Respuesta comprarAsiento(Long id, Long cliId) {
        try {
            Query qryActividad = em.createNamedQuery("ProAsientos.findByAsiId", ProAsientos.class);
            qryActividad.setParameter("asiId", id);
            ProAsientosDto proAsientosDto = new ProAsientosDto((ProAsientos) qryActividad.getSingleResult());
            
            
            Query qryCli = em.createNamedQuery("ProClientes.findByCliId", ProClientes.class);
            qryCli.setParameter("cliId", id);
            ProClientesDto proClientesDto = new ProClientesDto((ProClientes) qryCli.getSingleResult());
            
            correoCompra(proClientesDto, proAsientosDto);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProAsiento", new ProAsientosDto((ProAsientos) qryActividad.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un asiento con las credenciales ingresadas.", "validarAsiento NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el asiento.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el asiento.", "validarAsiento " + ex.getMessage());
        }
    }
    
    public Respuesta correoCompra(ProClientesDto proClientesDto, ProAsientosDto proAsientosDto) {
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
            String mensaje = mensajeEmail(proClientesDto,proAsientosDto);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");

            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "","");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cliente.", "guardarCliente " + ex.getMessage());
        }
    }
    
    
    public String mensajeEmail(ProClientesDto proClientesDto,ProAsientosDto proAsientosDto) {
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
                + "                                      style=\"line-height: 32.4px; font-family: Montserrat, sans-serif; font-size: 18px;\">Factura de asiento: "+ proAsientosDto.getAsiNombre() +"Por el monto de:" + proAsientosDto.tanId.getTanPrecio() +"Del usuario:"+ proClientesDto.getCliNombre() + "</span></span>\n"
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
