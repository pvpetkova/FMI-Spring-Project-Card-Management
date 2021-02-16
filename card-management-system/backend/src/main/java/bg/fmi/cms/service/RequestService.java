package bg.fmi.cms.service;

import bg.fmi.cms.model.Request;
import bg.fmi.cms.model.constats.RequestStatus;

import java.util.List;

public interface RequestService {
    List<Request> listAllRequests();

    Request getRequestDetails(long requestId);

    void changeRequestStatus(long requestId, RequestStatus newStatus);

    List<Request> getFilteredRequests(RequestStatus filterStatus);

    void addNewCardRequest(Request request);

    void addRevokeRequest(Request request);

    void delete(Long id);
}
