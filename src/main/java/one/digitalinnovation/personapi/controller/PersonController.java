package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emerson
 */

@RestController
@RequestMapping( "/api/v1/people" )
public class PersonController {
    private PersonRepository personRepository;
    
    // Injeção de Dependência
    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }
    
    /**
     * Método para criar um novo "objeto" para o nosso banco de dados, esperando
     * que os dados venham do corpo da nossa requisição.
     * 
     * @param Person Pessoa que será inserida no banco de dados
     * @return A mensagem indicando que a pessoa foi criada e informando o Id da mesma
     */ 
    @PostMapping
    private MessageResponseDTO createPerson(@RequestBody Person person){
        Person savedPerson = personRepository.save( person );
        
        return MessageResponseDTO.builder()
                .message( "Created Person with Id: " + savedPerson.getId() )
                .build();
    }
}
