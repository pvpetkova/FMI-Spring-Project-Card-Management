package bg.fmi.cms.controller;

import bg.fmi.cms.model.Request;
import bg.fmi.cms.model.constats.RequestStatus;
import bg.fmi.cms.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping(value = "/requests")
    public String showAllRequests(Model model) {
        model.addAttribute("requestsList", requestService.listAllRequests());
        return "requests";
    }

    @PostMapping(value = "/requests/filter")
    public String showFilteredRequests(@RequestParam(name = "filterStatus") String filterStatus, Model model) {
        List<Request> requests = requestService.getFilteredRequests(RequestStatus.valueOf(filterStatus));
        model.addAttribute("requestsList", requests);
        return "requests";
    }

    @PostMapping(value = "/status")
    public String changeRequestStatus(@ModelAttribute RequestStatus filterStatus, @ModelAttribute Request request, Model model) {
        requestService.changeRequestStatus(request.getId(), filterStatus);
        return "requests";
    }

    @GetMapping(value = "/requests/new")
    public String newRequest(Model model) {
        model.addAttribute("request", new Request());
        return "request-form";
    }

    @PostMapping(value = "/requests/new")
    public String createNewRequest(Model model, @ModelAttribute Request request) {
//        model.addAttribute("request", new Request());
        return "request-form";
    }

}
