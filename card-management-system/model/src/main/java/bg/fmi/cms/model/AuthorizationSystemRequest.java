package bg.fmi.cms.model;

import lombok.Data;

@Data
public class AuthorizationSystemRequest {
    private String panForPin;
    private String pin;
    private String panForCvv;
    private String cvv;
    private String pinStatus;
    private String cvvStatus;
}
