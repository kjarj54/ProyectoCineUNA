/*
 */
package cr.ac.una.Email.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author BiblioPZ UNA
 */
@Entity
@Table(name = "PRO_MAIL",schema = "CINEUNA")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="mai_id")
    private long id;
    
    private String email;
    
    private String clave;
    
    @Column(name="mai_nombre")
    private String mai_nombre;
    
    @Column(name="mai_pApellido")
    private String mai_pApellido;
    
    private boolean isEnabled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMai_pApellido() {
        return mai_pApellido;
    }

    public void setMai_pApellido(String mai_pApellido) {
        this.mai_pApellido = mai_pApellido;
    }

    public String getMai_nombre() {
        return mai_nombre;
    }

    public void setMai_nombre(String mai_nombre) {
        this.mai_nombre = mai_nombre;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
}
