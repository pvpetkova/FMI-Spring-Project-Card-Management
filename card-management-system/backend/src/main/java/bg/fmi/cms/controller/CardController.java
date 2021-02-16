package bg.fmi.cms.controller;

import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.Request;
import bg.fmi.cms.service.CardService;
import bg.fmi.cms.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/card-prod")
public class CardController {
    @Autowired
    CardService cardService;
    @Autowired
    RequestService requestService;

    // TODO move this
    @GetMapping("/greeting")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/request")
    public String newCardRequest(Model model) {
        Request request = new Request();
        request.setRequestSubject(new Card());
        model.addAttribute("request", request);
        return "new-request-form";
    }

    @PostMapping("/request")
    public String createNewCardRequest(@ModelAttribute Request request, Model model) {
        requestService.addNewCardRequest(request);
        model.addAttribute("request", request);
        return "greeting";
    }

    @GetMapping("/revoke")
    public String revokeCardRequest(Model model) {
        model.addAttribute("request", new Request());
        return "revoke-request-form";
    }

    @PostMapping("/revoke")
    public String createRevokeCardRequest(@ModelAttribute Request request, Model model) {
        requestService.addRevokeRequest(request);
        return "greeting";
    }
}
