/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class ProFacturasDto {
    private Long facId;
    private Long facTotal;
    private LocalDate facFecha;
    Boolean modificado;

    public ProFacturasDto() {
        this.modificado = false;
    }

    public ProFacturasDto(ProFacturas proFacturas){
        this();
        this.facId = proFacturas.getFacId();
        this.facFecha = proFacturas.getFacFecha();
        this.facTotal = proFacturas.getFacTotal();
    }
    
    
    public Long getFacId() {
        return facId;
    }

    public void setFacId(Long facId) {
        this.facId = facId;
    }

    public Long getFacTotal() {
        return facTotal;
    }

    public void setFacTotal(Long facTotal) {
        this.facTotal = facTotal;
    }

    public LocalDate getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(LocalDate facFecha) {
        this.facFecha = facFecha;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
    
}
