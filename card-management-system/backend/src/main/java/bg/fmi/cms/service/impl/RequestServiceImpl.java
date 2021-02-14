package bg.fmi.cms.service.impl;

import bg.fmi.cms.model.Request;
import bg.fmi.cms.model.constats.RequestStatus;
import bg.fmi.cms.service.RequestService;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    @Override
    public Iterable<Request> getAll() {
        return null;
    }

    @Override
    public void add(Request request) {

    }

    @Override
    public void setStatus(Long id, RequestStatus requestStatus) {

    }

    @Override
    public void delete(Long id) {

    }
}
