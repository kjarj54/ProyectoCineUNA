/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.security.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class ProReservacionDto {

    private Long resId;
    private LocalDate resFecha;
    private Timestamp resHora;
    private Boolean modificado;

    public ProReservacionDto() {
        this.modificado = false;
    }

    /*public ProReservacionDto(ProReservacion proReservacion) {
        this();
        this.resId = null;
        this.resFecha = null;
        this.resHora = null;
    }*/
    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public LocalDate getResFecha() {
        return resFecha;
    }

    public void setResFecha(LocalDate resFecha) {
        this.resFecha = resFecha;
    }

    public Timestamp getResHora() {
        return resHora;
    }

    public void setResHora(Timestamp resHora) {
        this.resHora = resHora;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

}
