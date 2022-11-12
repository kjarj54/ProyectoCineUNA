/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.ProPeliculasDto;
import cr.ac.una.wscineuna.service.ProPeliculasService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.Respuesta;
import cr.ac.una.wscineuna.util.Secure;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Path("/ProPeliculasController")
public class ProPeliculasController {
    @EJB
    ProPeliculasService proPeliculasService;
    
    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }
    
    @POST
    @Path("/guardarPelicula")
    public Response guardarPelicula(ProPeliculasDto proPeliculasDto) {
        try {
            Respuesta res = proPeliculasService.guardarPelicula(proPeliculasDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProPeliculasDto) res.getResultado("Pelicula")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProPeliculasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la pelicula").build();
        }
    }

    @GET
    @Secure
    @Path("/pelicula/{id}")
    public Response getPelicula(@PathParam("id") Long id) {
        try {
            Respuesta res = proPeliculasService.getPelicula(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProPeliculasDto proPeliculaDto = (ProPeliculasDto) res.getResultado("ProPelicula");

            return Response.ok(proPeliculaDto).build();

        } catch (Exception ex) {
            Logger.getLogger(ProPeliculasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la pelicula").build();
        }
    }

    @GET
    @Secure
    @Path("/peliculas/")
    public Response getPeliculas() {
        try {
            Respuesta res = proPeliculasService.getPeliculas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProPeliculasDto>>((List<ProPeliculasDto>) res.getResultado("ProClientes")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProPeliculasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo peliculas").build();
        }
    }

    @DELETE
    @Path("/eliminarPelicula/{id}")
    public Response eliminarPelicula(@PathParam("id") Long id) {
        try {
            Respuesta res = proPeliculasService.eliminarPelicula(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProPeliculasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando pelicula").build();
        }
    }
}
