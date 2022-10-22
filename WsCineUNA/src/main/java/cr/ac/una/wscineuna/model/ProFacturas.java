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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "PRO_FACTURAS",schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "ProFacturas.findAll", query = "SELECT p FROM ProFacturas p"),
    @NamedQuery(name = "ProFacturas.findByFacId", query = "SELECT p FROM ProFacturas p WHERE p.facId = :facId"),
    @NamedQuery(name = "ProFacturas.findByFacTotal", query = "SELECT p FROM ProFacturas p WHERE p.facTotal = :facTotal"),
    @NamedQuery(name = "ProFacturas.findByFacVersion", query = "SELECT p FROM ProFacturas p WHERE p.facVersion = :facVersion"),
    @NamedQuery(name = "ProFacturas.findByFacFecha", query = "SELECT p FROM ProFacturas p WHERE p.facFecha = :facFecha")})
public class ProFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_ID")
    private BigDecimal facId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_TOTAL")
    private BigInteger facTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_VERSION")
    private BigInteger facVersion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAC_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date facFecha;
    @JoinTable(name = "PRO_FATURASCOMIDAS", joinColumns = {
        @JoinColumn(name = "FAC_ID", referencedColumnName = "FAC_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProComidas> proComidasList;
    @JoinColumn(name = "CLI_ID", referencedColumnName = "CLI_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProClientes cliId;

    public ProFacturas() {
    }

    public ProFacturas(BigDecimal facId) {
        this.facId = facId;
    }

    public ProFacturas(BigDecimal facId, BigInteger facTotal, BigInteger facVersion, Date facFecha) {
        this.facId = facId;
        this.facTotal = facTotal;
        this.facVersion = facVersion;
        this.facFecha = facFecha;
    }

    public BigDecimal getFacId() {
        return facId;
    }

    public void setFacId(BigDecimal facId) {
        this.facId = facId;
    }

    public BigInteger getFacTotal() {
        return facTotal;
    }

    public void setFacTotal(BigInteger facTotal) {
        this.facTotal = facTotal;
    }

    public BigInteger getFacVersion() {
        return facVersion;
    }

    public void setFacVersion(BigInteger facVersion) {
        this.facVersion = facVersion;
    }

    public Date getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(Date facFecha) {
        this.facFecha = facFecha;
    }

    public List<ProComidas> getProComidasList() {
        return proComidasList;
    }

    public void setProComidasList(List<ProComidas> proComidasList) {
        this.proComidasList = proComidasList;
    }

    public ProClientes getCliId() {
        return cliId;
    }

    public void setCliId(ProClientes cliId) {
        this.cliId = cliId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facId != null ? facId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProFacturas)) {
            return false;
        }
        ProFacturas other = (ProFacturas) object;
        if ((this.facId == null && other.facId != null) || (this.facId != null && !this.facId.equals(other.facId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.ProFacturas[ facId=" + facId + " ]";
    }
    
}
