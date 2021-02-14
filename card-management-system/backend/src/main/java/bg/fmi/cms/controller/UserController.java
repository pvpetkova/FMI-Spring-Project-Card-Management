package bg.fmi.cms.controller;

import bg.fmi.cms.model.User;
import bg.fmi.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/register")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/new")
    public String sendRegisterRequest(@ModelAttribute User newUser, Model model) {
        System.out.println(newUser.toString());
        userService.add(newUser);
        model.addAttribute("message", "Your registration request has been submitted. " +
                "It must be reviewed and approved by an administrator before you can access the system.");
        return "register";
    }

    @GetMapping(value = "/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute User user, Model model) {
        return "";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "";
    }

}
