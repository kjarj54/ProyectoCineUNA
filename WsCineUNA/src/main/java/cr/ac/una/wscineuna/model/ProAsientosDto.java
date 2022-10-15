/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

/**
 *
 * @author kevin
 */
public class ProAsientosDto {

    private Long asiId;
    private Byte[] asiImg;
    private String asiNombre;
    private Long asiCantidad;
    private Boolean asiModificado;

    public ProAsientosDto() {
        this.asiModificado = false;
    }

    /*
    public ProAsientosDto(ProAsientos proAsientos) {
        this();
        this.asiId = null;
        this.asiImg = null;
        this.asiNombre = null;
        this.asiCantidad = null;
    }*/
    public Long getAsiId() {
        return asiId;
    }

    public void setAsiId(Long asiId) {
        this.asiId = asiId;
    }

    public Byte[] getAsiImg() {
        return asiImg;
    }

    public void setAsiImg(Byte[] asiImg) {
        this.asiImg = asiImg;
    }

    public String getAsiNombre() {
        return asiNombre;
    }

    public void setAsiNombre(String asiNombre) {
        this.asiNombre = asiNombre;
    }

    public Long getAsiCantidad() {
        return asiCantidad;
    }

    public void setAsiCantidad(Long asiCantidad) {
        this.asiCantidad = asiCantidad;
    }

    public Boolean getAsiModificado() {
        return asiModificado;
    }

    public void setAsiModificado(Boolean asiModificado) {
        this.asiModificado = asiModificado;
    }

}
