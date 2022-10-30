/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProClientesDto;
import cr.ac.una.cineuna.model.ProComidasDto;
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
public class ProComidasService {

    public Respuesta eliminarComida(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ProComidasController/eliminarComida", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ProComidasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar la comida.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la comida.", "eliminarComida " + ex.getMessage());
        }
    }

    public Respuesta guardarComida(ProComidasDto proComidasDto) {
        try {
            //TODO
            Request request = new Request("ProComidasController/guardarComida");
            request.post(proComidasDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProComidasDto proComida = (ProComidasDto) request.readEntity(ProComidasDto.class);
            return new Respuesta(true, "", "", "Comida", proComida);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error guardando la comida.", ex);
            return new Respuesta(false, "Error guardando la comida.", "guardarComida " + ex.getMessage());
        }
    }

    public Respuesta getComidas() {
        try {

            Request request = new Request("ProComidasController/comidas");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<ProComidasDto> proComidasDto = (List<ProComidasDto>) request.readEntity(new GenericType<List<ProComidasDto>>() {
            });
            return new Respuesta(true, "", "", "ProComidas", proComidasDto);

        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error consultando las comidas.", ex);
            return new Respuesta(false, "Error consultando las comidas.", "getComidas" + ex.getMessage());
        }
    }
}
