/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin
 */
public class ProSalasDto {

    private Long salId;
    private String salNombre;
    private String salEstado;
    private Byte[] salImgFondo;
    private Boolean modificado;
    List<ProPeliculasDto> peliculas;
    List<ProPeliculasDto> peliculasEliminados;

    public ProSalasDto() {
        this.modificado = false;
        peliculas = new ArrayList<>();
        peliculasEliminados = new ArrayList<>();
    }

    public ProSalasDto(ProSalas proSalas) {
        this();
        this.salId = proSalas.getSalId();
        this.salNombre = proSalas.getSalNombre();
        this.salEstado = proSalas.getSalEstado();
        this.salImgFondo = (Byte[]) proSalas.getSalImgfondo();
    }
    public Long getSalId() {
        return salId;
    }

    public void setSalId(Long salId) {
        this.salId = salId;
    }

    public String getSalNombre() {
        return salNombre;
    }

    public void setSalNombre(String salNombre) {
        this.salNombre = salNombre;
    }

    public String getSalEstado() {
        return salEstado;
    }

    public void setSalEstado(String salEstado) {
        this.salEstado = salEstado;
    }

    public Byte[] getSalImgFondo() {
        return salImgFondo;
    }

    public void setSalImgFondo(Byte[] salImgFondo) {
        this.salImgFondo = salImgFondo;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public List<ProPeliculasDto> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<ProPeliculasDto> peliculas) {
        this.peliculas = peliculas;
    }

    public List<ProPeliculasDto> getPeliculasEliminados() {
        return peliculasEliminados;
    }

    public void setPeliculasEliminados(List<ProPeliculasDto> peliculasEliminados) {
        this.peliculasEliminados = peliculasEliminados;
    }
    
    

}
