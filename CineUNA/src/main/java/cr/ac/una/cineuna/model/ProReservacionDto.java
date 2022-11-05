/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.model;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class ProReservacionDto {
    
    public SimpleStringProperty resId;
    public ObjectProperty<LocalDate> resFecha;
    public SimpleStringProperty resTotal;
    private Boolean modificado;
    
    public ProReservacionDto() {
        this.modificado = false;
        this.resId = new SimpleStringProperty();
        this.resFecha = new SimpleObjectProperty();
        this.resTotal = new SimpleStringProperty();
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
    
    public Long getResHora() {
        if (resTotal.get() != null && !resTotal.get().isEmpty()) {
            return Long.valueOf(resTotal.get());
        } else {
            return null;
        }
    }
    
    public void setResHora(Long resTotal) {
        this.resTotal.set(resTotal.toString());
    }
    
    public Boolean getModificado() {
        return modificado;
    }
    
    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
}
