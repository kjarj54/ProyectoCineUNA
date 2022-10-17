/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.model;

import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class ProClientesDto {

    public SimpleStringProperty cliId;
    public SimpleStringProperty cliUsuario;
    public SimpleStringProperty cliNombre;
    public SimpleStringProperty cliPApellido;
    public SimpleStringProperty cliCorreo;
    public ObjectProperty<String> cliIdioma;
    public SimpleBooleanProperty cliEstado;
    public SimpleBooleanProperty cliAdmin;
    public SimpleStringProperty cliSApellido;
    public SimpleStringProperty cliClave;
    private Boolean modificado;
    private String token;

    public ProClientesDto() {
        this.modificado = false;
        this.cliId = new SimpleStringProperty();
        this.cliUsuario = new SimpleStringProperty();
        this.cliClave = new SimpleStringProperty();
        this.cliNombre = new SimpleStringProperty();
        this.cliPApellido = new SimpleStringProperty();
        this.cliCorreo = new SimpleStringProperty();
        this.cliIdioma = new SimpleObjectProperty();
        this.cliEstado = new SimpleBooleanProperty(false);
        this.cliAdmin = new SimpleBooleanProperty(false);
        this.cliSApellido = new SimpleStringProperty();
    }

    public Long getCliId() {
        if (cliId.get() != null && !cliId.get().isEmpty()) {
            return Long.valueOf(cliId.get());
        } else {
            return null;
        }
    }

    public void setCliId(Long cliId) {
        this.cliId.set(cliId.toString());
    }

    public String getCliUsuario() {
        return cliUsuario.get();
    }

    public void setCliUsuario(String cliUsuario) {
        this.cliUsuario.set(cliUsuario);
    }

    public String getCliNombre() {
        return cliNombre.get();
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre.set(cliNombre);
    }

    public String getCliPApellido() {
        return cliPApellido.get();
    }

    public void setCliPApellido(String cliPApellido) {
        this.cliPApellido.set(cliPApellido);
    }

    public String getCliCorreo() {
        return cliCorreo.get();
    }

    public void setCliCorreo(String cliCorreo) {
        this.cliCorreo.set(cliCorreo);
    }

    public String getCliIdioma() {
        return cliIdioma.get();
    }

    public void setCliIdioma(String cliIdioma) {
        this.cliIdioma.set(cliIdioma);
    }

    public String getCliEstado() {
        return cliEstado.getValue() ? "A" : "I";
    }

    public void setCliEstado(String cliEstado) {
        this.cliEstado.setValue(cliEstado.equalsIgnoreCase("A"));
    }

    public String getCliAdmin() {
        return cliAdmin.getValue() ? "S" : "N";
    }

    public void setCliAdmin(String cliAdmin) {
        this.cliAdmin.setValue(cliAdmin.equalsIgnoreCase("S"));
    }

    public String getCliSApellido() {
        return cliSApellido.get();
    }

    public void setCliSApellido(String cliSApellido) {
        this.cliSApellido.set(cliSApellido);
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
        return cliClave.get();
    }

    public void setCliClave(String cliClave) {
        this.cliClave.set(cliClave);
    }
}
