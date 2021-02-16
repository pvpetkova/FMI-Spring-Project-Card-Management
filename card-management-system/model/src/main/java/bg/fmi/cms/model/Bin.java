package bg.fmi.cms.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "binRange")
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
}
