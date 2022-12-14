/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.service;

import cr.ac.una.cineuna.model.ProClientesDto;
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
public class ProClientesService {

    public Respuesta getCliente(String usuario, String clave) {
        try {
            //TODO
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("usuario", usuario);
            parametros.put("clave", clave);
            Request request = new Request("ProClientesController/usuario", "/{usuario}/{clave}", parametros);
            request.getToken();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProClientesDto proClienteDto = (ProClientesDto) request.readEntity(ProClientesDto.class);
            return new Respuesta(true, "", "", "ProCliente", proClienteDto);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + usuario + "]", ex);
            return new Respuesta(false, "Error obteniendo el usuario.", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta eliminarCliente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ProClientesController/eliminarcliente", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar el cliente.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el cliente.", "eliminarCliente " + ex.getMessage());
        }
    }
    
    public Respuesta getCliente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("ProClientesController/clientes", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProClientesDto proClienteDto = (ProClientesDto) request.readEntity(ProClientesDto.class);
            return new Respuesta(true, "", "", "ProCliente", proClienteDto);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar el cliente.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el cliente.", "eliminarCliente " + ex.getMessage());
        }
    }
    
    public Respuesta recuperarClave(String correo) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("correo", correo);
            Request request = new Request("ProClientesController/recuperarClave", "/{correo}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Ocurrio un error al enviar el correo.", ex);
            return new Respuesta(false, "Ocurrio un error al enviar el correo.", "recuperarClave " + ex.getMessage());
        }
    }

    public Respuesta guardarCliente(ProClientesDto proClientesDto) {
        try {
            //TODO
            Request request = new Request("ProClientesController/guardarCliente");
            request.post(proClientesDto);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            ProClientesDto proClientes = (ProClientesDto) request.readEntity(ProClientesDto.class);
            return new Respuesta(true, "", "", "Cliente", proClientes);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error guardando el empleado.", ex);
            return new Respuesta(false, "Error guardando el empleado.", "guardarEmpleado " + ex.getMessage());
        }
    }

    public Respuesta getClientes(String id, String nombre, String usuario, String estado, String admin) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            parametros.put("usuario", usuario);
            parametros.put("nombre", nombre);
            parametros.put("estado", estado);
            parametros.put("admin", admin);

            Request request = new Request("ProClientesController/getClientes", "/{id}/{usuario}/{nombre}/{estado}/{admin}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<ProClientesDto> proClientesDto = (List<ProClientesDto>) request.readEntity(new GenericType<List<ProClientesDto>>() {
            });
            return new Respuesta(true, "", "", "ProClientes", proClientesDto);

        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error consultando el cliente.", ex);
            return new Respuesta(false, "Error consultando el cliente.", "getClientes " + ex.getMessage());
        }
    }
    
    
    public Respuesta getClientesSinParametros() {
        try {
            
            Request request = new Request("ProClientesController/getClientes");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }

            List<ProClientesDto> proClientesDto = (List<ProClientesDto>) request.readEntity(new GenericType<List<ProClientesDto>>() {
            });
            return new Respuesta(true, "", "", "ProClientes", proClientesDto);

        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error consultando el cliente.", ex);
            return new Respuesta(false, "Error consultando el cliente.", "getClientes " + ex.getMessage());
        }
    }
    

    public Respuesta renovarToken() {
        try {
            Request request = new Request("ProClientesController/renovar");
            request.getRenewal();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            String token = (String) request.readEntity(String.class);
            return new Respuesta(true, "", "", "Token", token);
        } catch (Exception ex) {
            Logger.getLogger(ProClientesService.class.getName()).log(Level.SEVERE, "Error obteniendo el token", ex);
            return new Respuesta(false, "Error renovando el token.", "renovarToken " + ex.getMessage());
        }
    }

}
