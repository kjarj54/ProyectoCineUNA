/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProFacturasDto;
import cr.ac.una.cineuna.util.Request;
import cr.ac.una.cineuna.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class ProFacturasService {
    public Respuesta eliminarFactura(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ProComidasController/eliminarFactura", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ProComidasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar la factura.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la factura.", "eliminarFactura " + ex.getMessage());
        }
    }

    public Respuesta guardarFactura(ProFacturasDto proFacturasDto) {
        try {
            //TODO
            Request request = new Request("ProComidasController/guardarFactura");
            request.post(proFacturasDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProFacturasDto proFactura = (ProFacturasDto) request.readEntity(ProFacturasDto.class);
            return new Respuesta(true, "", "", "Factura", proFactura);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error guardando la factura.", ex);
            return new Respuesta(false, "Error guardando la factura.", "guardarFacutra " + ex.getMessage());
        }
    }

    public Respuesta getFactrura() {
        try {

            Request request = new Request("ProComidasController/facturas");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<ProFacturasDto> proComidasDto = (List<ProFacturasDto>) request.readEntity(new GenericType<List<ProFacturasDto>>() {
            });
            return new Respuesta(true, "", "", "ProFacturas", proComidasDto);

        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error consultando las comidas.", ex);
            return new Respuesta(false, "Error consultando las comidas.", "getFacturas" + ex.getMessage());
        }
    }
}
