/*
 */
package cr.ac.una.Email.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BiblioPZ UNA
 */
@Repository("SecurityToken")
public interface TokenRepository extends CrudRepository<TokenConfirmacion, String> {
    TokenConfirmacion findByTokenConfirmacion(String tokenConfirmacion);
}
