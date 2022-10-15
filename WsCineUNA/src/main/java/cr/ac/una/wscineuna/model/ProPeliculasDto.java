/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.net.URL;

/**
 *
 * @author kevin
 */
public class ProPeliculasDto {

    private Long pelId;
    private String pelNombre;
    private String pelSynopsis;
    private URL pelLink;
    private Byte[] pelImagen;
    private String pelEstado;
    private Boolean modificado;

    public ProPeliculasDto() {
        this.modificado = false;
    }

    /*public ProPeliculasDto(ProPeliculas proPeliculas) {
        this();
        this.pelId = null;
        this.pelNombre = null;
        this.pelSynopsis = null;
        this.pelLink = null;
        this.pelImagen = null;
        this.pelEstado = null;
    }*/
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

    public URL getPelLink() {
        return pelLink;
    }

    public void setPelLink(URL pelLink) {
        this.pelLink = pelLink;
    }

    public Byte[] getPelImagen() {
        return pelImagen;
    }

    public void setPelImagen(Byte[] pelImagen) {
        this.pelImagen = pelImagen;
    }

    public String getPelEstado() {
        return pelEstado;
    }

    public void setPelEstado(String pelEstado) {
        this.pelEstado = pelEstado;
    }

}
