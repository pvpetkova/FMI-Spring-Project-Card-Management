package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.RequestStatus;
import bg.fmi.cms.model.constats.Role;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
@Table(name = "user_change_request")
public class UserChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    @Column(name = "user_id")
    private long userId;
    @Enumerated(EnumType.ORDINAL)
    private Role requestedRole;
    @Enumerated(EnumType.ORDINAL)
    private RequestStatus status;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
