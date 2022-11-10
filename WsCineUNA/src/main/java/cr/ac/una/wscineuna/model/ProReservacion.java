/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

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
    @SequenceGenerator(name = "PRO_RESERVACION_RES_ID_GENERATOR", sequenceName = "CINEUNA.PRO_RESERVACION_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_RESERVACION_RES_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "RES_ID")
    private Long resId;
    @Basic(optional = false)
    @Column(name = "RES_FECHA")
    private LocalDate resFecha;
    @Basic(optional = false)
    @Column(name = "RES_TOTAL")
    private Long resTotal;
    @Basic(optional = false)
    @Version
    @Column(name = "RES_VERSION")
    private Long resVersion;
    @JoinColumn(name = "CLI_ID", referencedColumnName = "CLI_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProClientes cliId;
    @OneToMany(mappedBy = "resId", fetch = FetchType.LAZY)
    private List<ProTandas> proTandasList;

    public ProReservacion() {
    }

    public ProReservacion(Long resId) {
        this.resId = resId;
    }

    public ProReservacion(Long resId, LocalDate resFecha, Long resTotal, Long resVersion) {
        this.resId = resId;
        this.resFecha = resFecha;
        this.resTotal = resTotal;
        this.resVersion = resVersion;
    }
    
    public ProReservacion(ProReservacionDto proReservacionDto){
       this.resId = proReservacionDto.getResId();
       actualizarReservacion(proReservacionDto);
    }
    
    
    public void actualizarReservacion(ProReservacionDto proReservacionDto){
        this.resFecha = proReservacionDto.getResFecha();
        this.resTotal= proReservacionDto.getResTotal();
    }
    

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public LocalDate getResFecha() {
        return resFecha;
    }

    public void setResFecha(LocalDate resFecha) {
        this.resFecha = resFecha;
    }

    public Long getResTotal() {
        return resTotal;
    }

    public void setResTotal(Long resTotal) {
        this.resTotal = resTotal;
    }

    public Long getResVersion() {
        return resVersion;
    }

    public void setResVersion(Long resVersion) {
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
