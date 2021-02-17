package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.CardStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "card", schema = "cms")
public class Card {
    @Id
//    @SequenceGenerator(name = "seq_card_id", sequenceName = "seq_card_id",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private String expiryDate;
    @Enumerated(EnumType.ORDINAL)
    private CardStatus cardStatus;

    @OneToOne()
    @JoinColumn(name = "bin_id", referencedColumnName = "id")
    private Bin bin;
}
