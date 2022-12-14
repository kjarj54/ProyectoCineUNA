/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.ProSalasDto;
import cr.ac.una.wscineuna.service.ProSalasService;
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
@Path("/ProSalasController")
//@Secure
public class ProSalasController {
    @EJB
    ProSalasService proSalasService;
    
    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }
    
    @POST
    @Path("/guardarSala")
    public Response guardarSala(ProSalasDto proSalasDto) {
        try {
            Respuesta res = proSalasService.guardarSala(proSalasDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProSalasDto) res.getResultado("Sala")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la sala").build();
        }
    }

    @GET
    @Path("/sala/{id}")
    public Response getSala(@PathParam("id") Long id) {
        try {
            Respuesta res = proSalasService.getSala(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProSalasDto proPeliculaDto = (ProSalasDto) res.getResultado("ProSalas");

            return Response.ok(proPeliculaDto).build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la sala").build();
        }
    }

    @GET
    @Path("/salas/")
    public Response getSalas() {
        try {
            Respuesta res = proSalasService.getSalas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProSalasDto>>((List<ProSalasDto>) res.getResultado("ProSalas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo salas").build();
        }
    }

    @DELETE
    @Path("/eliminarSalas/{id}")
    public Response eliminarSalas(@PathParam("id") Long id) {
        try {
            Respuesta res = proSalasService.eliminarSala(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando sala").build();
        }
    }
    
    @GET
    @Secure
    @Path("/getSalas/")
    public Response getSalasSinParametros() {
        try {
            Respuesta res = proSalasService.getSalasSinParametros();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProSalasDto>>((List<ProSalasDto>) res.getResultado("ProSalas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo Sala").build();
        }
    }
    
}
