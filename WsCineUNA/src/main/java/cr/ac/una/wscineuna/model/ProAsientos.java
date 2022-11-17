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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "PRO_ASIENTOS",schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "ProAsientos.findAll", query = "SELECT p FROM ProAsientos p"),
    @NamedQuery(name = "ProAsientos.findByAsiId", query = "SELECT p FROM ProAsientos p WHERE p.asiId = :asiId"),
    @NamedQuery(name = "ProAsientos.findByAsiNombre", query = "SELECT p FROM ProAsientos p WHERE p.asiNombre = :asiNombre"),
    @NamedQuery(name = "ProAsientos.findByAsiEstado", query = "SELECT p FROM ProAsientos p WHERE p.asiEstado = :asiEstado"),
    @NamedQuery(name = "ProAsientos.findByAsiVersion", query = "SELECT p FROM ProAsientos p WHERE p.asiVersion = :asiVersion")})
public class ProAsientos implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PRO_ASIENTOS_ASI_ID_GENERATOR", sequenceName = "CINEUNA.PRO_ASIENTOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_ASIENTOS_ASI_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ASI_ID")
    private Long asiId;
    @Basic(optional = false)
    @Lob
    @Column(name = "ASI_IMG")
    private Serializable asiImg;
    @Basic(optional = false)
    @Column(name = "ASI_NOMBRE")
    private String asiNombre;
    @Basic(optional = false)
    @Column(name = "ASI_ESTADO")
    private String asiEstado;
    @Basic(optional = false)
    @Version
    @Column(name = "ASI_VERSION")
    private Long asiVersion;
    @JoinColumn(name = "SAL_ID", referencedColumnName = "SAL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProSalas salId;
    @JoinColumn(name = "TAN_ID", referencedColumnName = "TAN_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProTandas tanId;
    

    public ProAsientos() {
    }

    public ProAsientos(Long asiId) {
        this.asiId = asiId;
    }

    public ProAsientos(Long asiId, Serializable asiImg, String asiNombre, String asiEstado) {
        this.asiId = asiId;
        this.asiImg = asiImg;
        this.asiNombre = asiNombre;
        this.asiEstado = asiEstado;
    }
    
    public ProAsientos(ProAsientosDto proAsientosDto){
        this.asiId = proAsientosDto.getAsiId();
        actualizarAsiento(proAsientosDto);
    }
    
    public void actualizarAsiento(ProAsientosDto proAsientosDto){
        this.asiEstado = proAsientosDto.getAsiEstado();
        this.asiImg = proAsientosDto.getAsiImg();
        this.asiNombre = proAsientosDto.getAsiNombre();
    }

    public Long getAsiId() {
        return asiId;
    }

    public void setAsiId(Long asiId) {
        this.asiId = asiId;
    }

    public Serializable getAsiImg() {
        return asiImg;
    }

    public void setAsiImg(Serializable asiImg) {
        this.asiImg = asiImg;
    }

    public String getAsiNombre() {
        return asiNombre;
    }

    public void setAsiNombre(String asiNombre) {
        this.asiNombre = asiNombre;
    }

    public String getAsiEstado() {
        return asiEstado;
    }

    public void setAsiEstado(String asiEstado) {
        this.asiEstado = asiEstado;
    }

    public Long getAsiVersion() {
        return asiVersion;
    }

    public void setAsiVersion(Long asiVersion) {
        this.asiVersion = asiVersion;
    }

    public ProTandas getTanId() {
        return tanId;
    }

    public void setTanId(ProTandas tanId) {
        this.tanId = tanId;
    }

    

    public ProSalas getSalId() {
        return salId;
    }

    public void setSalId(ProSalas salId) {
        this.salId = salId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asiId != null ? asiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProAsientos)) {
            return false;
        }
        ProAsientos other = (ProAsientos) object;
        if ((this.asiId == null && other.asiId != null) || (this.asiId != null && !this.asiId.equals(other.asiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.ProAsientos[ asiId=" + asiId + " ]";
    }
    
}
