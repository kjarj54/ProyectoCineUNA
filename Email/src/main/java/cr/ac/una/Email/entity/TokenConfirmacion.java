/*
 */
package cr.ac.una.Email.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BiblioPZ UNA
 */
@Entity
@Table(name="PRO_TOKEN",schema = "CINEUNA")
public class TokenConfirmacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tok_id")
    private long tok_id;
    
    @Column(name="token_confirmacion")
    private String tokenConfirmacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "mai_id")
    private UserEntity userEntity;
    
    public TokenConfirmacion(UserEntity userEntity){
        this.userEntity = userEntity;
        fecha = new Date();
    }
    
}
