/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.service.ProSalasService;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Path("/ProSalasController")
public class ProSalasController {
    @EJB
    ProSalasService proSalasService;
    
    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }
}
