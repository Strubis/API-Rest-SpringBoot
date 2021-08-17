package one.digitalinnovation.personapi.builder;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.enums.PhoneType;

/**
 *
 * @author Emerson
 */

@Builder
public class PersonDTOBuilder {
    
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String firstName = "Emerson";

    @Builder.Default
    private String lastName = "Lima";

    @Builder.Default
    private String cpf = "801.815.005-23";

    @Builder.Default
    private String birthDate = "16-08-2002";

    @Builder.Default
    private List<PhoneDTO> phones = new ArrayList<>();

    public PersonDTO toPersonDTO() {
        PhoneDTO phoneTestOne = new PhoneDTO();
        phoneTestOne.setType( PhoneType.HOME );
        phoneTestOne.setNumber( "(11)2222-33333" );
        
        phones.add( phoneTestOne );
        
        return new PersonDTO( id,
                firstName,
                lastName,
                cpf,
                birthDate,
                phones );
    }
}
