/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class ProComidasDto {

    private SimpleStringProperty comId;
    private SimpleStringProperty comNombre;
    private SimpleStringProperty comPrecio;
    private SimpleStringProperty comDescripcion;
    private Boolean modificado;

    public ProComidasDto() {
        this.modificado = false;
        this.comDescripcion = new SimpleStringProperty();
        this.comId = new SimpleStringProperty();
        this.comNombre = new SimpleStringProperty();
        this.comPrecio = new SimpleStringProperty();
    }

    public Long getComId() {
        if (comId.get() != null && !comId.get().isEmpty()) {
            return Long.valueOf(comId.get());
        } else {
            return null;
        }
    }

    public void setComId(Long comId) {
        this.comId.set(comId.toString());
    }

    public String getComNombre() {
        return comNombre.get();
    }

    public void setComNombre(String comNombre) {
        this.comNombre.set(comNombre);
    }

    public Long getComPrecio() {
        if (comPrecio.get() != null && !comPrecio.get().isEmpty()) {
            return Long.valueOf(comPrecio.get());
        } else {
            return null;
        }
    }

    public void setComPrecio(Long comPrecio) {
        this.comPrecio.set(comPrecio.toString());
    }

    public String getComDescripcion() {
        return comDescripcion.get();
    }

    public void setComDescripcion(String comDescripcion) {
        this.comDescripcion.set(comDescripcion);
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

}
