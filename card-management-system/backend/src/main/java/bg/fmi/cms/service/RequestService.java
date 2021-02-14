package bg.fmi.cms.service;

import bg.fmi.cms.model.Request;
import bg.fmi.cms.model.constats.RequestStatus;

public interface RequestService {
    Iterable<Request> getAll();
    void add(Request request);
    void setStatus(Long id, RequestStatus requestStatus);
    void delete(Long id);
}
