package bg.fmi.cms.controller;

import bg.fmi.cms.model.Request;
import bg.fmi.cms.model.constats.RequestStatus;
import bg.fmi.cms.service.CardService;
import bg.fmi.cms.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    RequestService requestService;

    @Autowired
    CardService cardService;

    @GetMapping()
    public String showAllRequests(Model model) {
        model.addAttribute("requestsList", requestService.listAllRequests());
        return "requests";
    }

    @PostMapping(value = "/filter")
    public String showFilteredRequests(@RequestParam(name = "filterStatus") String filterStatus, Model model) {
        List<Request> requests = requestService.getFilteredRequests(RequestStatus.valueOf(filterStatus));
        model.addAttribute("requestsList", requests);
        return "requests";
    }

    @GetMapping("/{requestId}")
    public String showRequestDetails(@PathVariable(name = "requestId") Integer requestId, Model model) {
        model.addAttribute("request", requestService.getRequestDetails(requestId));
        return "request-details";
    }

    @PutMapping(value = "/{requestId}/status")
    public String changeRequestStatus(@ModelAttribute Request request,
                                      @PathVariable(name = "requestId") Integer requestId,
                                      Model model) {
        requestService.changeRequestStatus(requestId, request.getRequestStatus());
        return "redirect:/requests";
    }
}
