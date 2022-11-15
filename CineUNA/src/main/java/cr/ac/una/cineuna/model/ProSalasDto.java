/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.model;

import java.security.Timestamp;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class ProSalasDto {

    public SimpleStringProperty salId;
    public SimpleStringProperty salNombre;
    public ObjectProperty<String>  salEstado;
    public ObjectProperty<byte[]> salImgFondo;
    private Boolean modificado;

    public ProSalasDto() {
        this.modificado = false;
        this.salId = new SimpleStringProperty();
        this.salNombre = new SimpleStringProperty();
        this.salEstado = new SimpleObjectProperty("A");
        this.salImgFondo = new SimpleObjectProperty();
    }

    public Long getSalId() {
        if (salId.get() != null && !salId.get().isEmpty()) {
            return Long.valueOf(salId.get());
        } else {
            return null;
        }
    }

    public void setSalId(Long salId) {
        this.salId.set(salId.toString());
    }

    public String getSalNombre() {
        return salNombre.get();
    }

    public void setSalNombre(String salNombre) {
        this.salNombre.set(salNombre);
    }

    public String getSalEstado() {
        return salEstado.get();
    }

    public void setPelEstado(String salEstado) {
        this.salEstado.set(salEstado);
    }

    public byte[] getSalImgFondo() {
        return salImgFondo.get();
    }

    public void setSalImgFondo(byte[] salImgFondo) {
        this.salImgFondo.set(salImgFondo);
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

}
