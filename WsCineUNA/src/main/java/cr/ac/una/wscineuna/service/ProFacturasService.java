/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.ProComidas;
import cr.ac.una.wscineuna.model.ProComidasDto;
import cr.ac.una.wscineuna.model.ProFacturas;
import cr.ac.una.wscineuna.model.ProFacturasDto;
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
public class ProFacturasService {
    private static final Logger LOG = Logger.getLogger(ProFacturasService.class.getName());
    
    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;
    
    public Respuesta getFactura(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProFacturas.findByFacId", ProFacturas.class);
            qryActividad.setParameter("facId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProFacturas", new ProFacturasDto((ProFacturas) qryActividad.getSingleResult()));
           
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una factura con las credenciales ingresadas.", "validarFactura NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la factura.", "validarFactura NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la factura.", "validarFactura " + ex.getMessage());
        }
    }

    public Respuesta guardarFactura(ProFacturasDto proFacturasDto) {
        try {
            
            ProFacturas proFacturas;
            if (proFacturasDto.getFacId() != null && proFacturasDto.getFacId() > 0) {
                proFacturas = em.find(ProFacturas.class, proFacturasDto.getFacId());
                if (proFacturas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la factura a modificar.", "guardarFactura NoResultException");
                }
                proFacturas.actualizarFatura(proFacturasDto);
                for (ProComidasDto com : proFacturasDto.getComidasEliminadas()) {
                    proFacturas.getProComidasList().remove(new ProComidas(com.getComId()));
                }
                if (!proFacturasDto.getComidas().isEmpty()) {
                    for (ProComidasDto com : proFacturasDto.getComidas()) {
                        if (com.getModificado()) {
                            ProComidas comida = em.find(ProComidas.class, com.getComId());
                            comida.getProFacturasList().add(proFacturas);
                            proFacturas.getProComidasList().add(comida);
                        }
                    }
                }
                proFacturas = em.merge(proFacturas);
            } else {
                proFacturas = new ProFacturas(proFacturasDto);
                em.persist(proFacturas);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Factura", new ProFacturasDto(proFacturas));

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la factura.", "guardarFactura" + ex.getMessage());
        }
    }

    public Respuesta getFacturas() {
        try {
            
            Query qryFacturas = em.createNamedQuery("ProFacturas.findAll", ProFacturas.class);
            List<ProFacturas> facturas = qryFacturas.getResultList();


            List<ProFacturasDto> FacturasDto = new ArrayList<>();
            for (ProFacturas comida : facturas) {
                FacturasDto.add(new ProFacturasDto(comida));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProFacturas", FacturasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen facturas con los criterios ingresados.", "getFacturas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la factura.", "getFacturas " + ex.getMessage());
        }
    }

    public Respuesta eliminarFactura(Long id) {
        try {
            
            ProFacturas facturas;
            if (id != null && id > 0) {
                facturas = em.find(ProFacturas.class, id);
                if (facturas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la factura a eliminar.", "eliminarFactura NoResultException");
                }
                em.remove(facturas);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la factura a eliminar.", "eliminarFacura NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la factura porque tiene relaciones con otros registros.", "eliminarFactura " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la factura.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la factura.", "eliminarFactura " + ex.getMessage());
        }
    }
    
}
