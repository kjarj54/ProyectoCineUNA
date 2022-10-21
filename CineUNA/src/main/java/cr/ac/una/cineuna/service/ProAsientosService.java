/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProAsientosDto;
import cr.ac.una.cineuna.util.Request;
import cr.ac.una.cineuna.util.Respuesta;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class ProAsientosService {
    
    public Respuesta guardarCliente(ProAsientosDto proAsientosDto) {
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
    
}
