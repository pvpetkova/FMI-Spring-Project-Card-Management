package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.KeyType;
import bg.fmi.cms.model.constats.KeyUsage;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "key", schema = "cms")
public class Key {
    @Id
//    @SequenceGenerator(name = "seq_key_id", sequenceName = "seq_key_id",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_key_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Basic
    private String keyName;
    @Basic
    private String keyValue;
    @Enumerated(EnumType.ORDINAL)
    private KeyType keyType;
    @Enumerated(EnumType.ORDINAL)
    private KeyUsage keyUsage;
    @ManyToOne
    private Bin bin;
}
