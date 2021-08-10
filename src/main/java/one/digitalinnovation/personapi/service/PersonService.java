package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Emerson
 */

@Service
public class PersonService {
    private final PersonRepository personRepository;
    
    // Injeção de Dependência
    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    
    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save( person );
        
        return MessageResponseDTO.builder()
                .message( "Created Person with Id: " + savedPerson.getId() )
                .build();
    }
}
