/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

/**
 *
 * @author kevin
 */
public class ProComidasDto {

    private Long comId;
    private String comNombre;
    private Long comPrecio;
    private String comDescripcion;
    private Boolean modificado;

    public ProComidasDto() {
        this.modificado = false;
    }

    /*public ProComidasDto(ProComidas proComidas) {
        this.comId = null;
        this.comNombre = null;
        this.comPrecio = null;
        this.comDescripcion = null;
    }*/

    
    
    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

    public String getComNombre() {
        return comNombre;
    }

    public void setComNombre(String comNombre) {
        this.comNombre = comNombre;
    }

    public Long getComPrecio() {
        return comPrecio;
    }

    public void setComPrecio(Long comPrecio) {
        this.comPrecio = comPrecio;
    }

    public String getComDescripcion() {
        return comDescripcion;
    }

    public void setComDescripcion(String comDescripcion) {
        this.comDescripcion = comDescripcion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
    
}
