/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.ProTandasDto;
import cr.ac.una.wscineuna.service.ProTandasService;
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
@Path("/ProTandasController")
@Secure
public class ProTandasController {

    @EJB
    ProTandasService proTandasService;

    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }

    @POST
    @Path("/guardarTanda")
    public Response guardarTanda(ProTandasDto proTandasDto) {
        try {
            Respuesta res = proTandasService.guardarTanda(proTandasDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((ProTandasDto) res.getResultado("Tanda")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la tanda").build();
        }
    }

    @GET
    @Path("/tanda/{id}")
    public Response getPelicula(@PathParam("id") Long id) {
        try {
            Respuesta res = proTandasService.getTandas(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProTandasDto proTandasDto = (ProTandasDto) res.getResultado("ProTanda");

            return Response.ok(proTandasDto).build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la tandas").build();
        }
    }

    @GET
    @Path("/tandas/")
    public Response getTandas() {
        try {
            Respuesta res = proTandasService.getTandas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProTandasDto>>((List<ProTandasDto>) res.getResultado("ProTandas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo tanda").build();
        }
    }

    @DELETE
    @Path("/eliminarTanda/{id}")
    public Response eliminarTanda(@PathParam("id") Long id) {
        try {
            Respuesta res = proTandasService.eliminarTanda(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando tanda").build();
        }
    }
}
