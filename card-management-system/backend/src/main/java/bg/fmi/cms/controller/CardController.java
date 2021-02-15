package bg.fmi.cms.controller;

import bg.fmi.cms.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping("/greeting")
    public String greeting(Model model) {
        return "greeting";
    }
}
