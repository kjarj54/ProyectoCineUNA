/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "PRO_COMIDAS",schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "ProComidas.findAll", query = "SELECT p FROM ProComidas p"),
    @NamedQuery(name = "ProComidas.findByComId", query = "SELECT p FROM ProComidas p WHERE p.comId = :comId"),
    @NamedQuery(name = "ProComidas.findByComNombre", query = "SELECT p FROM ProComidas p WHERE p.comNombre = :comNombre"),
    @NamedQuery(name = "ProComidas.findByComPrecio", query = "SELECT p FROM ProComidas p WHERE p.comPrecio = :comPrecio"),
    @NamedQuery(name = "ProComidas.findByComDescripcion", query = "SELECT p FROM ProComidas p WHERE p.comDescripcion = :comDescripcion"),
    @NamedQuery(name = "ProComidas.findByComVersion", query = "SELECT p FROM ProComidas p WHERE p.comVersion = :comVersion")})
public class ProComidas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "PRO_COMIDAS_COM_ID_GENERATOR", sequenceName = "CINEUNA.PRO_COMIDAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_COMIDAS_COM_ID_GENERATOR")
    @Column(name = "COM_ID")
    private Long comId;
    @Basic(optional = false)
    @Column(name = "COM_NOMBRE")
    private String comNombre;
    @Basic(optional = false)
    @Column(name = "COM_PRECIO")
    private Long comPrecio;
    @Column(name = "COM_DESCRIPCION")
    private String comDescripcion;
    @Basic(optional = false)
    @Version
    @Column(name = "COM_VERSION")
    private Long comVersion;
    @ManyToMany(mappedBy = "proComidasList", fetch = FetchType.LAZY)
    private List<ProFacturas> proFacturasList;

    public ProComidas() {
    }

    public ProComidas(Long comId) {
        this.comId = comId;
    }

    public ProComidas(Long comId, String comNombre, Long comPrecio) {
        this.comId = comId;
        this.comNombre = comNombre;
        this.comPrecio = comPrecio;
    }
    
    public ProComidas(ProComidasDto proComidasDto){
        this.comId = proComidasDto.getComId();
        actualizarComida(proComidasDto);
    }
    
    public void actualizarComida(ProComidasDto proComidasDto){
        this.comDescripcion = proComidasDto.getComDescripcion();
        this.comNombre = proComidasDto.getComNombre();
        this.comPrecio = proComidasDto.getComPrecio();
    }

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

    public Long getComVersion() {
        return comVersion;
    }

    public void setComVersion(Long comVersion) {
        this.comVersion = comVersion;
    }

    public List<ProFacturas> getProFacturasList() {
        return proFacturasList;
    }

    public void setProFacturasList(List<ProFacturas> proFacturasList) {
        this.proFacturasList = proFacturasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comId != null ? comId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProComidas)) {
            return false;
        }
        ProComidas other = (ProComidas) object;
        if ((this.comId == null && other.comId != null) || (this.comId != null && !this.comId.equals(other.comId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.ProComidas[ comId=" + comId + " ]";
    }
    
}
