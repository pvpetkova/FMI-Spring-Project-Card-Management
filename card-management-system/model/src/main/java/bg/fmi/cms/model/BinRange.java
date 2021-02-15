package bg.fmi.cms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "bin_range", schema = "cms")
public class BinRange {
    @Id
//    @SequenceGenerator(name = "seq_bin_range_id", sequenceName = "seq_bin_range_id",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bin_range_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    private String startingBin;
    @Basic
    private String endBin;
    @Basic
    private String description;
    @OneToMany(mappedBy = "binRange")
    private Collection<Bin> bins;
}
