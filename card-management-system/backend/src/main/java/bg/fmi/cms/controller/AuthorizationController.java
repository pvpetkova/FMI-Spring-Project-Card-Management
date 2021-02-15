package bg.fmi.cms.controller;

import bg.fmi.cms.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {
    @Autowired
    CardService cardService;

    @GetMapping("authorize/{encPan}{pinBlock}")
    public boolean authorizeWithPin(@PathVariable String encPan, @PathVariable String pinBlock) {
        return cardService.authorize(encPan, pinBlock);
    }

    @GetMapping("authorize/{encPan}{cvv}")
    public boolean authorizeOnline(@PathVariable String encPan, @PathVariable String cvv) {
        return false;
    }

}
