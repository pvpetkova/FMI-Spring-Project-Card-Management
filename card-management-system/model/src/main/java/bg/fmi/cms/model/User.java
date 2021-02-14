package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.AccountStatus;
import bg.fmi.cms.model.constats.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "cms")
public class User {
    @Id
//    @SequenceGenerator(name = "seq_user_id", sequenceName = "seq_user_id",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "user_name")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "role")
    private Role role;
    @Basic
    @Column(name = "account_status")
    private AccountStatus accountStatus;
}
