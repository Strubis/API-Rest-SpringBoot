package one.digitalinnovation.personapi.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author Emerson
 */

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor( onConstructor = @__( @Autowired ) )
public class PersonController {
    private PersonService personService;
    
    // Injeção de Dependência
//    @Autowired
//    public PersonController(PersonService personService){
//        this.personService = personService;
//    }
    
    /**
     * Método para criar um novo "objeto" para o nosso banco de dados, esperando
     * que os dados venham do corpo da nossa requisição.
     * 
     * @param personDTO Pessoa que será inserida no banco de dados
     * @return A mensagem indicando que a pessoa foi criada e informando o Id da mesma
     */ 
    @PostMapping
    @ResponseStatus( HttpStatus.CREATED )
    public PersonDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson( personDTO );
    }
    
    /**
     * Método para retornar todas as pessoas cadastradas no banco de dados.
     * 
     * @return Uma lista com todos usuários
     */
    @GetMapping
    @ResponseStatus( HttpStatus.OK )
    public List<PersonDTO> listAll(){
        return personService.listAll();
    }
    
    /**
     * Método para retornar uma pessoa cadastrada no banco de dados.
     * 
     * @param id o identificador para encontrar a pessoa desejada
     * @return Os dados da pessoa, se for encontrada
     * @throws PersonNotFoundException, caso a pessoa não seja encontrada
     */
    @GetMapping( "/{id}" )
    public PersonDTO listById(@PathVariable Long id) throws PersonNotFoundException{
        return personService.listById( id );
    }
    
    /**
     * Apaga uma pessoa do nosso banco de dados.
     * 
     * @param id o identificador para apagar a pessoa desejada
     * @throws PersonNotFoundException, caso a pessoa não seja encontrada
     */
    @DeleteMapping( "/{id}" )
    @ResponseStatus( HttpStatus.NO_CONTENT )
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException{
        personService.deleteById( id );
    }
    
    /**
     * Atualiza os dados de uma pessoa do nosso banco de dados.
     * 
     * @param id o identificador para apagar a pessoa desejada
     * @param personDTO os novos dados da pessoa
     * @throws PersonNotFoundException, caso a pessoa não seja encontrada
     */
    @PutMapping( "/{id}" )
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException{
        return personService.updateById( id, personDTO );
    }
}
