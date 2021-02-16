package bg.fmi.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {
    @GetMapping("/greeting")
    public String greeting(){
        return "greeting";
    }
}
