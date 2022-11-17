/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.cineuna.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author kevin
 */
public class ProFacturasDto {

    public SimpleStringProperty facId;
    public SimpleStringProperty facTotal;
    public ObjectProperty<LocalDate> facFecha;
    private Boolean modificado;
    List<ProComidasDto> comidas;
    List<ProComidasDto> comidasEliminadas;
    ProClientesDto cliId;

    public ProFacturasDto() {
        this.modificado = false;
        this.facFecha = new SimpleObjectProperty();
        this.facId = new SimpleStringProperty();
        this.facTotal = new SimpleStringProperty();
        comidas = FXCollections.observableArrayList();
        comidasEliminadas = new ArrayList<>();
        this.cliId = new ProClientesDto();
    }

    public Long getFacId() {
        if (facId.get() != null && !facId.get().isEmpty()) {
            return Long.valueOf(facId.get());
        } else {
            return null;
        }
    }

    public void setFacId(Long facId) {
        this.facId.set(facId.toString());
    }

    public Long getFacTotal() {
        if (facTotal.get() != null && !facTotal.get().isEmpty()) {
            return Long.valueOf(facTotal.get());
        } else {
            return null;
        }
    }

    public void setFacTotal(Long facTotal) {
        this.facTotal.set(facTotal.toString());
    }

    public LocalDate getFacFecha() {
        return facFecha.get();
    }

    public void setFacFecha(LocalDate facFecha) {
        this.facFecha.set(facFecha);
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
        this.comidas = FXCollections.observableArrayList(comidas);
    }

    public List<ProComidasDto> getComidasEliminadas() {
        return comidasEliminadas;
    }

    public void setComidasEliminadas(List<ProComidasDto> comidasEliminadas) {
        this.comidasEliminadas = comidasEliminadas;
    }

    public ProClientesDto getCliId() {
        return cliId;
    }

    public void setCliId(ProClientesDto cliId) {
        this.cliId = cliId;
    }

}
