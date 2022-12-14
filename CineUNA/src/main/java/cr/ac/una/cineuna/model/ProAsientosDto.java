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
    public SimpleStringProperty asiEstado;
    private Boolean asiModificado;
    
    public ProTandasDto tanId;
    

    public ProAsientosDto() {
        this.asiModificado = false;
        this.asiId = new SimpleStringProperty();
        this.asiImg = new SimpleObjectProperty();
        this.asiNombre = new SimpleStringProperty();
        this.asiEstado = new SimpleStringProperty("L");
        this.tanId = new ProTandasDto();

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

    public String getAsiEstado() {
        return asiEstado.get();
    }

    public void setAsiEstado(String asiEstado) {
        this.asiEstado.set(asiEstado);
    }

    public Boolean getAsiModificado() {
        return asiModificado;
    }

    public void setAsiModificado(Boolean asiModificado) {
        this.asiModificado = asiModificado;
    }

    public ProTandasDto getTanId() {
        return tanId;
    }

    public void setTanId(ProTandasDto tanId) {
        this.tanId = tanId;
    }
    
    

}
