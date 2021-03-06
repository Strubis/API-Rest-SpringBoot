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
    
    public PersonDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        
        return personMapper.toDTO(savedPerson);
    }
    
    public List<PersonDTO> listAll() {
        List<Person> listAll = personRepository.findAll();
        
        return listAll.stream().
                map( personMapper::toDTO )
                .collect( Collectors.toList() );
    }

    public PersonDTO listById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists( id );
        
        return personMapper.toDTO( person );
    }

    public void deleteById(Long id) throws PersonNotFoundException{
        Person person = verifyIfExists( id );
        
        personRepository.deleteById( id );
    }
    
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists( id );
        
        Person personToUpdate = personMapper.toModel( personDTO );
        Person updatedPerson = personRepository.save( personToUpdate );
        
        return createMessageResponse( updatedPerson.getId(), "Update person with ID" );
    }
    
    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById( id )
                .orElseThrow( () -> new PersonNotFoundException( id ) );
    }
    
    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
