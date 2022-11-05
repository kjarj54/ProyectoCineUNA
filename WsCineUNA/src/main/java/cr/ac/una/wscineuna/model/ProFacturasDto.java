/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class ProFacturasDto {
    private Long facId;
    private Long facTotal;
    private LocalDate facFecha;
    private Boolean modificado;
    List<ProComidasDto> comidas;
    List<ProComidasDto> comidasEliminadas;

    public ProFacturasDto() {
        this.modificado = false;
        comidas = new ArrayList<>();
        comidasEliminadas = new ArrayList<>();
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

    public List<ProComidasDto> getComidas() {
        return comidas;
    }

    public void setComidas(List<ProComidasDto> comidas) {
        this.comidas = comidas;
    }

    public List<ProComidasDto> getComidasEliminadas() {
        return comidasEliminadas;
    }

    public void setComidasEliminadas(List<ProComidasDto> comidasEliminadas) {
        this.comidasEliminadas = comidasEliminadas;
    }
    
    
    
}
