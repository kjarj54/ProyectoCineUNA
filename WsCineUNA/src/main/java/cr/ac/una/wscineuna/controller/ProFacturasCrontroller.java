/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.ProFacturasDto;
import cr.ac.una.wscineuna.service.ProFacturasService;
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
@Path("/ProFacturasController")
public class ProFacturasCrontroller {

    @EJB
    ProFacturasService proFacturasServices;

    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }

    @POST
    @Path("/guardarFactura")
    public Response guardarFactura(ProFacturasDto proFacturasDto) {
        try {
            Respuesta res = proFacturasServices.guardarFactura(proFacturasDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProFacturasDto proFacturasDto1 = (ProFacturasDto) res.getResultado("Factura");
            proFacturasDto1.setComidas(proFacturasDto.getComidas());
            proFacturasDto1.setCliId(proFacturasDto.getCliId());
            res = proFacturasServices.guardarFactura(proFacturasDto1);
            
            return Response.ok((ProFacturasDto) res.getResultado("Factura")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando la factura").build();
        }
    }

    @GET
    @Path("/factura/{id}")
    public Response getFactura(@PathParam("id") Long id) {
        try {
            Respuesta res = proFacturasServices.getFactura(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProFacturasDto proFacturasDto = (ProFacturasDto) res.getResultado("ProFactura");

            return Response.ok(proFacturasDto).build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo la factura").build();
        }
    }

    @GET
    @Path("/facturas/")
    public Response getFacturas() {
        try {
            Respuesta res = proFacturasServices.getFacturas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProFacturasDto>>((List<ProFacturasDto>) res.getResultado("ProFacturas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo facturas").build();
        }
    }

    @DELETE
    @Path("/eliminarFactura/{id}")
    public Response eliminarFactura(@PathParam("id") Long id) {
        try {
            Respuesta res = proFacturasServices.eliminarFactura(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProSalasController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando factura").build();
        }
    }
}
