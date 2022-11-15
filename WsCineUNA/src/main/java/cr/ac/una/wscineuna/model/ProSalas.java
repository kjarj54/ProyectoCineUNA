/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wscineuna.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @SequenceGenerator(name = "PRO_SALAS_SAL_ID_GENERATOR", sequenceName = "CINEUNA.PRO_SALAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_SALAS_SAL_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "SAL_ID")
    private Long salId;
    @Basic(optional = false)
    @Column(name = "SAL_NOMBRE")
    private String salNombre;
    @Basic(optional = false)
    @Column(name = "SAL_ESTADO")
    private String salEstado;
    @Basic(optional = false)
    @Lob
    @Column(name = "SAL_IMGFONDO")
    private Serializable salImgfondo;
    @Basic(optional = false)
    @Version
    @Column(name = "SAL_VERSION")
    private Long salVersion;
    @OneToMany(mappedBy = "salId", fetch = FetchType.LAZY)
    private List<ProAsientos> proAsientosList;
    @OneToMany(mappedBy = "salId", fetch = FetchType.LAZY)
    private List<ProTandas> proTandasList;


    
    public ProSalas() {
    }

    public ProSalas(Long salId) {
        this.salId = salId;
    }

    public ProSalas(Long salId, String salNombre, String salEstado, Serializable salImgfondo) {
        this.salId = salId;
        this.salNombre = salNombre;
        this.salEstado = salEstado;
        this.salImgfondo = salImgfondo;
    }
    
    public ProSalas(ProSalasDto proSalasDto){
        this.salId = proSalasDto.getSalId();
        actualizarSala(proSalasDto);
    }
    
    public void actualizarSala(ProSalasDto proSalasDto){
        this.salEstado = proSalasDto.getsalEstado();
        this.salImgfondo= proSalasDto.getSalImgFondo();
        this.salNombre = proSalasDto.getSalNombre();
    }

    public Long getSalId() {
        return salId;
    }

    public void setSalId(Long salId) {
        this.salId = salId;
    }

    public String getSalNombre() {
        return salNombre;
    }

    public void setSalNombre(String salNombre) {
        this.salNombre = salNombre;
    }

    public String getsalEstado() {
        return salEstado;
    }

    public void setsalEstado(String salEstado) {
        this.salEstado = salEstado;
    }

    public Serializable getSalImgfondo() {
        return salImgfondo;
    }

    public void setSalImgfondo(Serializable salImgfondo) {
        this.salImgfondo = salImgfondo;
    }

    public Long getSalVersion() {
        return salVersion;
    }

    public void setSalVersion(Long salVersion) {
        this.salVersion = salVersion;
    }


    public List<ProAsientos> getProAsientosList() {
        return proAsientosList;
    }

    public void setProAsientosList(List<ProAsientos> proAsientosList) {
        this.proAsientosList = proAsientosList;
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
