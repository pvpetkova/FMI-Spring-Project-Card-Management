package bg.fmi.cms.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bin", schema = "cms")
public class Bin {
    @Id
//    @SequenceGenerator(name = "seq_bin_id", sequenceName = "seq_bin_id", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Basic
    private String bin;
    @Basic
    private String description;
    @ManyToOne()
    private BinRange binRange;
}
