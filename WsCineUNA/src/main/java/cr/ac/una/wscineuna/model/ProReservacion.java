/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "PRO_RESERVACION",schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "ProReservacion.findAll", query = "SELECT p FROM ProReservacion p"),
    @NamedQuery(name = "ProReservacion.findByResId", query = "SELECT p FROM ProReservacion p WHERE p.resId = :resId"),
    @NamedQuery(name = "ProReservacion.findByResFecha", query = "SELECT p FROM ProReservacion p WHERE p.resFecha = :resFecha"),
    @NamedQuery(name = "ProReservacion.findByResTotal", query = "SELECT p FROM ProReservacion p WHERE p.resTotal = :resTotal"),
    @NamedQuery(name = "ProReservacion.findByResVersion", query = "SELECT p FROM ProReservacion p WHERE p.resVersion = :resVersion")})
public class ProReservacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_ID")
    private BigDecimal resId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_TOTAL")
    private BigInteger resTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RES_VERSION")
    private BigInteger resVersion;
    @JoinColumn(name = "CLI_ID", referencedColumnName = "CLI_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProClientes cliId;
    @OneToMany(mappedBy = "resId", fetch = FetchType.LAZY)
    private List<ProTandas> proTandasList;

    public ProReservacion() {
    }

    public ProReservacion(BigDecimal resId) {
        this.resId = resId;
    }

    public ProReservacion(BigDecimal resId, Date resFecha, BigInteger resTotal, BigInteger resVersion) {
        this.resId = resId;
        this.resFecha = resFecha;
        this.resTotal = resTotal;
        this.resVersion = resVersion;
    }

    public BigDecimal getResId() {
        return resId;
    }

    public void setResId(BigDecimal resId) {
        this.resId = resId;
    }

    public Date getResFecha() {
        return resFecha;
    }

    public void setResFecha(Date resFecha) {
        this.resFecha = resFecha;
    }

    public BigInteger getResTotal() {
        return resTotal;
    }

    public void setResTotal(BigInteger resTotal) {
        this.resTotal = resTotal;
    }

    public BigInteger getResVersion() {
        return resVersion;
    }

    public void setResVersion(BigInteger resVersion) {
        this.resVersion = resVersion;
    }

    public ProClientes getCliId() {
        return cliId;
    }

    public void setCliId(ProClientes cliId) {
        this.cliId = cliId;
    }

    public List<ProTandas> getProTandasList() {
        return proTandasList;
    }

    public void setProTandasList(List<ProTandas> proTandasList) {
        this.proTandasList = proTandasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resId != null ? resId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProReservacion)) {
            return false;
        }
        ProReservacion other = (ProReservacion) object;
        if ((this.resId == null && other.resId != null) || (this.resId != null && !this.resId.equals(other.resId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.ProReservacion[ resId=" + resId + " ]";
    }
    
}
