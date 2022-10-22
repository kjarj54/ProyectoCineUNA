/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull
    @Column(name = "COM_ID")
    private BigDecimal comId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "COM_NOMBRE")
    private String comNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "COM_PRECIO")
    private String comPrecio;
    @Size(max = 30)
    @Column(name = "COM_DESCRIPCION")
    private String comDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COM_VERSION")
    private BigInteger comVersion;
    @ManyToMany(mappedBy = "proComidasList", fetch = FetchType.LAZY)
    private List<ProFacturas> proFacturasList;

    public ProComidas() {
    }

    public ProComidas(BigDecimal comId) {
        this.comId = comId;
    }

    public ProComidas(BigDecimal comId, String comNombre, String comPrecio, BigInteger comVersion) {
        this.comId = comId;
        this.comNombre = comNombre;
        this.comPrecio = comPrecio;
        this.comVersion = comVersion;
    }

    public BigDecimal getComId() {
        return comId;
    }

    public void setComId(BigDecimal comId) {
        this.comId = comId;
    }

    public String getComNombre() {
        return comNombre;
    }

    public void setComNombre(String comNombre) {
        this.comNombre = comNombre;
    }

    public String getComPrecio() {
        return comPrecio;
    }

    public void setComPrecio(String comPrecio) {
        this.comPrecio = comPrecio;
    }

    public String getComDescripcion() {
        return comDescripcion;
    }

    public void setComDescripcion(String comDescripcion) {
        this.comDescripcion = comDescripcion;
    }

    public BigInteger getComVersion() {
        return comVersion;
    }

    public void setComVersion(BigInteger comVersion) {
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
