package bg.fmi.cms.service.impl;

import bg.fmi.cms.model.Request;
import bg.fmi.cms.model.constats.RequestStatus;
import bg.fmi.cms.repo.RequestRepository;
import bg.fmi.cms.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Override
    public List<Request> listAllRequests() {
        return (List<Request>) requestRepository.findAll();
    }

    @Override
    public Request getRequestDetails(long requestId) {
        return requestRepository.findById(requestId).orElse(null);
    }

    @Override
    public void changeRequestStatus(long requestId, RequestStatus newStatus) {
        Optional<Request> request = requestRepository.findById(requestId);
        if (request.isPresent()) {
            request.get().setRequestStatus(newStatus);
            requestRepository.save(request.get());
        }
    }

    @Override
    public List<Request> getFilteredRequests(RequestStatus filterStatus) {
        return requestRepository.findAllByRequestStatus(filterStatus);
    }

    @Override
    public void add(Request request) {
        requestRepository.save(request);
    }

    @Override
    public void delete(Long id) {
        requestRepository.findById(id).ifPresent(value -> requestRepository.delete(value));
    }
}
