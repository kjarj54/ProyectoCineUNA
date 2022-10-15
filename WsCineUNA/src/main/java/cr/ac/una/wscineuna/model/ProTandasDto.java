/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

/**
 *
 * @author kevin
 */
public class ProTandasDto {

    private Long tanId;
    private String tanNombre;
    private Boolean modificado;

    public ProTandasDto() {
        this.modificado = false;
    }

    /*public ProTandasDto(ProTandas proTandas) {
        this();
        this.tanId = tanId;
        this.tanNombre = tanNombre;
    }*/
    
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

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

}
