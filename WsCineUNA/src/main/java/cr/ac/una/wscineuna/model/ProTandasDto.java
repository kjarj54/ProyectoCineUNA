/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.security.Timestamp;
import java.util.Date;

/**
 *
 * @author kevin
 */
public class ProTandasDto {

    private Long tanId;
    private String tanNombre;
    private Timestamp tanHorainico;
    private Timestamp tanHorafinal;
    private Date tanFecha;
    private Long tanPrecio;
    private Boolean modificado;

    public ProTandasDto() {
        this.modificado = false;
    }

    /*public ProTandasDto(ProTandas proTandas) {
        this();
        this.tanId = tanId;
        this.tanNombre = tanNombre;
    }*/
    
    public Long getTanId() {
        return tanId;
    }

    public void setTanId(Long tanId) {
        this.tanId = tanId;
    }

    public String getTanNombre() {
        return tanNombre;
    }

    public void setTanNombre(String tanNombre) {
        this.tanNombre = tanNombre;
    }

    public Timestamp getTanHorainico() {
        return tanHorainico;
    }

    public void setTanHorainico(Timestamp tanHorainico) {
        this.tanHorainico = tanHorainico;
    }

    public Timestamp getTanHorafinal() {
        return tanHorafinal;
    }

    public void setTanHorafinal(Timestamp tanHorafinal) {
        this.tanHorafinal = tanHorafinal;
    }

    public Date getTanFecha() {
        return tanFecha;
    }

    public void setTanFecha(Date tanFecha) {
        this.tanFecha = tanFecha;
    }

    public Long getTanPrecio() {
        return tanPrecio;
    }

    public void setTanPrecio(Long tanPrecio) {
        this.tanPrecio = tanPrecio;
    }

    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

}
