/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.ProAsientos;
import cr.ac.una.wscineuna.model.ProAsientosDto;
import cr.ac.una.wscineuna.model.ProPeliculas;
import cr.ac.una.wscineuna.model.ProTandas;
import cr.ac.una.wscineuna.model.ProTandasDto;
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
public class ProTandasService {

    private static final Logger LOG = Logger.getLogger(ProTandasService.class.getName());

    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;

    public Respuesta getTandas(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProTandas.findByTanId", ProTandas.class);
            qryActividad.setParameter("tanId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProComidas", new ProTandasDto((ProTandas) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una tanda con las credenciales ingresadas.", "validarTanda NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la tanda.", "validarTanda NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la tanda.", "validarTanda " + ex.getMessage());
        }
    }

    public Respuesta guardarTanda(ProTandasDto proTandasDto) {
        try {

            ProTandas proTandas;
            ProPeliculas pelicula ;
            if (proTandasDto.getTanId() != null && proTandasDto.getTanId() > 0) {
                proTandas = em.find(ProTandas.class, proTandasDto.getTanId());
                pelicula = em.find(ProPeliculas.class, proTandasDto.getPelId().getPelId());
                if (proTandas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la tanda a modificar.", "guardarTanda NoResultException");
                }
                proTandas.actualizarTanda(proTandasDto);
                
                

                
                pelicula.getProTandasList().add(proTandas);
                proTandas.setPelId(pelicula);

                proTandas = em.merge(proTandas);
            } else {
                proTandas = new ProTandas(proTandasDto);
                em.persist(proTandas);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Tanda", new ProTandasDto(proTandas));

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la comida.", "guardarTanda" + ex.getMessage());
        }
    }

    public Respuesta getTandas() {
        try {

            Query qryTandas = em.createNamedQuery("ProTandas.findAll", ProTandas.class);
            List<ProTandas> tandas = qryTandas.getResultList();

            List<ProTandasDto> tandasDto = new ArrayList<>();
            for (ProTandas tanda : tandas) {
                tandasDto.add(new ProTandasDto(tanda));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProTandas", tandasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen tandas con los criterios ingresados.", "getTandas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la tanda.", "getTandas " + ex.getMessage());
        }
    }

    public Respuesta eliminarTanda(Long id) {
        try {

            ProTandas tandas;
            if (id != null && id > 0) {
                tandas = em.find(ProTandas.class, id);
                if (tandas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la tanda a eliminar.", "eliminarTanda NoResultException");
                }
                em.remove(tandas);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la tanda a eliminar.", "eliminarTanda NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la tanda porque tiene relaciones con otros registros.", "eliminarTanda " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la tanda.", "eliminarTanda " + ex.getMessage());
        }
    }
    
    public Respuesta getTandasSinParametros(Long id) {
        try {
            Query qryClientes = em.createNamedQuery("ProTandas.findAll", ProTandas.class);
            List<ProTandas> clientes = qryClientes.getResultList();
            
            clientes = clientes.stream().filter(a-> a.getPelId().getPelId().equals(id)).collect(Collectors.toList());

            List<ProTandasDto> clientesDto = new ArrayList<>();
            for (ProTandas cliente : clientes) {
                clientesDto.add(new ProTandasDto(cliente));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProTandas", clientesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen tandas con los criterios ingresados.", "getTandasSinParametros NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el tanda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el tanda.", "getTandasSinParametros " + ex.getMessage());
        }
    }

}
