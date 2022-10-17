/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProClientesDto;
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
public class ProClientesService {
    
    public Respuesta getCliente(String usuario, String clave){
        try {
            //TODO
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("usuario", usuario);
            parametros.put("clave", clave);
            Request request = new Request("/ProClientesController/usuario", "/{usuario}/{clave}", parametros);
            request.getToken();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProClientesDto proClienteDto = (ProClientesDto) request.readEntity(ProClientesDto.class);
            return new Respuesta(true, "", "", "Empleado", proClienteDto);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + usuario + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }
    
}
