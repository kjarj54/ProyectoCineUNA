/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProSalasDto;
import cr.ac.una.cineuna.util.Request;
import cr.ac.una.cineuna.util.Respuesta;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class ProSalasService {
    
    
    public Respuesta guardarSala(ProSalasDto proSalasDto) {
        try {
            //TODO

            Request request = new Request("ProSalasController/guardarSala");
            request.post(proSalasDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProSalasDto proSala = (ProSalasDto) request.readEntity(ProSalasDto.class);
            return new Respuesta(true, "", "", "Sala", proSala);
        } catch (Exception ex) {
            Logger.getLogger(ProSalasService.class.getName()).log(Level.SEVERE, "Error guardando la sala.", ex);
            return new Respuesta(false, "Error guardando la sala.", "guardarSala " + ex.getMessage());
        }
    }
    
}
