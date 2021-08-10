package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emerson
 */

@RestController
@RequestMapping( "/api/v1/people" )
public class PersonController {
    private PersonService personService;
    
    // Injeção de Dependência
    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }
    
    /**
     * Método para criar um novo "objeto" para o nosso banco de dados, esperando
     * que os dados venham do corpo da nossa requisição.
     * 
     * @param Person Pessoa que será inserida no banco de dados
     * @return A mensagem indicando que a pessoa foi criada e informando o Id da mesma
     */ 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson( person );
    }
}
