package one.digitalinnovation.personapi.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Emerson
 */

@Service
@AllArgsConstructor( onConstructor = @__( @Autowired ) )
public class PersonService {
    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;
    
    // Injeção de Dependência
//    @Autowired
//    public PersonService(PersonRepository personRepository){
//        this.personRepository = personRepository;
//        this.personMapper = PersonMapper.INSTANCE;
//    }
    
    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }
    
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> listAll = personRepository.findAll();
        
        return listAll.stream().
                map( personMapper::toDTO )
                .collect( Collectors.toList() );
    }
}
