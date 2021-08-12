package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.entity.Phone;
import one.digitalinnovation.personapi.enums.PhoneType;

/**
 *
 * @author Emerson
 */
public class PhoneUtils {
    private static final String PHONE_NUMBER = "3311111-1111";
    private static final PhoneType PHONE_TYPE = PhoneType.COMERCIAL;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number( PHONE_NUMBER )
                .type( PHONE_TYPE )
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id( PHONE_ID )
                .number( PHONE_NUMBER )
                .type( PHONE_TYPE )
                .build();
    }
}
