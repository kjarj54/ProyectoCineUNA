package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.service.ProClientesService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.JwTokenHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author 
 */
@Path("/ProClientesController")
public class ProClientesController {
    
    @EJB
    ProClientesService proClientesService;
    
    @Context
    SecurityContext securityContext;
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
    
    @GET
    @Path("/renovar")
    public Response renovar() {
        try {
            String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (usuarioRequest != null && !usuarioRequest.isEmpty()) {
                return Response.ok(JwTokenHelper.getInstance().generatePrivateKey(usuarioRequest)).build();
            } else {
                return Response.status(CodigoRespuesta.ERROR_PERMISOS.getValue()).entity("No se pudo renovar el token").build();

            }
        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el token").build();
        }
    }
}
