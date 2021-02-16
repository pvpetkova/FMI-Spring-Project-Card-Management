package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.RequestStatus;
import bg.fmi.cms.model.constats.RequestType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "request", schema = "cms")
public class Request {
    @Id
//    @SequenceGenerator(name = "seq_request_id", sequenceName = "seq_request_id",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "issuer", referencedColumnName = "id")
    private User issuer;
    @Enumerated(EnumType.ORDINAL)
    private RequestType requestType;
    @Enumerated(EnumType.ORDINAL)
    private RequestStatus requestStatus;
    @Basic
    private String reason;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_subject", referencedColumnName = "id")
    private Card requestSubject;
}
