/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.ProComidas;
import cr.ac.una.wscineuna.model.ProComidasDto;
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
public class ProComidasService {
    private static final Logger LOG = Logger.getLogger(ProComidasService.class.getName());
    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    
    public Respuesta getComida(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProComidas.findBySalId", ProComidas.class);
            qryActividad.setParameter("comId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProComidas", new ProComidasDto((ProComidas) qryActividad.getSingleResult()));
           
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una comida con las credenciales ingresadas.", "validarComida NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la comida.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la comida.", "validarComida NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la comida.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la comida.", "validarComida " + ex.getMessage());
        }
    }

    public Respuesta guardarComida(ProComidasDto proComidasDto) {
        try {
            
            ProComidas proComidas;
            if (proComidasDto.getComId() != null && proComidasDto.getComId() > 0) {
                proComidas = em.find(ProComidas.class, proComidasDto.getComId());
                if (proComidas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la comida a modificar.", "guardarComida NoResultException");
                }
                proComidas.actualizarComida(proComidasDto);
                proComidas = em.merge(proComidas);
            } else {
                proComidas = new ProComidas(proComidasDto);
                em.persist(proComidas);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Comida", new ProComidasDto(proComidas));

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la comida.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la comida.", "guardarComida" + ex.getMessage());
        }
    }

    public Respuesta getComidas() {
        try {
            
            Query qryComidas = em.createNamedQuery("ProComidas.findAll", ProComidas.class);
            List<ProComidas> comidas = qryComidas.getResultList();


            List<ProComidasDto> comidasDto = new ArrayList<>();
            for (ProComidas comida : comidas) {
                comidasDto.add(new ProComidasDto(comida));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProComidas", comidasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen comidas con los criterios ingresados.", "getComidas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la comida.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la comida.", "getComidas " + ex.getMessage());
        }
    }

    public Respuesta eliminarComida(Long id) {
        try {
            
            ProComidas comidas;
            if (id != null && id > 0) {
                comidas = em.find(ProComidas.class, id);
                if (comidas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la comida a eliminar.", "eliminarComida NoResultException");
                }
                em.remove(comidas);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la comida a eliminar.", "eliminarComida NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la comida porque tiene relaciones con otros registros.", "eliminarComida " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la comida.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la comida.", "eliminarComida " + ex.getMessage());
        }
    }
    
}
