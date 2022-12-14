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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "PRO_CLIENTES",schema = "CINEUNA")
@NamedQueries({
    @NamedQuery(name = "ProClientes.findAll", query = "SELECT p FROM ProClientes p"),
    @NamedQuery(name = "ProClientes.findByCliId", query = "SELECT p FROM ProClientes p WHERE p.cliId = :cliId"),
    @NamedQuery(name = "ProClientes.findByCliUsuario", query = "SELECT p FROM ProClientes p WHERE p.cliUsuario = :cliUsuario"),
    @NamedQuery(name = "ProClientes.findByCliClave", query = "SELECT p FROM ProClientes p WHERE p.cliClave = :cliClave"),
    @NamedQuery(name = "ProClientes.findByCliNombre", query = "SELECT p FROM ProClientes p WHERE p.cliNombre = :cliNombre"),
    @NamedQuery(name = "ProClientes.findByCliPapellido", query = "SELECT p FROM ProClientes p WHERE p.cliPapellido = :cliPapellido"),
    @NamedQuery(name = "ProClientes.findByCliCorreo", query = "SELECT p FROM ProClientes p WHERE p.cliCorreo = :cliCorreo"),
    @NamedQuery(name = "ProClientes.findByCliIdioma", query = "SELECT p FROM ProClientes p WHERE p.cliIdioma = :cliIdioma"),
    @NamedQuery(name = "ProClientes.findByCliEstado", query = "SELECT p FROM ProClientes p WHERE p.cliEstado = :cliEstado"),
    @NamedQuery(name = "ProClientes.findByCliAdmin", query = "SELECT p FROM ProClientes p WHERE p.cliAdmin = :cliAdmin"),
    @NamedQuery(name = "ProClientes.findByUsuClave", query = "SELECT p FROM ProClientes p WHERE p.cliUsuario = :cliUsuario and p.cliClave = :cliClave", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "ProClientes.findByCliSapellido", query = "SELECT p FROM ProClientes p WHERE p.cliSapellido = :cliSapellido"),
    @NamedQuery(name = "ProClientes.findByCliVersion", query = "SELECT p FROM ProClientes p WHERE p.cliVersion = :cliVersion")})
