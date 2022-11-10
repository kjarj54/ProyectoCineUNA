package cr.ac.una.wscineuna.controller;

import cr.ac.una.wscineuna.model.ProClientesDto;
import cr.ac.una.wscineuna.service.ProClientesService;
import cr.ac.una.wscineuna.util.CodigoRespuesta;
import cr.ac.una.wscineuna.util.JwTokenHelper;
import cr.ac.una.wscineuna.util.Respuesta;
import cr.ac.una.wscineuna.util.Secure;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
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
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }

    @POST
    @Path("/guardarCliente")
    public Response guardarCliente(ProClientesDto proClientesDto) {
        try {
            Respuesta res = proClientesService.guardarCliente(proClientesDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            Respuesta res2 = proClientesService.correoActivacion((ProClientesDto) res.getResultado("Cliente"));
            return Response.ok((ProClientesDto) res.getResultado("Cliente")).build();
        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el cliente").build();
        }
    }

    @GET
    //@Secure
    @Path("/activarCuenta")
    public Response activarCuenta(@QueryParam("id") Long id) {
        try {
            Respuesta res = proClientesService.activacionCuenta(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().entity("<html lang=\"es\">\n"
                    + "<head>\n"
                    + "	<meta charset=\"UTF-8\">\n"
                    + "	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "	<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n"
                    + "	<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\">\n"
                    + "	<title>Pagina de verificacion</title>\n"
                    + "</head>\n"
                    + "<body style=\"background-color: #f5f5f5;\">\n"
                    + "	<div class=\"text-center p-5\">\n"
                    + "	   <h1>Gracias por registrarse!</h1>\n"
                    + "	   <p class=\"lead\">Ahora su cuenta a sido activada puede usar la app con normalidad.</p>\n"
                    + "	</div>\n"
                    + "	\n"
                    + "	<!-- FOOTER -->\n"
                    + "	<footer class=\"footer mt-5 text-muted text-center text-small\">\n"
                    + "      <p>Email Verification</p>\n"
                    + "    </footer>\n"
                    + "</body>\n"
                    + "</html>").build();
        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el cliente").build();
        }
    }

    @GET
    @Path("/recuperarClave/{correo}")
    public Response recuperarClave(@PathParam("correo") String correo) {
        try {
            Respuesta res = proClientesService.recuperarClave(correo);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    @GET
    @Path("/usuario/{usuario}/{clave}")
    public Response getUsuario(@PathParam("usuario") String usuario, @PathParam("clave") String clave) {
        try {
            Respuesta res = proClientesService.validarUsuario(usuario, clave);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProClientesDto proClientesDto = (ProClientesDto) res.getResultado("ProCliente");
            proClientesDto.setToken(JwTokenHelper.getInstance().generatePrivateKey(usuario));
            return Response.ok(proClientesDto).build();
        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    @GET
    @Secure
    @Path("/clientes/{id}")
    public Response getCliente(@PathParam("id") Long id) {
        try {
            Respuesta res = proClientesService.getCliente(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            ProClientesDto proClientesDto = (ProClientesDto) res.getResultado("ProCliente");

            return Response.ok(proClientesDto).build();

        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    @GET
    @Secure
    @Path("/getClientes/{id}/{usuario}/{nombre}/{estado}/{admin}")
    public Response getClientes(@PathParam("id") String id, @PathParam("usuario") String usuario, @PathParam("nombre") String nombre, @PathParam("estado") String estado, @PathParam("admin") String admin) {
        try {
            Respuesta res = proClientesService.getClientes(id, usuario, nombre, estado, admin);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProClientesDto>>((List<ProClientesDto>) res.getResultado("ProClientes")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo Clientes").build();
        }
    }

    @GET
    @Secure
    @Path("/getClientes/")
    public Response getClientesSinParametros() {
        try {
            Respuesta res = proClientesService.getClientesSinParametros();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<ProClientesDto>>((List<ProClientesDto>) res.getResultado("ProClientes")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo Clientes").build();
        }
    }

    @DELETE
    @Path("/eliminarCliente/{id}")
    public Response eliminarCliente(@PathParam("id") Long id) {
        try {
            Respuesta res = proClientesService.eliminarCliente(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();

        } catch (Exception ex) {
            Logger.getLogger(ProClientesController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando Cliente").build();
        }
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
