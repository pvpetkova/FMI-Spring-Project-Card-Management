package bg.fmi.cms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "bin_range", schema = "cms")
public class BinRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    private String startingBin;
    @Basic
    private String endBin;
    @Basic
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name="bin_range", referencedColumnName = "id")
    private Collection<Bin> bins;
}
