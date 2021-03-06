package bg.fmi.cms.controller;

import bg.fmi.cms.model.User;
import bg.fmi.cms.model.UserChangeRequest;
import bg.fmi.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String getUsersList(Model model) {
        model.addAttribute("usersList", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/register-admin")
    public String registerAdmin(Model model) {
        model.addAttribute("user", new User());
        return "register-admin";
    }

    @GetMapping(value = "/register")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String sendRegisterRequest(@ModelAttribute User newUser, Model model) {
        userService.add(newUser);
        model.addAttribute("message", "Your registration request has been submitted. " +
                "It must be reviewed and approved by an administrator before you can access the system.");
        return "register";
    }

    @PostMapping(value = "/register-admin")
    public String registerUser(@ModelAttribute User newUser) {
        userService.addAdm(newUser);
        return "redirect:/users";
    }

    @GetMapping(value = "/login")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return new ModelAndView("greeting");
    }

    @PostMapping(value = "/logout")
    public String logout() {
        return "";
    }

    @GetMapping(value = "/settings")
    public String getUserSettings(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);
        UserChangeRequest changeRequest = new UserChangeRequest();
        changeRequest.setUser(currentUser);
        model.addAttribute("userChangeRequest", changeRequest);
        return "settings";
    }

    @GetMapping(value = "/{userID}")
    public String viewUser(Model model, @PathVariable long userID) {
        model.addAttribute("user", userService.getById(userID));
        return "admin-user-view";
    }

    @DeleteMapping(value = "/{userID}")
    public String deleteUser(Model model, @PathVariable long userID) {
        userService.delete(userID);
        return "redirect:/users";
    }

    @PutMapping(value = "/{userID}")
    public String editUser(Model model, @PathVariable long userID, @ModelAttribute User user) {
        user.setId(userID);
        userService.update(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/settings")
    public String changeUserSettings(@ModelAttribute UserChangeRequest userChangeRequest, Model model) {
        userService.addUserChangeRequest(userChangeRequest);
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "settings";
    }
}
