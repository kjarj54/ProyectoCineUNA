/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProTandasDto;
import cr.ac.una.cineuna.util.Request;
import cr.ac.una.cineuna.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class ProTandasService {
    
    
    public Respuesta guardarPelicula(ProTandasDto proTandasDto) {
        try {
            //TODO

            Request request = new Request("ProTandasController/guardarTanda");
            request.post(proTandasDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProTandasDto proTandas = (ProTandasDto) request.readEntity(ProTandasDto.class);
            return new Respuesta(true, "", "", "Tanda", proTandas);
        } catch (Exception ex) {
            Logger.getLogger(ProTandasService.class.getName()).log(Level.SEVERE, "Error guardando la tanda.", ex);
            return new Respuesta(false, "Error guardando la tanda.", "guardarTanda " + ex.getMessage());
        }
    }
    
    public Respuesta getTandasSinParametros() {
        try {

            Request request = new Request("ProTandasController/getTandas");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<ProTandasDto> proSalasDto = (List<ProTandasDto>) request.readEntity(new GenericType<List<ProTandasDto>>() {
            });
            return new Respuesta(true, "", "", "ProTandas", proSalasDto);

        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error consultando el tanda.", ex);
            return new Respuesta(false, "Error consultando el tanda.", "getTandasSinParametros " + ex.getMessage());
        }
    }
    
}
