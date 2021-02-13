package bg.fmi.cms.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Data
public class BinRange {
    @Id
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
