package one.digitalinnovation.personapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Emerson
 */

@RestController
@RequestMapping( "/api/v1/people" )
public class PersonController {
    
    @GetMapping
    private String test(){
        return "Hello World!";
    }
}
