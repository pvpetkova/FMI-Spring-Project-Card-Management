package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.AccountStatus;
import bg.fmi.cms.model.constats.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "changeRequests")
@ToString(exclude = "changeRequests")
@Table(name = "user", schema = "cms")
public class User {
    @Id
//    @SequenceGenerator(name = "seq_user_id", sequenceName = "seq_user_id",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_name")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Transient
    private String repeatPassword;
    @Basic
    @Column(name = "role")
    private Role role;
    @Column(name = "account_status")
    @Enumerated(EnumType.ORDINAL)
    private AccountStatus accountStatus;
    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<UserChangeRequest> changeRequests;
}
