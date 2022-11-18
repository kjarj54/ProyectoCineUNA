/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.ProAsientos;
import cr.ac.una.wscineuna.model.ProAsientosDto;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
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

}
