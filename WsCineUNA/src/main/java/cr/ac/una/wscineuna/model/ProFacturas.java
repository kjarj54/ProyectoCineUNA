/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "PRO_FACTURAS", schema = "CINEUNA")
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
    @SequenceGenerator(name = "PRO_FACTURAS_FAC_ID_GENERATOR", sequenceName = "CINEUNA.PRO_FACTURAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_FACTURAS_FAC_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "FAC_ID")
    private Long facId;
    @Basic(optional = false)
    @Column(name = "FAC_TOTAL")
    private Long facTotal;
    @Basic(optional = false)
    @Column(name = "FAC_FECHA")
    private LocalDate facFecha;
    @Basic(optional = false)
    @Version
    @Column(name = "FAC_VERSION")
    private Long facVersion;
    @JoinTable(name = "PRO_FACTURASCOMIDAS", joinColumns = {
        @JoinColumn(name = "FAC_ID", referencedColumnName = "FAC_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "COM_ID", referencedColumnName = "COM_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProComidas> proComidasList;
    @JoinColumn(name = "CLI_ID", referencedColumnName = "CLI_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProClientes cliId;

    public ProFacturas() {
    }

    public ProFacturas(Long facId) {
        this.facId = facId;
    }

    public ProFacturas(Long facId, Long facTotal, LocalDate facFecha,ProClientesDto proClientesDto) {
        this.facId = facId;
        this.facTotal = facTotal;
        this.facFecha = facFecha;
        this.cliId = new ProClientes();
    }

    public ProFacturas(ProFacturasDto proFacturasDto) {
        this.facId = proFacturasDto.getFacId();
        actualizarFatura(proFacturasDto);
    }

    public void actualizarFatura(ProFacturasDto proFacturasDto) {
        this.facFecha = proFacturasDto.getFacFecha();
        this.facTotal = proFacturasDto.getFacTotal();
        this.cliId = new ProClientes(proFacturasDto.cliId);
        
    }

    public Long getFacId() {
        return facId;
    }

    public void setFacId(Long facId) {
        this.facId = facId;
    }

    public Long getFacTotal() {
        return facTotal;
    }

    public void setFacTotal(Long facTotal) {
        this.facTotal = facTotal;
    }

    public Long getFacVersion() {
        return facVersion;
    }

    public void setFacVersion(Long facVersion) {
        this.facVersion = facVersion;
    }

    public LocalDate getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(LocalDate facFecha) {
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
