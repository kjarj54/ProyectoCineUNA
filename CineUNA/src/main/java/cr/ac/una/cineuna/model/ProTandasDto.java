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
public class ProTandasDto {

    public SimpleStringProperty tanId;
    public SimpleStringProperty tanNombre;
    public SimpleStringProperty tanPrecio;
    public ObjectProperty<Timestamp> tanHoraInicio;
    public ObjectProperty<Timestamp> tanHoraFinal;
    public ObjectProperty<LocalDate> tanFecha;
    private Boolean modificado;

    public ProTandasDto() {
        this.modificado = false;
        this.tanId = new SimpleStringProperty();
        this.tanNombre = new SimpleStringProperty();
        this.tanPrecio = new SimpleStringProperty();
        this.tanHoraInicio = new SimpleObjectProperty();
        this.tanHoraFinal = new SimpleObjectProperty();
        this.tanFecha = new SimpleObjectProperty();
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
    
    public Long getTanPrecio() {
        if (tanPrecio.get() != null && !tanPrecio.get().isEmpty()) {
            return Long.valueOf(tanPrecio.get());
        } else {
            return null;
        }
    }

    public void setTanPrecio(Long tanPrecio) {
        this.tanPrecio.set(tanPrecio.toString());
    }

    public Timestamp getTanHoraInicio() {
        return tanHoraInicio.get();
    }

    public void setTanHoraInicio(Timestamp tanHoraInicio) {
        this.tanHoraInicio.set(tanHoraInicio);
    }

    public Timestamp getTanHoraFinal() {
        return tanHoraFinal.get();
    }

    public void setTanHoraFinal(Timestamp tanHoraFinal) {
        this.tanHoraFinal.set(tanHoraFinal);
    }

    public LocalDate getTanFecha() {
        return tanFecha.get();
    }

    public void setTanFecha(LocalDate tanFecha) {
        this.tanFecha.set(tanFecha);
    }

    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
