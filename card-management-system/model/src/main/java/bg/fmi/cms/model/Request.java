package bg.fmi.cms.model;

import bg.fmi.cms.model.constats.RequestStatus;
import bg.fmi.cms.model.constats.RequestType;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Request {
    @Id
    private long id;
    @OneToOne
    private User issuer;
    @Basic
    private RequestType requestType;
    @Basic
    private RequestStatus requestStatus;
    @Basic
    private String reason;
    @OneToOne
    private Card requestSubject;
}
