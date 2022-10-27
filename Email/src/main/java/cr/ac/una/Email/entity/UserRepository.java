/*
 */
package cr.ac.una.Email.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author BiblioPZ UNA
 */
@Repository("userRepository")
public interface UserRepository extends CrudRepository<UserEntity, String> {
    UserEntity findByEmailIdIgnoreCase(String email);
}