public class ProClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "PRO_CLIENTES_CLI_ID_GENERATOR", sequenceName = "CINEUNA.PRO_ClIENTES_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_CLIENTES_CLI_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "CLI_ID")
    private Long cliId;
    @Basic(optional = false)
    @Column(name = "CLI_USUARIO")
    private String cliUsuario;
    @Basic(optional = false)
    @Column(name = "CLI_CLAVERESTAURADA")
    private String cliClaverestaurada;
    @Basic(optional = false)
    @Column(name = "CLI_CLAVE")
    private String cliClave;
    @Basic(optional = false)
    @Column(name = "CLI_NOMBRE")
    private String cliNombre;
    @Basic(optional = false)
    @Column(name = "CLI_PAPELLIDO")
    private String cliPapellido;
    @Basic(optional = false)
    @Column(name = "CLI_CORREO")
    private String cliCorreo;
    @Basic(optional = false)
    @Column(name = "CLI_IDIOMA")
    private String cliIdioma;
    @Basic(optional = false)
    @Column(name = "CLI_ESTADO")
    private String cliEstado;
    @Basic(optional = false)
    @Column(name = "CLI_ADMIN")
    private String cliAdmin;
    @Column(name = "CLI_SAPELLIDO")
    private String cliSapellido;
    @Version
    @Column(name = "CLI_VERSION")
    private Long cliVersion;
    @OneToMany(mappedBy = "cliId", fetch = FetchType.LAZY)
    private List<ProReservacion> proReservacionList;
    @OneToMany(mappedBy = "cliId", fetch = FetchType.LAZY)
    private List<ProFacturas> proFacturasList;

    public ProClientes() {
    }

    public ProClientes(Long cliId) {
        this.cliId = cliId;
    }

    public ProClientes(Long cliId, String cliUsuario, String cliClave, String cliNombre, String cliPapellido, String cliCorreo, String cliIdioma, String cliEstado, String cliAdmin,String cliSapellido,String cliClaverestaurada) {
        this.cliId = cliId;
        this.cliUsuario = cliUsuario;
        this.cliClave = cliClave;
        this.cliClaverestaurada = cliClaverestaurada;
        this.cliNombre = cliNombre;
        this.cliPapellido = cliPapellido;
        this.cliCorreo = cliCorreo;
        this.cliIdioma = cliIdioma;
        this.cliEstado = cliEstado;
        this.cliAdmin = cliAdmin;
        this.cliSapellido = cliSapellido;
    }

    public ProClientes(ProClientesDto proClientesDto) {
        this.cliId = proClientesDto.getCliId();
        actualizarCliente(proClientesDto);
    }

    public void actualizarCliente(ProClientesDto proClientesDto) {
        this.cliUsuario = proClientesDto.getCliUsuario();
        this.cliClave = proClientesDto.getCliClave();
        this.cliClaverestaurada = proClientesDto.getCliClaverestaurada();
        this.cliNombre = proClientesDto.getCliNombre();
        this.cliPapellido = proClientesDto.getCliPApellido();
        this.cliCorreo = proClientesDto.getCliCorreo();
        this.cliIdioma = proClientesDto.getCliIdioma();
        this.cliEstado = proClientesDto.getCliEstado();
        this.cliAdmin = proClientesDto.getCliAdmin();
        this.cliSapellido = proClientesDto.getCliSApellido();
    }

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliUsuario() {
        return cliUsuario;
    }

    public void setCliUsuario(String cliUsuario) {
        this.cliUsuario = cliUsuario;
    }

    public String getCliClave() {
        return cliClave;
    }

    public void setCliClave(String cliClave) {
        this.cliClave = cliClave;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliPapellido() {
        return cliPapellido;
    }

    public void setCliPapellido(String cliPapellido) {
        this.cliPapellido = cliPapellido;
    }

    public String getCliCorreo() {
        return cliCorreo;
    }

    public void setCliCorreo(String cliCorreo) {
        this.cliCorreo = cliCorreo;
    }

    public String getCliIdioma() {
        return cliIdioma;
    }

    public void setCliIdioma(String cliIdioma) {
        this.cliIdioma = cliIdioma;
    }

    public String getCliEstado() {
        return cliEstado;
    }

    public void setCliEstado(String cliEstado) {
        this.cliEstado = cliEstado;
    }

    public String getCliAdmin() {
        return cliAdmin;
    }

    public void setCliAdmin(String cliAdmin) {
        this.cliAdmin = cliAdmin;
    }

    public String getCliSapellido() {
        return cliSapellido;
    }

    public void setCliSapellido(String cliSapellido) {
        this.cliSapellido = cliSapellido;
    }

    public Long getCliVersion() {
        return cliVersion;
    }

    public void setCliVersion(Long cliVersion) {
        this.cliVersion = cliVersion;
    }


    public List<ProReservacion> getProReservacionList() {
        return proReservacionList;
    }

    public void setProReservacionList(List<ProReservacion> proReservacionList) {
        this.proReservacionList = proReservacionList;
    }

    public List<ProFacturas> getProFacturasList() {
        return proFacturasList;
    }

    public void setProFacturasList(List<ProFacturas> proFacturasList) {
        this.proFacturasList = proFacturasList;
    }

    public String getCliClaverestaurada() {
        return cliClaverestaurada;
    }

    public void setCliClaverestaurada(String cliClaverestaurada) {
        this.cliClaverestaurada = cliClaverestaurada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cliId != null ? cliId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProClientes)) {
            return false;
        }
        ProClientes other = (ProClientes) object;
        if ((this.cliId == null && other.cliId != null) || (this.cliId != null && !this.cliId.equals(other.cliId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wscineuna.model.ProClientes[ cliId=" + cliId + " ]";
    }

}
