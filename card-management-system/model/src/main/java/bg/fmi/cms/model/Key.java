package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.KeyType;
import bg.fmi.cms.model.constats.KeyUsage;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class Key {
    @Id
    private long id;
    @Basic
    private String keyName;
    @Basic
    private String keyValue;
    @Basic
    private KeyType keyType;
    @Basic
    private KeyUsage keyUsage;
}
