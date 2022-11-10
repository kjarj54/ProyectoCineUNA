/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.model;

import java.net.URL;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author kevin
 */
public class ProPeliculasDto {

    public SimpleStringProperty pelId;
    public SimpleStringProperty pelNombre;
    public SimpleStringProperty pelSynopsis;
    public SimpleStringProperty pelLink;
    public ObjectProperty<byte[]> pelImagen;
    public ObjectProperty<String> pelIdioma;
    public ObjectProperty<String> pelEstado;
    private Boolean modificado;

    public ProPeliculasDto() {
        this.modificado = false;
        this.pelId = new SimpleStringProperty();
        this.pelNombre = new SimpleStringProperty();
        this.pelSynopsis = new SimpleStringProperty();
        this.pelLink = new SimpleStringProperty();
        this.pelImagen = new SimpleObjectProperty();
        this.pelEstado = new SimpleObjectProperty("S");
        this.pelIdioma = new SimpleObjectProperty("E");
    }

    public Long getPelId() {
        if (pelId.get() != null && !pelId.get().isEmpty()) {
            return Long.valueOf(pelId.get());
        } else {
            return null;
        }
    }

    public void setPelId(Long pelId) {
        this.pelId.set(pelId.toString());
    }

    public String getPelNombre() {
        return pelNombre.get();
    }

    public void setPelNombre(String pelNombre) {
        this.pelNombre.set(pelNombre);
    }

    public String getPelSynopsis() {
        return pelSynopsis.get();
    }

    public void setPelSynopsis(String pelSynopsis) {
        this.pelSynopsis.set(pelSynopsis);
    }

    public String getPelLink() {
        return pelLink.get();
    }

    public void setPelLink(String pelLink) {
        this.pelLink.set(pelLink);
    }

    public byte[] getPelImagen() {
        return pelImagen.get();
    }

    public void setPelImagen(byte[] pelImagen) {
        this.pelImagen.set(pelImagen);
    }

    public String getPelEstado() {
        return pelEstado.get();
    }

    public void setPelEstado(String pelEstado) {
        this.pelEstado.set(pelEstado);
    }

    public String getPelIdioma() {
        return pelIdioma.get();
    }

    public void setPelIdioma(String pelIdioma) {
        this.pelIdioma.set(pelIdioma);
    }

}
