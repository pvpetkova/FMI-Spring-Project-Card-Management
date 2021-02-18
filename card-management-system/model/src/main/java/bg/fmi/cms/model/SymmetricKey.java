package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.KeyType;
import bg.fmi.cms.model.constats.KeyUsage;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "symmetric_key", schema = "cms")
public class SymmetricKey {
    @Id
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
