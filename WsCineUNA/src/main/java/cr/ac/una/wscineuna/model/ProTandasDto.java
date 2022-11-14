/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kevin
 */
public class ProTandasDto {

    private Long tanId;
    private String tanNombre;
    private String tanHorainico;
    private String tanHorafinal;
    private LocalDate tanFecha;
    private Long tanPrecio;
    private Boolean modificado;
    List<ProAsientosDto> asientos;
    List<ProAsientosDto> asientosEliminados;
    
    public ProPeliculasDto pelId;

    public ProTandasDto() {
        this.modificado = false;
        asientos = new ArrayList<>();
        asientosEliminados = new ArrayList<>();
        this.pelId = new ProPeliculasDto();
    }

    public ProTandasDto(ProTandas proTandas) {
        this();
        this.tanId = proTandas.getTanId();
        this.tanNombre = proTandas.getTanNombre();
        this.tanFecha = proTandas.getTanFecha();
        this.tanHorafinal = proTandas.getTanHorafinal();
        this.tanHorainico = proTandas.getTanHorainicio();
        this.tanPrecio = proTandas.getTanPrecio();
        
        
        
    }
    
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

    public String getTanHorainico() {
        return tanHorainico;
    }

    public void setTanHorainico(String tanHorainico) {
        this.tanHorainico = tanHorainico;
    }

    public String getTanHorafinal() {
        return tanHorafinal;
    }

    public void setTanHorafinal(String tanHorafinal) {
        this.tanHorafinal = tanHorafinal;
    }

    public LocalDate getTanFecha() {
        return tanFecha;
    }

    public void setTanFecha(LocalDate tanFecha) {
        this.tanFecha = tanFecha;
    }

    public Long getTanPrecio() {
        return tanPrecio;
    }

    public void setTanPrecio(Long tanPrecio) {
        this.tanPrecio = tanPrecio;
    }

    public ProPeliculasDto getPelId() {
        return pelId;
    }

    public void setPelId(ProPeliculasDto pelId) {
        this.pelId = pelId;
    }
    
    

    
    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<ProAsientosDto> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<ProAsientosDto> asientos) {
        this.asientos = asientos;
    }

    public List<ProAsientosDto> getAsientosEliminados() {
        return asientosEliminados;
    }

    public void setAsientosEliminados(List<ProAsientosDto> asientosEliminados) {
        this.asientosEliminados = asientosEliminados;
    }
    
    

}
