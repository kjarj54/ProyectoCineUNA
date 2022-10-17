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
public class ProTandasDto {

    public SimpleStringProperty tanId;
    public SimpleStringProperty tanNombre;
    private Boolean modificado;

    public ProTandasDto() {
        this.modificado = false;
        this.tanId = new SimpleStringProperty();
        this.tanNombre = new SimpleStringProperty();
    }

    public Long getTanId() {
       if(tanId.get()!=null && !tanId.get().isEmpty())
            return Long.valueOf(tanId.get());
        else
            return null;
    }

    public void setTanId(Long tanId) {
        this.tanId.set(tanId.toString());
    }

    public String getTanNombre() {
        return tanNombre.get();
    }

    public void setTanNombre(String tanNombre) {
        this.tanNombre.set(tanNombre);
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
