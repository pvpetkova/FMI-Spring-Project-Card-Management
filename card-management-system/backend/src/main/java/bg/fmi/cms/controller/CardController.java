package bg.fmi.cms.controller;

import bg.fmi.cms.model.Card;
import bg.fmi.cms.model.Request;
import bg.fmi.cms.service.BinService;
import bg.fmi.cms.service.CardService;
import bg.fmi.cms.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/card-prod")
public class CardController {
    @Autowired
    CardService cardService;
    @Autowired
    RequestService requestService;
    @Autowired
    BinService binService;


    @GetMapping("/request")
    public String newCardRequest(Model model) {
        Request request = new Request();
        request.setRequestSubject(new Card());
        model.addAttribute("request", request);
        model.addAttribute("binList", binService.getAll());
        return "new-request-form";
    }

    @PostMapping("/request")
    public String createNewCardRequest(@ModelAttribute Request request, Model model) {
        request.setRequestSubject(cardService.addCard(request.getRequestSubject()));
        requestService.addNewCardRequest(request);
        model.addAttribute("request", request);
        return "greeting";
    }

    @GetMapping("/revoke")
    public String revokeCardRequest(Model model) {
        Request request = new Request();
        request.setRequestSubject(new Card());
        model.addAttribute("request", request);
        return "revoke-request-form";
    }

    @PostMapping("/revoke")
    public String createRevokeCardRequest(@ModelAttribute Request request, Model model) {
        Card card = cardService.getByClearPan(request.getRequestSubject().getPan());
        request.setRequestSubject(card);
        requestService.addRevokeRequest(request);
        return "greeting";
    }

    @GetMapping("/cards")
    public String getAllCards(Model model) {
        model.addAttribute("cardsList", cardService.getClearCardDetails());
        return "cards";
    }

    @GetMapping("/cards/{pan}")
    public String getCard(Model model, @PathVariable String pan) {
        model.addAttribute("card", cardService.getByClearPan(pan));
        return "card-detail";
    }

    @PutMapping("/cards/{id}/status")
    public ModelAndView changeCardStatus(ModelMap model, @PathVariable Long id, @ModelAttribute Card card) {
        cardService.updateCardStatus(id, card.getCardStatus());
        model.addAttribute("cardsList", cardService.getClearCardDetails());
        return new ModelAndView("redirect:/card-prod/cards", model);
    }

    @PutMapping("/cards/{id}/update-pin")
    public ModelAndView updateCardPin(ModelMap model, @PathVariable Long id, @ModelAttribute Card card) {
        cardService.changePin(id, card.getPinBlock());
        model.addAttribute("cardsList", cardService.getClearCardDetails());
        return new ModelAndView("redirect:/card-prod/cards", model);
    }
}
