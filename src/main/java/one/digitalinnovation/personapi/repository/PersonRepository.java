package one.digitalinnovation.personapi.repository;

import java.util.Optional;
import one.digitalinnovation.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Emerson
 */
public interface PersonRepository extends JpaRepository<Person, Long>{
    Optional<Person> findByName(String Name);
}
