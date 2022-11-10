/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProPeliculasDto;
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
public class ProPeliculasService {

    public Respuesta eliminarPelicula(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ProPeliculasController/eliminarPelicula", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ProComidasService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar la pelicula.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar la pelicula.", "eliminarPelicula " + ex.getMessage());
        }
    }

    public Respuesta guardarPelicula(ProPeliculasDto proPeliculasDto) {
        try {
            //TODO
            Request request = new Request("ProPeliculasController/guardarPelicula");
            request.post(proPeliculasDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProPeliculasDto proPelicula = (ProPeliculasDto) request.readEntity(ProPeliculasDto.class);
            return new Respuesta(true, "", "", "Pelicula", proPelicula);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error guardando la pelicula.", ex);
            return new Respuesta(false, "Error guardando la pelicula.", "guardarPelicula " + ex.getMessage());
        }
    }

    public Respuesta getPelicualas() {
        try {

            Request request = new Request("ProPeliculasController/facturas");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<ProPeliculasDto> proComidasDto = (List<ProPeliculasDto>) request.readEntity(new GenericType<List<ProPeliculasDto>>() {
            });
            return new Respuesta(true, "", "", "ProPeliculas", proComidasDto);

        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error consultando las peliculas.", ex);
            return new Respuesta(false, "Error consultando las peliculas.", "getPelicualas" + ex.getMessage());
        }
    }
}
