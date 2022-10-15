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
public class ProSalasDto {

    private Long salId;
    private String salNombre;
    private String salEstado;
    private Long salCosto;
    private Timestamp salHoraInicio;
    private Timestamp salHoraFinal;
    private LocalDate salFechaInicio;
    private LocalDate salFechaFinal;
    private Byte[] salImgFondo;
    private Boolean modificado;

    public ProSalasDto() {
        this.modificado = false;
    }

    /*public ProSalasDto(ProSalas proSalas) {
        this();
        this.salId = salId;
        this.salNombre = salNombre;
        this.salEstado = salEstado;
        this.salCosto = salCosto;
        this.salHoraInicio = salHoraInicio;
        this.salHoraFinal = salHoraFinal;
        this.salFechaInicio = salFechaInicio;
        this.salFechaFinal = salFechaFinal;
        this.salImgFondo = salImgFondo;
    }*/
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

    public Long getSalCosto() {
        return salCosto;
    }

    public void setSalCosto(Long salCosto) {
        this.salCosto = salCosto;
    }

    public Timestamp getSalHoraInicio() {
        return salHoraInicio;
    }

    public void setSalHoraInicio(Timestamp salHoraInicio) {
        this.salHoraInicio = salHoraInicio;
    }

    public Timestamp getSalHoraFinal() {
        return salHoraFinal;
    }

    public void setSalHoraFinal(Timestamp salHoraFinal) {
        this.salHoraFinal = salHoraFinal;
    }

    public LocalDate getSalFechaInicio() {
        return salFechaInicio;
    }

    public void setSalFechaInicio(LocalDate salFechaInicio) {
        this.salFechaInicio = salFechaInicio;
    }

    public LocalDate getSalFechaFinal() {
        return salFechaFinal;
    }

    public void setSalFechaFinal(LocalDate salFechaFinal) {
        this.salFechaFinal = salFechaFinal;
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

}
