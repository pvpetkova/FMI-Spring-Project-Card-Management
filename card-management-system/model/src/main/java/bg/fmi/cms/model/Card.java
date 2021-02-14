package bg.fmi.cms.model;

import lombok.Data;

import javax.persistence.*;
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
    @Basic
    private Integer cardStatus;

    @OneToOne()
    private Bin bin;
}
