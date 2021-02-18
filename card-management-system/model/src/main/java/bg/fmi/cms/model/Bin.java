package bg.fmi.cms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"binRange","symmetricKeys"})
@EqualsAndHashCode(exclude = "binRange")
@Table(name = "bin", schema = "cms")
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String bin;
    @Basic
    private String description;
    @ManyToOne()
    @JoinColumn(name = "bin_range", referencedColumnName = "id")
    private BinRange binRange;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bin_id", referencedColumnName = "id")
    private List<SymmetricKey> symmetricKeys;
}
