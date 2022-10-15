/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.model;

import java.security.Timestamp;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class ProReservacionDto {
    
    private SimpleStringProperty resId;
    private ObjectProperty<LocalDate> resFecha;
    private ObjectProperty<Timestamp> resHora;
    private Boolean modificado;
    
    public ProReservacionDto() {
        this.modificado = false;
        this.resId = new SimpleStringProperty();
        this.resFecha = new SimpleObjectProperty();
        this.resHora = new SimpleObjectProperty();
    }
    
    public Long getResId() {
        if (resId.get() != null && !resId.get().isEmpty()) {
            return Long.valueOf(resId.get());
        } else {
            return null;
        }
    }
    
    public void setResId(Long resId) {
        this.resId.set(resId.toString());
    }
    
    public LocalDate getResFecha() {
        return resFecha.get();
    }
    
    public void setResFecha(LocalDate resFecha) {
        this.resFecha.set(resFecha);
    }
    
    public Timestamp getResHora() {
        return resHora.get();
    }
    
    public void setResHora(Timestamp resHora) {
        this.resHora.set(resHora);
    }
    
    public Boolean getModificado() {
        return modificado;
    }
    
    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
}
