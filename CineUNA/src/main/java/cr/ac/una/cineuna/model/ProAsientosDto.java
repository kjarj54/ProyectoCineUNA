/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class ProAsientosDto {

    public SimpleStringProperty asiId;
    public ObjectProperty<byte[]> asiImg;
    public SimpleStringProperty asiNombre;
    public SimpleStringProperty asiCantidad;
    private Boolean asiModificado;

    public ProAsientosDto() {
        this.asiModificado = false;
        this.asiId = new SimpleStringProperty();
        this.asiImg = new SimpleObjectProperty();
        this.asiNombre = new SimpleStringProperty();
        this.asiCantidad = new SimpleStringProperty();

    }

    public Long getAsiId() {
        if (asiId.get() != null && !asiId.get().isEmpty()) {
            return Long.valueOf(asiId.get());
        } else {
            return null;
        }
    }

    public void setAsiId(Long asiId) {
        this.asiId.set(asiId.toString());
    }

    public byte[] getAsiImg() {
        return asiImg.get();
    }

    public void setAsiImg(byte[] asiImg) {
        this.asiImg.set(asiImg);
    }

    public String getAsiNombre() {
        return asiNombre.get();
    }

    public void setAsiNombre(String asiNombre) {
        this.asiNombre.set(asiNombre);
    }

    public String getAsiCantidad() {
        return asiCantidad.get();
    }

    public void setAsiCantidad(Long asiCantidad) {
        this.asiCantidad.set(asiCantidad.toString());
    }

    public Boolean getAsiModificado() {
        return asiModificado;
    }

    public void setAsiModificado(Boolean asiModificado) {
        this.asiModificado = asiModificado;
    }

}
