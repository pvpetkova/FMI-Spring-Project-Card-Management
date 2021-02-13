package bg.fmi.cms.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Bin {
    @Id
    private Long id;
    @Basic
    private String bin;
    @Basic
    private String description;
    @ManyToOne()
    private BinRange binRange;
}
