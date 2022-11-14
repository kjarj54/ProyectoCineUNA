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
@Table(name = "PRO_TANDAS",schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "ProTandas.findAll", query = "SELECT p FROM ProTandas p"),
    @NamedQuery(name = "ProTandas.findByTanId", query = "SELECT p FROM ProTandas p WHERE p.tanId = :tanId"),
    @NamedQuery(name = "ProTandas.findByTanNombre", query = "SELECT p FROM ProTandas p WHERE p.tanNombre = :tanNombre"),
    @NamedQuery(name = "ProTandas.findByTanHorainicio", query = "SELECT p FROM ProTandas p WHERE p.tanHorainicio = :tanHorainicio"),
    @NamedQuery(name = "ProTandas.findByTanHorafinal", query = "SELECT p FROM ProTandas p WHERE p.tanHorafinal = :tanHorafinal"),
    @NamedQuery(name = "ProTandas.findByTanFecha", query = "SELECT p FROM ProTandas p WHERE p.tanFecha = :tanFecha"),
    @NamedQuery(name = "ProTandas.findByTanPrecio", query = "SELECT p FROM ProTandas p WHERE p.tanPrecio = :tanPrecio"),
    @NamedQuery(name = "ProTandas.findByTanVersion", query = "SELECT p FROM ProTandas p WHERE p.tanVersion = :tanVersion")})
public class ProTandas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PRO_TANDAS_TAN_ID_GENERATOR", sequenceName = "CINEUNA.PRO_TANDAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_TANDAS_TAN_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "TAN_ID")
    private Long tanId;
    @Basic(optional = false)
    @Column(name = "TAN_NOMBRE")
    private String tanNombre;
    @Basic(optional = false)
    @Column(name = "TAN_HORAINICIO")
    private String tanHorainicio;
    @Basic(optional = false)
    @Column(name = "TAN_HORAFINAL")
    private String tanHorafinal;
    @Basic(optional = false)
    @Column(name = "TAN_FECHA")
    private LocalDate tanFecha;
    @Basic(optional = false)
    @Column(name = "TAN_PRECIO")
    private Long tanPrecio;
    @Basic(optional = false)
    @Version
    @Column(name = "TAN_VERSION")
    private Long tanVersion;
    @ManyToMany(mappedBy = "proTandasList", fetch = FetchType.LAZY)
    private List<ProAsientos> proAsientosList;
    @JoinColumn(name = "PEL_ID", referencedColumnName = "PEL_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProPeliculas pelId;
    @JoinColumn(name = "RES_ID", referencedColumnName = "RES_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProReservacion resId;

    public ProTandas() {
    }

    public ProTandas(Long tanId) {
        this.tanId = tanId;
    }

    public ProTandas(Long tanId, String tanNombre, String tanHorainicio, String tanHorafinal, LocalDate tanFecha, Long tanPrecio, ProPeliculasDto peli) {
        this.tanId = tanId;
        this.tanNombre = tanNombre;
        this.tanHorainicio = tanHorainicio;
        this.tanHorafinal = tanHorafinal;
        this.tanFecha = tanFecha;
        this.tanPrecio = tanPrecio;
        this.pelId = new ProPeliculas();
        
    }
    
    public ProTandas(ProTandasDto proTandasDto){
        this.tanId = proTandasDto.getTanId();
        actualizarTanda(proTandasDto);
    }

    public void actualizarTanda(ProTandasDto proTandasDto){
        this.tanFecha = proTandasDto.getTanFecha();
        this.tanHorafinal  = proTandasDto.getTanHorafinal();
        this.tanHorainicio = proTandasDto.getTanHorainicio();
        this.tanNombre = proTandasDto.getTanNombre();
        this.tanPrecio = proTandasDto.getTanPrecio();
        this.pelId = new ProPeliculas(proTandasDto.getPelId());
        
    }
    
    public Long getTanId() {
        return tanId;
    }

    public void setTanId(Long tanId) {
        this.tanId = tanId;
    }

    public String getTanNombre() {
        return tanNombre;
    }

    public void setTanNombre(String tanNombre) {
        this.tanNombre = tanNombre;
    }

    public String getTanHorainicio() {
        return tanHorainicio;
    }

    public void setTanHorainicio(String tanHorainicio) {
        this.tanHorainicio = tanHorainicio;
    }

    public String getTanHorafinal() {
        return tanHorafinal;
    }

    public void setTanHorafinal(String tanHorafinal) {
        this.tanHorafinal = tanHorafinal;
    }

    public LocalDate getTanFecha() {
        return tanFecha;
    }

    public void setTanFecha(LocalDate tanFecha) {
        this.tanFecha = tanFecha;
    }

    public Long getTanPrecio() {
        return tanPrecio;
    }

    public void setTanPrecio(Long tanPrecio) {
        this.tanPrecio = tanPrecio;
    }

    public Long getTanVersion() {
        return tanVersion;
    }

    public void setTanVersion(Long tanVersion) {
        this.tanVersion = tanVersion;
    }

    public List<ProAsientos> getProAsientosList() {
        return proAsientosList;
    }

    public void setProAsientosList(List<ProAsientos> proAsientosList) {
        this.proAsientosList = proAsientosList;
    }

    public ProPeliculas getPelId() {
        return pelId;
    }

    public void setPelId(ProPeliculas pelId) {
        this.pelId = pelId;
    }

    public ProReservacion getResId() {
        return resId;
    }

    public void setResId(ProReservacion resId) {
        this.resId = resId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tanId != null ? tanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProTandas)) {
            return false;
        }
        ProTandas other = (ProTandas) object;
        if ((this.tanId == null && other.tanId != null) || (this.tanId != null && !this.tanId.equals(other.tanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.ProTandas[ tanId=" + tanId + " ]";
    }
    
}
