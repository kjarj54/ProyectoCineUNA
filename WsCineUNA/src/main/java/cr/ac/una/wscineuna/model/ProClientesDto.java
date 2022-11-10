/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.time.LocalDateTime;

/**
 *
 * @author kevin
 */
public class ProClientesDto {

    private Long cliId;
    private String cliUsuario;
    private String cliNombre;
    private String cliPApellido;
    private String cliCorreo;
    private String cliIdioma;
    private String cliEstado;
    private String cliAdmin;
    private String cliSApellido;
    private String cliClave;
    private String cliClaverestaurada;
    private Boolean modificado;
    private String token;
    private LocalDateTime fecha;

    public ProClientesDto() {
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public ProClientesDto(ProClientes proClientes) {
        this();
        this.cliId = proClientes.getCliId();
        this.cliUsuario = proClientes.getCliUsuario();
        this.cliNombre = proClientes.getCliNombre();
        this.cliPApellido = proClientes.getCliPapellido();
        this.cliCorreo = proClientes.getCliCorreo();
        this.cliIdioma = proClientes.getCliIdioma();
        this.cliEstado = proClientes.getCliEstado();
        this.cliAdmin = proClientes.getCliAdmin();
        this.cliSApellido = proClientes.getCliSapellido();
        this.cliClave = proClientes.getCliClave();
        this.cliClaverestaurada = proClientes.getCliClaverestaurada();
    }
    
    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliUsuario() {
        return cliUsuario;
    }

    public void setCliUsuario(String cliUsuario) {
        this.cliUsuario = cliUsuario;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliPApellido() {
        return cliPApellido;
    }

    public void setCliPApellido(String cliPApellido) {
        this.cliPApellido = cliPApellido;
    }

    public String getCliCorreo() {
        return cliCorreo;
    }

    public void setCliCorreo(String cliCorreo) {
        this.cliCorreo = cliCorreo;
    }

    public String getCliIdioma() {
        return cliIdioma;
    }

    public void setCliIdioma(String cliIdioma) {
        this.cliIdioma = cliIdioma;
    }

    public String getCliEstado() {
        return cliEstado;
    }

    public void setCliEstado(String cliEstado) {
        this.cliEstado = cliEstado;
    }

    public String getCliAdmin() {
        return cliAdmin;
    }

    public void setCliAdmin(String cliAdmin) {
        this.cliAdmin = cliAdmin;
    }

    public String getCliSApellido() {
        return cliSApellido;
    }

    public void setCliSApellido(String cliSApellido) {
        this.cliSApellido = cliSApellido;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCliClave() {
        return cliClave;
    }

    public void setCliClave(String cliClave) {
        this.cliClave = cliClave;
    }

    public String getCliClaverestaurada() {
        return cliClaverestaurada;
    }

    public void setCliClaverestaurada(String cliClaverestaurada) {
        this.cliClaverestaurada = cliClaverestaurada;
    }
    
    

}
