package one.digitalinnovation.personapi.controller;

import one.digitalinnovation.personapi.builder.PersonDTOBuilder;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static one.digitalinnovation.personapi.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import org.mockito.stubbing.Answer;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


/**
 *
 * @author Emerson
 */

@ExtendWith( MockitoExtension.class )
public class PersonControllerTest {
    
    private static final String PERSON_API_URL_PATH = "/api/v1/people";
    private MockMvc mockMvc;
    
    @Mock
    private PersonService personService;
    
    @InjectMocks
    private PersonController personController;
    
    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }
    
    @Test
    void whenPOSTIsCalledThenAPersonIsCreated() throws Exception{
        // Given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        
        // When
        when( personService.createPerson( personDTO ) ).thenReturn(personDTO);
        
        // Then
        mockMvc.perform(post(PERSON_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(personDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(personDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(personDTO.getLastName())))
                .andExpect(jsonPath("$.cpf", is(personDTO.getCpf())));
    }
    
    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws PersonNotFoundException, Exception{
        // Given
        PersonDTO personDTO = PersonDTOBuilder.builder().build().toPersonDTO();
        
        // When
        when( personService.listById(personDTO.getId()) ).thenReturn(personDTO);
        
        // Then
        mockMvc.perform( MockMvcRequestBuilders.get( 
                PERSON_API_URL_PATH + "/" + personDTO.getId() )
                .contentType( MediaType.APPLICATION_JSON ))
                .andExpect( status().isOk() )
                .andExpect( jsonPath( "$.firstName", is( personDTO.getFirstName() ) ) )
                .andExpect( jsonPath( "$.lastName", is( personDTO.getLastName() ) ) )
                .andExpect( jsonPath( "$.cpf", is( personDTO.getCpf() ) ) );
    }
}
