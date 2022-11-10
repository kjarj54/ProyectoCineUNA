/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.net.URL;
import java.time.LocalDate;

/**
 *
 * @author kevin
 */
public class ProPeliculasDto {

    private Long pelId;
    private String pelNombre;
    private String pelSynopsis;
    private String pelLink;
    private byte[] pelImagen;
    private LocalDate pelFechaestreno;
    private String pelEstado;
    private String pelIdioma;
    private Boolean modificado;

    public ProPeliculasDto() {
        this.modificado = false;
    }

    public ProPeliculasDto(ProPeliculas proPeliculas) {
        this();
        this.pelId = proPeliculas.getPelId();
        this.pelNombre = proPeliculas.getPelNombre();
        this.pelSynopsis = proPeliculas.getPelSynopsis();
        this.pelLink = proPeliculas.getPelLink();
        this.pelImagen = (byte[]) proPeliculas.getPelImagen();
        this.pelEstado = proPeliculas.getPelEstado();
        this.pelIdioma = proPeliculas.getPelIdioma();
    }
    public Long getPelId() {
        return pelId;
    }

    public void setPelId(Long pelId) {
        this.pelId = pelId;
    }

    public String getPelNombre() {
        return pelNombre;
    }

    public void setPelNombre(String pelNombre) {
        this.pelNombre = pelNombre;
    }

    public String getPelSynopsis() {
        return pelSynopsis;
    }

    public void setPelSynopsis(String pelSynopsis) {
        this.pelSynopsis = pelSynopsis;
    }

    public String getPelLink() {
        return pelLink;
    }

    public void setPelLink(String pelLink) {
        this.pelLink = pelLink;
    }

    public byte[] getPelImagen() {
        return pelImagen;
    }

    public void setPelImagen(byte[] pelImagen) {
        this.pelImagen = pelImagen;
    }

    public String getPelEstado() {
        return pelEstado;
    }

    public void setPelEstado(String pelEstado) {
        this.pelEstado = pelEstado;
    }

    public LocalDate getPelFechaestreno() {
        return pelFechaestreno;
    }

    public void setPelFechaestreno(LocalDate pelFechaestreno) {
        this.pelFechaestreno = pelFechaestreno;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public String getPelIdioma() {
        return pelIdioma;
    }

    public void setPelIdioma(String pelIdioma) {
        this.pelIdioma = pelIdioma;
    }
    
    

}
