/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.ProReservacionDto;
import cr.ac.una.wscineuna.service.ProReservacionService;
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
@Path("/ProReservacionController")
public class ProReservacionController {
    @EJB
    ProReservacionService proReservacionService;
    
    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }
    
    @POST
    @Path("/guardarReservacion")
    public Response guardarReservacion(ProReservacionDto proReservacionDto) {
        try {
            Respuesta res = proReservacionService.guardarReservacion(proReservacionDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProReservacionDto) res.getResultado("Reservacion")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la reservacion").build();
        }
    }

    @GET
    @Secure
    @Path("/reservacion/{id}")
    public Response getReservacion(@PathParam("id") Long id) {
        try {
            Respuesta res = proReservacionService.getReservacion(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProReservacionDto proReservacionDto = (ProReservacionDto) res.getResultado("ProReservacion");

            return Response.ok(proReservacionDto).build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la reservacion").build();
        }
    }

    @GET
    @Secure
    @Path("/reservaciones/")
    public Response getReservacines() {
        try {
            Respuesta res = proReservacionService.getReservaciones();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProReservacionDto>>((List<ProReservacionDto>) res.getResultado("ProReservaicones")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo reservacion").build();
        }
    }

    @DELETE
    @Path("/eliminarReservacion/{id}")
    public Response eliminarReservacion(@PathParam("id") Long id) {
        try {
            Respuesta res = proReservacionService.eliminarReservacion(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando reservacion").build();
        }
    }
}
