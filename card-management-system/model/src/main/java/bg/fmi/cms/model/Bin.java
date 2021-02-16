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
//    @SequenceGenerator(name = "seq_bin_id", sequenceName = "seq_bin_id", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bin_id")
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
