package one.digitalinnovation.personapi.dto.response;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Emerson
 */

@Data
@Builder
public class MessageResponseDTO {
    private String message;
}
