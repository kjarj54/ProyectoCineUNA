/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProAsientosDto;
import cr.ac.una.cineuna.util.Request;
import cr.ac.una.cineuna.util.Respuesta;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class ProAsientosService {
    
    public Respuesta guardarAsiento(ProAsientosDto proAsientosDto) {
        try {
            //TODO
            Request request = new Request("ProAsientosController/guardarAsiento");
            request.post(proAsientosDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProAsientosDto proAsientos = (ProAsientosDto) request.readEntity(ProAsientosDto.class);
            return new Respuesta(true, "", "", "Asiento", proAsientos);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error guardando el asiento.", ex);
            return new Respuesta(false, "Error guardando el asiento.", "guardarAsiento " + ex.getMessage());
        }
    }
    
    
    public Respuesta getAsiento(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ProAsientosController/asiento", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProAsientosDto proAsientos = (ProAsientosDto) request.readEntity(ProAsientosDto.class);
            return new Respuesta(true, "", "", "ProAsiento", proAsientos);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar el cliente.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el cliente.", "eliminarCliente " + ex.getMessage());
        }
    }
    
    
    public Respuesta getAsientosTanId(Long id, String nombre) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            parametros.put("nombre", nombre);
            Request request = new Request("ProAsientosController/getAsientos", "/{id}/{nombre}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProAsientosDto proAsientos = (ProAsientosDto) request.readEntity(ProAsientosDto.class);
            return new Respuesta(true, "", "", "ProAsientos", proAsientos);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar el asiento.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el asiento.", "getAsientosTanId " + ex.getMessage());
        }
    }
    
    public Respuesta compraAsiento(Long asiId, Long cliId) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("asiId", asiId);
            parametros.put("cliId", cliId);
            Request request = new Request("ProAsientosController/compraAsiento", "/{asiId}/{cliId}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al enviar el asiento.", ex);
            return new Respuesta(false, "Ocurrio un error al enviar el asiento.", "compraAsiento " + ex.getMessage());
        }
    }
    
}
