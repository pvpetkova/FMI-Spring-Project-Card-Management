package bg.fmi.cms.controller;

import bg.fmi.cms.model.AuthorizationSystemRequest;
import bg.fmi.cms.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authorize")
public class AuthorizationController {
    @Autowired
    CardService cardService;

    @GetMapping("")
    public String getAuth(Model model) {
        model.addAttribute("authObj", new AuthorizationSystemRequest());
        return "authorization";
    }

    @GetMapping("/pin")
    public ModelAndView authorizeWithPin(ModelMap model, @RequestParam("pin") String pinBlock, @RequestParam(name = "panForPin") String encPan) {
        boolean success = false;
        try {
            success = cardService.authorize(encPan, pinBlock);
        } catch (Exception ex) {
            success = false;
        }
        AuthorizationSystemRequest authObj = new AuthorizationSystemRequest();
        authObj.setPinStatus(success ? "Success" : "Failed");
        model.addAttribute("authObj", authObj);
        return new ModelAndView("authorization", model);
    }

    @GetMapping("/cvv")
    public ModelAndView authorizeOnline(ModelMap model, @RequestParam("panForCvv") String encPan, @RequestParam("cvv") String cvv) {
        boolean success = false;
        try {
            success = cardService.authorize(encPan, cvv);
        } catch (Exception ex) {
            success = false;
        }
        AuthorizationSystemRequest authObj = new AuthorizationSystemRequest();
        authObj.setCvvStatus(success ? "Success" : "Failed");
        model.addAttribute("authObj", authObj);
        return new ModelAndView("authorization", model);
    }

}
