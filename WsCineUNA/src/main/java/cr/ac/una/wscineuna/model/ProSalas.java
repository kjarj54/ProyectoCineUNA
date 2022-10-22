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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "PRO_SALAS",schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "ProSalas.findAll", query = "SELECT p FROM ProSalas p"),
    @NamedQuery(name = "ProSalas.findBySalId", query = "SELECT p FROM ProSalas p WHERE p.salId = :salId"),
    @NamedQuery(name = "ProSalas.findBySalNombre", query = "SELECT p FROM ProSalas p WHERE p.salNombre = :salNombre"),
    @NamedQuery(name = "ProSalas.findBySalEstado", query = "SELECT p FROM ProSalas p WHERE p.salEstado = :salEstado"),
    @NamedQuery(name = "ProSalas.findBySalVersion", query = "SELECT p FROM ProSalas p WHERE p.salVersion = :salVersion")})
public class ProSalas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAL_ID")
    private BigDecimal salId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "SAL_NOMBRE")
    private String salNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "SAL_ESTADO")
    private String salEstado;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "SAL_IMGFONDO")
    private Serializable salImgfondo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SAL_VERSION")
    private BigInteger salVersion;
    @ManyToMany(mappedBy = "proSalasList", fetch = FetchType.LAZY)
    private List<ProPeliculas> proPeliculasList;
    @OneToMany(mappedBy = "salId", fetch = FetchType.LAZY)
    private List<ProAsientos> proAsientosList;
    @JoinColumn(name = "CLI_ID", referencedColumnName = "CLI_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProClientes cliId;

    public ProSalas() {
    }

    public ProSalas(BigDecimal salId) {
        this.salId = salId;
    }

    public ProSalas(BigDecimal salId, String salNombre, String salEstado, Serializable salImgfondo, BigInteger salVersion) {
        this.salId = salId;
        this.salNombre = salNombre;
        this.salEstado = salEstado;
        this.salImgfondo = salImgfondo;
        this.salVersion = salVersion;
    }

    public BigDecimal getSalId() {
        return salId;
    }

    public void setSalId(BigDecimal salId) {
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

    public Serializable getSalImgfondo() {
        return salImgfondo;
    }

    public void setSalImgfondo(Serializable salImgfondo) {
        this.salImgfondo = salImgfondo;
    }

    public BigInteger getSalVersion() {
        return salVersion;
    }

    public void setSalVersion(BigInteger salVersion) {
        this.salVersion = salVersion;
    }

    public List<ProPeliculas> getProPeliculasList() {
        return proPeliculasList;
    }

    public void setProPeliculasList(List<ProPeliculas> proPeliculasList) {
        this.proPeliculasList = proPeliculasList;
    }

    public List<ProAsientos> getProAsientosList() {
        return proAsientosList;
    }

    public void setProAsientosList(List<ProAsientos> proAsientosList) {
        this.proAsientosList = proAsientosList;
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
        hash += (salId != null ? salId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProSalas)) {
            return false;
        }
        ProSalas other = (ProSalas) object;
        if ((this.salId == null && other.salId != null) || (this.salId != null && !this.salId.equals(other.salId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.ProSalas[ salId=" + salId + " ]";
    }
    
}
