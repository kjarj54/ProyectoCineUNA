/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.service;

import cr.ac.una.wscineuna.model.ProClientes;
import cr.ac.una.wscineuna.model.ProClientesDto;
import cr.ac.una.wscineuna.model.ProPeliculas;
import cr.ac.una.wscineuna.model.ProPeliculasDto;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Comparator;
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
public class ProPeliculasService {

    private static final Logger LOG = Logger.getLogger(ProPeliculasService.class.getName());

    @PersistenceContext(unitName = "WsCineUNAPU")
    private EntityManager em;

    public Respuesta getPelicula(Long id) {
        try {
            Query qryActividad = em.createNamedQuery("ProPeliculas.findByPelId", ProPeliculas.class);
            qryActividad.setParameter("pelId", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProPelicula", new ProPeliculasDto((ProPeliculas) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe una pelicula con las credenciales ingresadas.", "validarPelicula NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la pelicula.", "validarPelicula NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la pelicula.", "validarPelicula " + ex.getMessage());
        }
    }

    public Respuesta guardarPelicula(ProPeliculasDto proPeliculasDto) {
        try {

            ProPeliculas proPeliculas;
            if (proPeliculasDto.getPelId() != null && proPeliculasDto.getPelId() > 0) {
                proPeliculas = em.find(ProPeliculas.class, proPeliculasDto.getPelId());
                if (proPeliculas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el pelicula a modificar.", "guardarPelicula NoResultException");
                }
                proPeliculas.actualizarPelicula(proPeliculasDto);
                proPeliculas = em.merge(proPeliculas);
            } else {
                proPeliculas = new ProPeliculas(proPeliculasDto);
                em.persist(proPeliculas);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Pelicula", new ProPeliculasDto(proPeliculas));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar la pelicula.", "guardarPelicula " + ex.getMessage());
        }
    }

    public Respuesta getPeliculas(String id, String nombre) {
        try {

            Query qryPeliculas = em.createNamedQuery("ProPeliculas.findAll", ProPeliculas.class);
            List<ProPeliculas> peliculas = qryPeliculas.getResultList();
            if (!id.equals("%")) {
                peliculas = peliculas.stream().filter((p) -> p.getPelId().equals(Long.parseLong(id))).collect(Collectors.toList());
            }
            if (!nombre.equals("%")) {
                peliculas = peliculas.stream().filter((p) -> p.getPelNombre().contains(nombre.toLowerCase()) || p.getPelNombre().contains(nombre.toUpperCase())).collect(Collectors.toList());
            }

            List<ProPeliculasDto> peliculasDto = new ArrayList<>();
            for (ProPeliculas pelicula : peliculas) {
                peliculasDto.add(new ProPeliculasDto(pelicula));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProPeliculasParam", peliculasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen peliculas con los criterios ingresados.", "getPeliculas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la pelicula.", "getPeliculas " + ex.getMessage());
        }
    }

    public Respuesta getPeliculasEstado(String estado) {
        try {

            Query qryPeliculas = em.createNamedQuery("ProPeliculas.findAll", ProPeliculas.class);
            List<ProPeliculas> peliculas = qryPeliculas.getResultList();
            if (!estado.equals("%")) {
                peliculas = peliculas.stream().filter((p) -> p.getPelEstado().contains(estado.toLowerCase()) || p.getPelEstado().contains(estado.toUpperCase())).collect(Collectors.toList());
            }

            if (estado.equals("P")) {
                peliculas = peliculas.stream().sorted(Comparator.comparing(ProPeliculas:: getPelFechaestreno)).collect(Collectors.toList());
            }
            
            List<ProPeliculasDto> peliculasDto = new ArrayList<>();
            for (ProPeliculas pelicula : peliculas) {
                peliculasDto.add(new ProPeliculasDto(pelicula));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProPeliculasEstado", peliculasDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen peliculas con los criterios ingresados.", "getPeliculas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar la pelicula.", "getPeliculas " + ex.getMessage());
        }
    }

    public Respuesta eliminarPelicula(Long id) {
        try {

            ProPeliculas peliculas;
            if (id != null && id > 0) {
                peliculas = em.find(ProPeliculas.class, id);
                if (peliculas == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró la pelicula a eliminar.", "eliminarPelicula NoResultException");
                }
                em.remove(peliculas);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar la pelicula a eliminar.", "eliminarPelicula NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar la pelicula porque tiene relaciones con otros registros.", "eliminarPelicula " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar la pelicula.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar la pelicula.", "eliminarPelicula " + ex.getMessage());
        }
    }

    public Respuesta getPeliculasSinParametros() {
        try {
            Query qryClientes = em.createNamedQuery("ProPeliculas.findAll", ProPeliculas.class);
            List<ProPeliculas> clientes = qryClientes.getResultList();

            List<ProPeliculasDto> clientesDto = new ArrayList<>();
            for (ProPeliculas cliente : clientes) {
                clientesDto.add(new ProPeliculasDto(cliente));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "ProPeliculas", clientesDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen clientes con los criterios ingresados.", "getClientes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el cliente.", "getCliente " + ex.getMessage());
        }
    }
}
