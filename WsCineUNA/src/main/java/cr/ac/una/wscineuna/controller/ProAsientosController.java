/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.ProAsientosDto;
import cr.ac.una.wscineuna.service.ProAsientosService;
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
@Path("/ProAsientosController")
public class ProAsientosController {

    @EJB
    ProAsientosService proAsientosService;

    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }

    @POST
    @Path("/guardarAsiento")
    public Response guardarAsiento(ProAsientosDto proAsientosDto) {
        try {
            Respuesta res = proAsientosService.guardarAsiento(proAsientosDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProAsientosDto) res.getResultado("Asiento")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProAsientosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el asiento").build();
        }
    }

    @GET
    @Secure
    @Path("/asiento/{id}")
    public Response getAsiento(@PathParam("id") Long id) {
        try {
            Respuesta res = proAsientosService.getAsiento(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProAsientosDto proAsientosDto = (ProAsientosDto) res.getResultado("ProAsiento");

            return Response.ok(proAsientosDto).build();

        } catch (Exception ex) {
            Logger.getLogger(ProAsientosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el asiento").build();
        }
    }

    @GET
    @Secure
    @Path("/asientos/")
    public Response getAsientos() {
        try {
            Respuesta res = proAsientosService.getAsientos();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProAsientosDto>>((List<ProAsientosDto>) res.getResultado("ProAsientos")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProAsientosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo asientos").build();
        }
    }

    @DELETE
    @Path("/asientos/{id}")
    public Response eliminarAsiento(@PathParam("id") Long id) {
        try {
            Respuesta res = proAsientosService.eliminarAsiento(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProAsientosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando asiento").build();
        }
    }

}
