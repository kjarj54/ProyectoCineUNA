/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.ProReservacion;
import cr.ac.una.wscineuna.model.ProReservacionDto;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ProReservacionService {
    private static final Logger LOG = Logger.getLogger(ProReservacionService.class.getName());
    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getReservacion(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProReservacion.findByResId", ProReservacion.class);
            qryActividad.setParameter("resId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProReservacion", new ProReservacionDto((ProReservacion) qryActividad.getSingleResult()));
           
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una reservacion con las credenciales ingresadas.", "validarReservacion NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la reservacion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la reservacion.", "validarReservacion NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la reservacion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la reservacion.", "validarReservacion " + ex.getMessage());
        }
    }

    public Respuesta guardarReservacion(ProReservacionDto proReservacionDto) {
        try {
            
            ProReservacion proReservacion;
            if (proReservacionDto.getResId() != null && proReservacionDto.getResId() > 0) {
                proReservacion = em.find(ProReservacion.class, proReservacionDto.getResId());
                if (proReservacion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la reservacion a modificar.", "guardarReservacion NoResultException");
                }
                proReservacion.actualizarReservacion(proReservacionDto);
                proReservacion = em.merge(proReservacion);
            } else {
                proReservacion = new ProReservacion(proReservacionDto);
                em.persist(proReservacion);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reservacion", new ProReservacionDto(proReservacion));

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la reservacion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la reservacion.", "guardarReservacion" + ex.getMessage());
        }
    }

    public Respuesta getReservaciones() {
        try {
            
            Query qryReservacion = em.createNamedQuery("ProReservacion.findAll", ProReservacion.class);
            List<ProReservacion> reservaciones = qryReservacion.getResultList();


            List<ProReservacionDto> reservacionDto = new ArrayList<>();
            for (ProReservacion reservacion : reservaciones) {
                reservacionDto.add(new ProReservacionDto(reservacion));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProReservaciones", reservacionDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen reservacion con los criterios ingresados.", "getReservaciones NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la reservacion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la reservacion.", "getReservaciones " + ex.getMessage());
        }
    }

    public Respuesta eliminarReservacion(Long id) {
        try {
            
            ProReservacion proReservacion;
            if (id != null && id > 0) {
                proReservacion = em.find(ProReservacion.class, id);
                if (proReservacion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la reservacion a eliminar.", "eliminarReservacion NoResultException");
                }
                em.remove(proReservacion);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la reservacion a eliminar.", "eliminarReservacion NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la reservacion porque tiene relaciones con otros registros.", "eliminarReservacion " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la reservacion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la reservacion.", "eliminarReservacion " + ex.getMessage());
        }
    }
    
}
