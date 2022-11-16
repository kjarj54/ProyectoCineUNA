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
    private byte[] asiImg;
    private String asiNombre;
    private String asiEstado;
    private Boolean asiModificado;
    private ProSalas salId;

    public ProAsientosDto() {
        this.asiModificado = false;
    }

    
    public ProAsientosDto(ProAsientos proAsientos) {
        this();
        this.asiId = proAsientos.getAsiId();
        this.asiImg = (byte[]) proAsientos.getAsiImg();
        this.asiNombre = proAsientos.getAsiNombre();
        this.asiEstado = proAsientos.getAsiEstado();
        this.salId = proAsientos.getSalId();
    }
    
    public Long getAsiId() {
        return asiId;
    }

    public void setAsiId(Long asiId) {
        this.asiId = asiId;
    }

    public byte[] getAsiImg() {
        return asiImg;
    }

    public void setAsiImg(byte[] asiImg) {
        this.asiImg = asiImg;
    }

    public String getAsiNombre() {
        return asiNombre;
    }

    public void setAsiNombre(String asiNombre) {
        this.asiNombre = asiNombre;
    }

    public String getAsiEstado() {
        return asiEstado;
    }

    public void setAsiEstado(String asiCantidad) {
        this.asiEstado = asiCantidad;
    }

    public Boolean getAsiModificado() {
        return asiModificado;
    }

    public void setAsiModificado(Boolean asiModificado) {
        this.asiModificado = asiModificado;
    }

}
