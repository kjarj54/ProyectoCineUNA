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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "PRO_PELICULAS", schema = "CINEUNA")
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
    @SequenceGenerator(name = "PRO_PELICULAS_PEL_ID_GENERATOR", sequenceName = "CINEUNA.PRO_PELICULAS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_PELICULAS_PEL_ID_GENERATOR")
    @Column(name = "PEL_ID")
    private Long pelId;
    @Basic(optional = false)
    @Column(name = "PEL_NOMBRE")
    private String pelNombre;
    @Column(name = "PEL_SYNOPSIS")
    private String pelSynopsis;
    @Basic(optional = false)
    @Column(name = "PEL_LINK")
    private String pelLink;
    @Basic(optional = false)
    @Column(name = "PEL_IDIOMA")
    private String pelIdioma;
    @Basic(optional = false)
    @Lob
    @Column(name = "PEL_IMAGEN")
    private Serializable pelImagen;
    @Basic(optional = false)
    @Column(name = "PEL_FECHAESTRENO")
    private LocalDate pelFechaestreno;
    @Basic(optional = false)
    @Column(name = "PEL_ESTADO")
    private String pelEstado;
    @Basic(optional = false)
    @Version
    @Column(name = "PEL_VERSION")
    private Long pelVersion;
    @JoinTable(name = "PRO_SALASPELICULAS", joinColumns = {
        @JoinColumn(name = "PEL_ID", referencedColumnName = "PEL_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "SAL_ID", referencedColumnName = "SAL_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProSalas> proSalasList;
    @OneToMany(mappedBy = "pelId", fetch = FetchType.LAZY)
    private List<ProTandas> proTandasList;

    public ProPeliculas() {
    }

    public ProPeliculas(Long pelId) {
        this.pelId = pelId;
    }

    public ProPeliculas(Long pelId, String pelNombre, String pelLink, Serializable pelImagen, LocalDate pelFechaestreno, String pelEstado) {
        this.pelId = pelId;
        this.pelNombre = pelNombre;
        this.pelLink = pelLink;
        this.pelImagen = pelImagen;
        this.pelFechaestreno = pelFechaestreno;
        this.pelEstado = pelEstado;
    }
    
    public ProPeliculas(ProPeliculasDto proPeliculasDto){
        this.pelId = proPeliculasDto.getPelId();
        
    }
    
    public void actualizarPelicula(ProPeliculasDto proPeliculasDto){
        this.pelEstado = proPeliculasDto.getPelEstado();
        this.pelFechaestreno = proPeliculasDto.getPelFechaestreno();
        this.pelImagen = proPeliculasDto.getPelImagen();
        this.pelLink = proPeliculasDto.getPelLink();
        this.pelNombre = proPeliculasDto.getPelNombre();
        this.pelSynopsis = proPeliculasDto.getPelSynopsis();
    }

    public Long getPelId() {
        return pelId;
    }

    public void setPelId(Long pelId) {
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

    public LocalDate getPelFechaestreno() {
        return pelFechaestreno;
    }

    public void setPelFechaestreno(LocalDate pelFechaestreno) {
        this.pelFechaestreno = pelFechaestreno;
    }

    public String getPelEstado() {
        return pelEstado;
    }

    public void setPelEstado(String pelEstado) {
        this.pelEstado = pelEstado;
    }

    public Long getPelVersion() {
        return pelVersion;
    }

    public void setPelVersion(Long pelVersion) {
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

    public String getPelIdioma() {
        return pelIdioma;
    }

    public void setPelIdioma(String pelIdioma) {
        this.pelIdioma = pelIdioma;
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
