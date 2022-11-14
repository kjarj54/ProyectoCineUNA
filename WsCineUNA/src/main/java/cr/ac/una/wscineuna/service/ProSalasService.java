/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.ProPeliculas;
import cr.ac.una.wscineuna.model.ProPeliculasDto;
import cr.ac.una.wscineuna.model.ProSalas;
import cr.ac.una.wscineuna.model.ProSalasDto;
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
public class ProSalasService {
    private static final Logger LOG = Logger.getLogger(ProSalasService.class.getName());
    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getSala(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProSalas.findBySalId", ProSalas.class);
            qryActividad.setParameter("salId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProSalas", new ProSalasDto((ProSalas) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una sala con las credenciales ingresadas.", "validarSala NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la sala.", "validarSala NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la sala.", "validarSala " + ex.getMessage());
        }
    }

    public Respuesta guardarSala(ProSalasDto proSalasDto) {
        try {

            ProSalas proSalas;
            if (proSalasDto.getSalId() != null && proSalasDto.getSalId() > 0) {
                proSalas = em.find(ProSalas.class, proSalasDto.getSalId());
                if (proSalas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la sala a modificar.", "guardarSala NoResultException");
                }
                proSalas.actualizarSala(proSalasDto);
                proSalas = em.merge(proSalas);
            } else {
                proSalas = new ProSalas(proSalasDto);
                em.persist(proSalas);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Sala", new ProSalasDto(proSalas));
            
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la sala.", "guardarSala" + ex.getMessage());
        }
    }

    public Respuesta getSalas() {
        try {
            
            Query qrySalas = em.createNamedQuery("ProSalas.findAll", ProSalas.class);
            List<ProSalas> salas = qrySalas.getResultList();


            List<ProSalasDto> salasDto = new ArrayList<>();
            for (ProSalas sala : salas) {
                salasDto.add(new ProSalasDto(sala));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProSalas", salasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen salas con los criterios ingresados.", "getSalas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la sala.", "getSalas " + ex.getMessage());
        }
    }

    public Respuesta eliminarSala(Long id) {
        try {
            
            ProSalas salas;
            if (id != null && id > 0) {
                salas = em.find(ProSalas.class, id);
                if (salas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la sala a eliminar.", "eliminarSala NoResultException");
                }
                em.remove(salas);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la sala a eliminar.", "eliminarSala NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la sala porque tiene relaciones con otros registros.", "eliminarSala " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la sala.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la sala.", "eliminarSala " + ex.getMessage());
        }
    }
    
}
