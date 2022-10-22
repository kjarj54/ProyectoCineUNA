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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "PRO_PELICULAS",schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "ProPeliculas.findAll", query = "SELECT p FROM ProPeliculas p"),
    @NamedQuery(name = "ProPeliculas.findByPelId", query = "SELECT p FROM ProPeliculas p WHERE p.pelId = :pelId"),
    @NamedQuery(name = "ProPeliculas.findByPelNombre", query = "SELECT p FROM ProPeliculas p WHERE p.pelNombre = :pelNombre"),
    @NamedQuery(name = "ProPeliculas.findByPelSynopsis", query = "SELECT p FROM ProPeliculas p WHERE p.pelSynopsis = :pelSynopsis"),
    @NamedQuery(name = "ProPeliculas.findByPelLink", query = "SELECT p FROM ProPeliculas p WHERE p.pelLink = :pelLink"),
    @NamedQuery(name = "ProPeliculas.findByPelFechaestreno", query = "SELECT p FROM ProPeliculas p WHERE p.pelFechaestreno = :pelFechaestreno"),
    @NamedQuery(name = "ProPeliculas.findByPelEstado", query = "SELECT p FROM ProPeliculas p WHERE p.pelEstado = :pelEstado"),
    @NamedQuery(name = "ProPeliculas.findByPelVersion", query = "SELECT p FROM ProPeliculas p WHERE p.pelVersion = :pelVersion")})
public class ProPeliculas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEL_ID")
    private BigDecimal pelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PEL_NOMBRE")
    private String pelNombre;
    @Size(max = 30)
    @Column(name = "PEL_SYNOPSIS")
    private String pelSynopsis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "PEL_LINK")
    private String pelLink;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "PEL_IMAGEN")
    private Serializable pelImagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEL_FECHAESTRENO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pelFechaestreno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "PEL_ESTADO")
    private String pelEstado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PEL_VERSION")
    private BigInteger pelVersion;
    @JoinTable(name = "PRO_SALASPELICULAS", joinColumns = {
        @JoinColumn(name = "PEL_ID", referencedColumnName = "PEL_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "SAL_ID", referencedColumnName = "SAL_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProSalas> proSalasList;
    @OneToMany(mappedBy = "pelId", fetch = FetchType.LAZY)
    private List<ProTandas> proTandasList;

    public ProPeliculas() {
    }

    public ProPeliculas(BigDecimal pelId) {
        this.pelId = pelId;
    }

    public ProPeliculas(BigDecimal pelId, String pelNombre, String pelLink, Serializable pelImagen, Date pelFechaestreno, String pelEstado, BigInteger pelVersion) {
        this.pelId = pelId;
        this.pelNombre = pelNombre;
        this.pelLink = pelLink;
        this.pelImagen = pelImagen;
        this.pelFechaestreno = pelFechaestreno;
        this.pelEstado = pelEstado;
        this.pelVersion = pelVersion;
    }

    public BigDecimal getPelId() {
        return pelId;
    }

    public void setPelId(BigDecimal pelId) {
        this.pelId = pelId;
    }

    public String getPelNombre() {
        return pelNombre;
    }

    public void setPelNombre(String pelNombre) {
        this.pelNombre = pelNombre;
    }

    public String getPelSynopsis() {
        return pelSynopsis;
    }

    public void setPelSynopsis(String pelSynopsis) {
        this.pelSynopsis = pelSynopsis;
    }

    public String getPelLink() {
        return pelLink;
    }

    public void setPelLink(String pelLink) {
        this.pelLink = pelLink;
    }

    public Serializable getPelImagen() {
        return pelImagen;
    }

    public void setPelImagen(Serializable pelImagen) {
        this.pelImagen = pelImagen;
    }

    public Date getPelFechaestreno() {
        return pelFechaestreno;
    }

    public void setPelFechaestreno(Date pelFechaestreno) {
        this.pelFechaestreno = pelFechaestreno;
    }

    public String getPelEstado() {
        return pelEstado;
    }

    public void setPelEstado(String pelEstado) {
        this.pelEstado = pelEstado;
    }

    public BigInteger getPelVersion() {
        return pelVersion;
    }

    public void setPelVersion(BigInteger pelVersion) {
        this.pelVersion = pelVersion;
    }

    public List<ProSalas> getProSalasList() {
        return proSalasList;
    }

    public void setProSalasList(List<ProSalas> proSalasList) {
        this.proSalasList = proSalasList;
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
        hash += (pelId != null ? pelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProPeliculas)) {
            return false;
        }
        ProPeliculas other = (ProPeliculas) object;
        if ((this.pelId == null && other.pelId != null) || (this.pelId != null && !this.pelId.equals(other.pelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.ProPeliculas[ pelId=" + pelId + " ]";
    }
    
}
