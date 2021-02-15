package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.RequestStatus;
import bg.fmi.cms.model.constats.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    private long userId;
    @Enumerated(EnumType.ORDINAL)
    private Role requestedRole;
    @Enumerated(EnumType.ORDINAL)
    private RequestStatus status;

}
