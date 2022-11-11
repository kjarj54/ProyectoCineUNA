/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.ProComidasDto;
import cr.ac.una.wscineuna.service.ProComidasService;
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

@Path("/ProComidasController")
@Secure
public class ProComidasController {
    @EJB
    ProComidasService proComidasService;
    
    @POST
    @Path("/guardarComida")
    public Response guardarComida(ProComidasDto proComidasDto) {
        try {
            Respuesta res = proComidasService.guardarComida(proComidasDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProComidasDto) res.getResultado("Comida")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la comida").build();
        }
    }

    @GET
    @Path("/comida/{id}")
    public Response getComida(@PathParam("id") Long id) {
        try {
            Respuesta res = proComidasService.getComida(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProComidasDto proComidasDto = (ProComidasDto) res.getResultado("ProComidas");

            return Response.ok(proComidasDto).build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la comida").build();
        }
    }

    @GET
    @Path("/comidas/")
    public Response getComidas() {
        try {
            Respuesta res = proComidasService.getComidas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProComidasDto>>((List<ProComidasDto>) res.getResultado("ProComidas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo comida").build();
        }
    }

    @DELETE
    @Path("/eliminarComida/{id}")
    public Response eliminarComida(@PathParam("id") Long id) {
        try {
            Respuesta res = proComidasService.eliminarComida(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando comida").build();
        }
    }
}
