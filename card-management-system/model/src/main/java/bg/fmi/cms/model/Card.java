package bg.fmi.cms.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Data
public class Card {
    @Id
    private long id;
    @Basic
    private String pan;
    @Basic
    private String cardHolderName;
    @Basic
    private String pinBlock;
    @Basic
    private String cvv;
    @Basic
    private LocalDate expiryDate;

    @OneToOne()
    private Bin bin;
}
