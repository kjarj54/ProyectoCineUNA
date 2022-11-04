/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import static com.sun.tools.sjavac.options.Option.LOG;
import cr.ac.una.wscineuna.model.ProClientes;
import cr.ac.una.wscineuna.model.ProClientesDto;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
public class ProClientesService {

    private static final Logger LOG = Logger.getLogger(ProClientesService.class.getName());

    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;

    public Respuesta validarUsuario(String usuario, String clave) {
        try {
            Query qryActividad = em.createNamedQuery("ProClientes.findByUsuClave", ProClientes.class);
            qryActividad.setParameter("cliUsuario", usuario);
            qryActividad.setParameter("cliClave", clave);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProCliente", new ProClientesDto((ProClientes) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }

    public Respuesta getCliente(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProClientes.findByCliId", ProClientes.class);
            qryActividad.setParameter("cliId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProCliente", new ProClientesDto((ProClientes) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }

    public Respuesta guardarCliente(ProClientesDto proClientesDto) {
        try {
            ProClientes proClientes;
            if (proClientesDto.getCliId() != null && proClientesDto.getCliId() > 0) {
                proClientes = em.find(ProClientes.class, proClientesDto.getCliId());
                if (proClientes == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el cliente a modificar.", "guardarCliente NoResultException");
                }
                proClientes.actualizarCliente(proClientesDto);
                proClientes = em.merge(proClientes);
            } else {
                proClientes = new ProClientes(proClientesDto);
                em.persist(proClientes);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Cliente", new ProClientesDto(proClientes));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cliente.", "guardarCliente " + ex.getMessage());
        }
    }

    public Respuesta activacionCuenta(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProClientes.findByCliId", ProClientes.class);
            qryActividad.setParameter("cliId", id);
            ProClientesDto proClientesDto = new ProClientesDto((ProClientes) qryActividad.getSingleResult());
            proClientesDto.setCliEstado("A");
            ProClientes proClientes;
            if (proClientesDto.getCliId() != null && proClientesDto.getCliId() > 0) {
                proClientes = em.find(ProClientes.class, proClientesDto.getCliId());
                if (proClientes == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el cliente a modificar.", "guardarCliente NoResultException");
                }
                proClientes.actualizarCliente(proClientesDto);
                proClientes = em.merge(proClientes);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cliente.", "guardarCliente " + ex.getMessage());
        }
    }

    public Respuesta getClientes(String id, String usuario, String nombre, String estado, String admin) {
        try {
            Query qryClientes = em.createNamedQuery("ProClientes.findAll", ProClientes.class);
            List<ProClientes> clientes = qryClientes.getResultList();

            if (!id.equals("%")) {
                clientes = clientes.stream().filter((p) -> p.getCliId().equals(Long.parseLong(id))).collect(Collectors.toList());
            }
            if (!usuario.equals("%")) {
                clientes = clientes.stream().filter((p) -> p.getCliUsuario().contains(usuario.toLowerCase()) || p.getCliUsuario().contains(usuario.toUpperCase())).collect(Collectors.toList());
            }
            if (!nombre.equals("%")) {
                clientes = clientes.stream().filter((p) -> p.getCliNombre().contains(nombre.toLowerCase()) || p.getCliNombre().contains(nombre.toUpperCase())).collect(Collectors.toList());
            }
            if (!estado.equals("%")) {
                clientes = clientes.stream().filter((p) -> p.getCliEstado().contains(estado.toLowerCase()) || p.getCliEstado().contains(estado.toUpperCase())).collect(Collectors.toList());
            }
            if (!admin.equals("%")) {
                clientes = clientes.stream().filter((p) -> p.getCliAdmin().contains(admin.toLowerCase()) || p.getCliAdmin().contains(admin.toUpperCase())).collect(Collectors.toList());
            }

            List<ProClientesDto> clientesDto = new ArrayList<>();
            for (ProClientes cliente : clientes) {
                clientesDto.add(new ProClientesDto(cliente));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProClientes", clientesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen clientes con los criterios ingresados.", "getClientes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el cliente.", "getCliente " + ex.getMessage());
        }
    }

    public Respuesta getClientesSinParametros() {
        try {
            Query qryClientes = em.createNamedQuery("ProClientes.findAll", ProClientes.class);
            List<ProClientes> clientes = qryClientes.getResultList();

            List<ProClientesDto> clientesDto = new ArrayList<>();
            for (ProClientes cliente : clientes) {
                clientesDto.add(new ProClientesDto(cliente));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProClientes", clientesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen clientes con los criterios ingresados.", "getClientes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el cliente.", "getCliente " + ex.getMessage());
        }
    }

    public Respuesta eliminarCliente(Long id) {
        try {
            ProClientes clientes;
            if (id != null && id > 0) {
                clientes = em.find(ProClientes.class, id);
                if (clientes == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el cliente a eliminar.", "eliminarCliento NoResultException");
                }
                em.remove(clientes);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el cliente a eliminar.", "eliminarCliente NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el cliente porque tiene relaciones con otros registros.", "eliminarCliente " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el cliente.", "eliminarCliente " + ex.getMessage());
        }
    }

}
